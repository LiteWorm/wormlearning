# Typora

Typora是一款免费、开源的markdown文本编辑器，它将所有markdown标记语言的优点融合起来，并且拥有十分突出的界面，操作简单，支持许多markdown语法，同时操作简单，适合初次使用markdown的新手。下面将介绍Typora的常用语法：
### 一、标题
#### 1.1 markdown 中标题分为 1-6 级，一级标题展示如下
在行末添加一个“#”后加上标题即可，使用不同多的“#”，代表不同级别的标题，如下：
一级标题：
`## 二级标题`
三级标题:
以此类推，六级标题表示为：
`
#### 1.2 Typora 中使用快捷键
Typora实现标题样式的快捷键为Ctrl+#,其中#为1-6的数字，比如Ctrl+1代表第一级标题，Ctrl+2代表第二级标题，依次类推。
### 二、分割线
markdown中使用三个或三个以上的星号，减号，下划线来建立一个分隔线，如下：
`***`
`---`
`   `
实例：
Typora 支持快捷键：Ctrl+Shift+-或Ctrl+- 来创建分割线，样式均为三个减号。
### 三、加粗、斜体
加粗：在需要加粗的文字两侧加两个星号：`**加粗文字**`
使用 “>” 可以对文本进行引用，可以嵌套引用：
> 这里是引用的文字
>
> > 这是嵌套引用
Typora 快捷键 Ctrl+q 可以创建引用格式。
语法：
```markdown
![图片alt](图片地址 ''图片title'')
```
图片alt表示图片的替代文字，相当于html中的alt；图片title是对图片的补充描述文字，在鼠标移到图片上可以看到；
#### 7.1 无序列表
语法：使用“*”“+”“-”来标识出每一个列表项，如下：
`* 列表项1`
使用三个反引号（ `）来标识出代码，且可以给出高亮提示，右侧可以给出代码的所属语言，如下：
```html
<!-- 这是 HTML 代码 -->
<div>
<h1>这是标题</h1>
<p>这是正文</p>
</div>
```
#### 8.2 行内代码
在需要添加代码的两侧添加一堆反引号来标识，如下：
`行内代码使用`` ` 这种形式`
表格以 | 来分割每一列，以 - 来分割每一行，如下：
| 列1  |  列2  |  列3  |
| :-: | :-: | :-: |
| 内容1  |  内容2  |  内容3  |
### 末尾