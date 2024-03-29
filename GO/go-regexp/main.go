package main

import (
	"fmt"
	_ "io"
	"regexp"
)

func main() {
	a := "I am learning Go language"

	re, _ := regexp.Compile("[a-z]{2,4}")

	//查找符合正则的第一个
	one := re.Find([]byte(a))
	fmt.Println("Find:", string(one))

	//查找符合正则的所有slice,n小于0表示返回全部符合的字符串，不然就是返回指定的长度
	all := re.FindAll([]byte(a), -1)
	fmt.Println("FindAll", all)

	//查找符合条件的index位置,开始位置和结束位置
	index := re.FindIndex([]byte(a))
	fmt.Println("FindIndex", index)

	//查找符合条件的所有的index位置，n同上
	allindex := re.FindAllIndex([]byte(a), -1)
	fmt.Println("FindAllIndex", allindex)

	re2, _ := regexp.Compile("am(.*)lang(.*)")

	//查找Submatch,返回数组，第一个元素是匹配的全部元素，第二个元素是第一个()里面的，第三个是第二个()里面的
	//下面的输出第一个元素是"am learning Go language"
	//第二个元素是" learning Go "，注意包含空格的输出
	//第三个元素是"uage"
	submatch := re2.FindSubmatch([]byte(a))
	fmt.Println("FindSubmatch", submatch)
	for _, v := range submatch {
		fmt.Println(string(v))
	}

	//定义和上面的FindIndex一样
	submatchindex := re2.FindSubmatchIndex([]byte(a))
	fmt.Println(submatchindex)

	//FindAllSubmatch,查找所有符合条件的子匹配
	submatchall := re2.FindAllSubmatch([]byte(a), -1)
	fmt.Println(submatchall)

	//FindAllSubmatchIndex,查找所有字匹配的index
	submatchallindex := re2.FindAllSubmatchIndex([]byte(a), -1)
	fmt.Println(submatchallindex)
}

//
//前面介绍过匹配函数，Regexp也定义了三个函数，它们和同名的外部函数功能一模一样，其实外部函数就是调用了这Regexp的三个函数来实现的：
//
//func (re *Regexp) Match(b []byte) bool
//func (re *Regexp) MatchReader(r io.RuneReader) bool
//func (re *Regexp) MatchString(s string) bool
//接下里让我们来了解替换函数是怎么操作的？
//
//func (re *Regexp) ReplaceAll(src, repl []byte) []byte
//func (re *Regexp) ReplaceAllFunc(src []byte, repl func([]byte) []byte) []byte
//func (re *Regexp) ReplaceAllLiteral(src, repl []byte) []byte
//func (re *Regexp) ReplaceAllLiteralString(src, repl string) string
//func (re *Regexp) ReplaceAllString(src, repl string) string
//func (re *Regexp) ReplaceAllStringFunc(src string, repl func(string) string) string
//这些替换函数我们在上面的抓网页的例子有详细应用示例，
//
//接下来我们看一下Expand的解释：
//
//func (re *Regexp) Expand(dst []byte, template []byte, src []byte, match []int) []byte
//func (re *Regexp) ExpandString(dst []byte, template string, src string, match []int) []byte
//那么这个Expand到底用来干嘛的呢？请看下面的例子：
//
//func main() {
//	src := []byte(`
//        call hello alice
//        hello bob
//        call hello eve
//    `)
//	pat := regexp.MustCompile(`(?m)(call)\s+(?P<cmd>\w+)\s+(?P<arg>.+)\s*$`)
//	res := []byte{}
//	for _, s := range pat.FindAllSubmatchIndex(src, -1) {
//		res = pat.Expand(res, []byte("$cmd('$arg')\n"), src, s)
//	}
//	fmt.Println(string(res))
//}
