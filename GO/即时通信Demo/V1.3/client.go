package main

import (
	"flag"
	"fmt"
	"io"
	"net"
	"os"
)

type Client struct {
	ServerIp   string
	ServerPort int
	Name       string
	conn       net.Conn
	flag       int //当前client的模式
}

func (client *Client) Run() {
	//除非输入0否则一直循环
	for client.flag != 0 {
		//除非输入0-3，否则一直循环
		for client.menu() != true {
		}
		//根据flag执行不同业务
		switch client.flag {
		case 1:
			fmt.Println("选择公聊...")
		case 2:
			fmt.Println("选择私聊...")
		case 3:
			client.UpdateName()
		}
	}
}
func (client *Client) menu() bool {
	var flag int //接收用户输入
	fmt.Println("1.公聊")
	fmt.Println("2.私聊")
	fmt.Println("3.改名")
	fmt.Println("0.退出")
	fmt.Scanln(&flag)
	if flag >= 0 && flag <= 3 {
		client.flag = flag
		return true
	} else {
		fmt.Println("输入不合法")
		return false
	}
}

var serverIp string
var serverPort int

// ./client -ip 127.0.0.1 -port 8888
func init() {
	flag.StringVar(&serverIp, "ip", "127.0.0.1", "设置服务器IP")
	flag.IntVar(&serverPort, "port", 8888, "设置服务器端口")
}

func NewClient(serverIp string, serverPort int) *Client {
	client := &Client{
		ServerIp:   serverIp,
		ServerPort: serverPort,
		flag:       999,
	}
	conn, err := net.Dial("tcp", fmt.Sprintf("%s:%d", serverIp, serverPort))
	if err != nil {
		fmt.Println("net.Dial err:", err)
		return nil
	}
	client.conn = conn
	return client
}
func (client *Client) UpdateName() bool {
	fmt.Println(">>>>>请输入用户名：")
	fmt.Scanln(&client.Name)
	sendMsg := "rename|" + client.Name + "\n"
	_, err := client.conn.Write([]byte(sendMsg))
	if err != nil {
		fmt.Println("conn.Write err:", err)
		return false
	}
	return true
}

//处理server回应的消息，直接显示到标准输出即可
func (client *Client) DealResponse() {
	//一旦client有数据，就直接copy到stdout标准输出上，永久阻塞监听
	io.Copy(os.Stdout, client.conn)
}

func (client *Client) PublicChat() {
	var chatMsg string
	fmt.Println(">>>>>请输入聊天内容，exit退出")
	fmt.Scanln(&chatMsg)
	for chatMsg != "exit" {
		if len(chatMsg) != 0 {
			sendMsg := chatMsg + "\n"
			_, err := client.conn.Write([]byte(sendMsg))
			if err != nil {
				fmt.Println("conn.Write err:", err)
				break
			}
			chatMsg = ""
			fmt.Println(">>>>>请输入聊天内容，exit退出")
			fmt.Scanln(&chatMsg)
		}
	}
}

func main() {
	flag.Parse()
	client := NewClient(serverIp, serverPort)
	if client == nil {
		fmt.Println(">>>>>连接服务器失败")
		return
	}
	fmt.Println(">>>>>连接服务器成功")
	//单独开启一个goroutine处理server的回执消息
	go client.DealResponse()
	//启动客户端业务
	client.Run()
}
