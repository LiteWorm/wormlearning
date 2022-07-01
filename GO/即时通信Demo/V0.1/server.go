package main

import (
	"fmt"
	"net"
)

type Server struct {
	Ip   string
	Port int
}

//创建一个server的接口
func NewServer(ip string, port int) *Server {
	server := &Server{
		Ip:   ip,
		Port: port,
	}
	return server
}

//启动服务器的接口
// 定义对象(类)的方法：   func (对象类型参数)方法名(参数列表)(返回值列表){ }
// 如果想通过方法修改对象，那么建议传递对象的地址 （结构体是值传递，通过结构体的指针修改结构体(地址传递)）  （也可以通过返回值修改对象）
//func (obj *MyInt) add() { }  // 对象调用时，会自动将对象的地址传给obj
func (this *Server) Start() {
	//socket listen
	listener, err := net.Listen("tcp", fmt.Sprintf("%s:%d", this.Ip, this.Port))
	if err != nil {
		fmt.Println("net.listen tcp err:", err)
	}
	//close listen socket
	defer listener.Close()
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

//链接成功，执行业务
func (this *Server) Handler(conn net.Conn) {
	fmt.Println("Connection Success...")
}
