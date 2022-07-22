package main

import (
	"fmt"
	"io"
	"net"
	"sync"
)

type Server struct {
	Ip   string
	Port int
	//在线用户列表
	OnlineMap map[string]*User
	mapLock   sync.RWMutex
	//消息广播channel
	Message chan string
}

//创建一个server的接口
func NewServer(ip string, port int) *Server {
	server := &Server{
		Ip:        ip,
		Port:      port,
		OnlineMap: make(map[string]*User),
		Message:   make(chan string),
	}
	return server
}

//坚挺Message广播，有消息则发送给全部在线User (在start方法中加载)
func (this *Server) ListenMessager() {
	for {
		msg := <-this.Message
		this.mapLock.Lock()
		for _, user := range this.OnlineMap {
			user.C <- msg
		}
		this.mapLock.Unlock()
	}
}

//消息广播
func (this *Server) BroadCast(user *User, msg string) {
	sendMsg := "[" + user.Addr + "]" + user.Name + ":" + msg
	this.Message <- sendMsg
}

//链接成功，执行业务
func (this *Server) Handler(conn net.Conn) {
	user := NewUser(conn)
	//用户上线，将用户加入OnlineMap中
	this.mapLock.Lock()
	this.OnlineMap[user.Name] = user
	this.mapLock.Unlock()
	//广播用户上线消息
	this.BroadCast(user, "上线了")
	//接收用户消息
	go func() {
		buf := make([]byte, 4096)
		for {
			//将conn中数据读取到buf中
			n, err := conn.Read(buf)
			if n == 0 {
				this.BroadCast(user, "下线")
				return
			}
			if err != nil && err != io.EOF {
				fmt.Println("Conn Read err:", err)
				return
			}
			//提取用户的消息（去除\n）
			msg := string(buf[:n-1])
			//将得到的消息进行广播
			this.BroadCast(user, msg)
		}
	}()
	//阻塞当前Handler(防止当前及子goroutine死亡)
	select {}
}

//启动服务器的接口
func (this *Server) Start() {
	//socket listen
	listener, err := net.Listen("tcp", fmt.Sprintf("%s:%d", this.Ip, this.Port))
	if err != nil {
		fmt.Println("net.listen tcp err:", err)
	}
	//close listen socket
	defer listener.Close()
	//启动监听Message的goroutine
	go this.ListenMessager()
	for {
		//accept
		conn, err := listener.Accept()
		if err != nil {
			fmt.Println("listen accept err:", err)
			continue
		}
		//do handler
		go this.Handler(conn)
	}
}
