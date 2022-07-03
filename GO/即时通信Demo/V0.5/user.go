package main

import "net"

type User struct {
	Name   string
	Addr   string
	C      chan string
	conn   net.Conn
	server *Server
}

//创建用户
func NewUser(conn net.Conn, server *Server) *User {
	userAddr := conn.RemoteAddr().String()
	user := &User{
		Name:   userAddr,
		Addr:   userAddr,
		C:      make(chan string),
		conn:   conn,
		server: server,
	}
	//启动监听当前user channel的goroutine
	go user.ListenMessage()
	return user
}

//监听当前User channel，有新消息则发送到客户端
func (this *User) ListenMessage() {
	for {
		msg := <-this.C
		this.conn.Write([]byte(msg + "\n"))
	}
}

//用户上线
func (this *User) Online() {
	this.server.mapLock.Lock()
	this.server.OnlineMap[this.Name] = this
	this.server.mapLock.Unlock()
	this.server.BroadCast(this, "上线了")
}

//用户下线
func (this *User) Offline() {
	this.server.mapLock.Lock()
	delete(this.server.OnlineMap, this.Name)
	this.server.mapLock.Unlock()
	this.server.BroadCast(this, "下线了")
}

//用户消息
func (this *User) DoMessage(msg string) {
	if msg == "who" {
		this.server.OnlineMap.Lock()
		for _, user := range this.server.OnlineMap {
			olMsg := "[" + user.Addr + "]" + user.Name + ":在线...\n"
			this.conn.Write([]byte(olMsg))
		}
		this.server.OnlineMap.Unlock()
	} else {
		this.server.BroadCast(this, msg)
	}
}
