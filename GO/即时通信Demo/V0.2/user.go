package main

import "net"

type User struct {
	Name string
	Addr string
	C    chan string
	conn net.Conn
}

//创建用户
func NewUser(conn net.Conn) *User {
	userAddr := conn.RemoteAddr().String()
	user := &User{
		Name: userAddr,
		Addr: userAddr,
		C:    make(chan string),
		conn: conn,
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
