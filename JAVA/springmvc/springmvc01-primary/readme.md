#步骤：
##1.新建maven web项目
##2.加入mavaen的依赖（坐标）
##3.注册springmvc框架中的核心对象DispatchServlet
##4.新建jsp发起一个请求
##5.新建处理器类，处理用户的请求
##6.新建jsp作为视图页面，显示数据和处理结果
##7.新建springmvc的配置文件
###1）声明处理器队形，处理用户的请求
###2）声明视图解析器对象，处理视图
##8.修改处理器方法，指定使用逻辑视图名称


#SpringMVC请求处理过程：
##1.用户在页面点击链接，发起请求some.do
##2.tomacat服务器接收somde.do的请求，tomcat根据web.xml中的url-pattern的设置把some.do的请求交给了DispatcherServlet
##3.DisptacherSevlet根据springmvc的配置文件中关于中奖处理器的配置
~~~xml
    <bean id="some.do" class="com.liteworm.controller.MyController"/>
~~~

框架就知道some.do是交给MyController
所以DispatcherServlet把请求分配给MyController处理
##4.MyController处理器，调用handleRequest()，处理请求。把结果放入到ModelAndView中
##5.ModelAndView会再次交给DispatchServlet，DispatcherServlet把数据放入到View，通过HttpServletResponse输出到浏览器，请求处理完成



# SpringMVC执行流程

## 时序图

~~~mermaid
%% 时序图例子,-> 直线，-->虚线，->>实线箭头
sequenceDiagram
  	%%浏览器
    participant B as Browser
    %%中央调度器
    participant DS as DispatcherServlet
    %%处理器映射器
    participant HM as HandelMapping
    %%处理器适配器
    participant HA as HandeleAdaptor
    %%处理器
    participant C as Controller
    %%视图解析器
    participant VR as ViewResolver
    %%视图
    participant V as View
 
B->>DS:1、请求
DS->>HM:2、请求
HM-->>DS:3.处理器执行链接
DS->>HA:4、处理器执行链接适配
HA->>C:5、执行
C-->>HA:6、ModelAndView
HA-->>DS:7、ModelAndView
DS->>VR:8、MOdelAndView
VR-->>DS:9、View
DS->>V:10、调用执行
V-->>DS: 
DS-->>B:11、响应
~~~

## 执行流程分析

1、用户发起请求到中央调度器DispatcherServlet

2、中央调度器DispatcherServlet把请求（some.do）交给了处理器的映射器

​	处理器的映射器：框架中的一组类，资额锡类都实现了HandleMapping接口。

​	处理器的应输液器作用：根据请求的信息（some.do）找到对应的处理器对象（MyController）

​		MyController对象是在springmvc的配置文件中定义的，MyController的对象是存放在springmvc的容器中。

​		意味着要从springmvc的容器中获取MyController的对象。

​		

~~~java
WebApplicationContext ctx = new ...
MyController controller = （MyController）ctx.getBean("some.do");
~~~

​		找到MyController对象是放在【处理器执行链】的对象中保存。

3、中央调度器DispatcherServlet吧【处理器执行链】保存的MyController嫁给了处理器的适配器

​	处理器的适配器：框架中的一族类，都实现了HandleAdapater接口。

​	处理器的适配器的作用：能够把处理器对应的适配器队形，通过适配器对象执行处理方法（handleRequest()）

​												并把返回值ModelAndView交给DispatcherServlet

​	通过处理器的适配器执行处理器方法，返回ModelAndView

4、中央调度器DispatcherServlet把发返回值：ModelAndView交给了视图解析器

​	视图解析器：框架中的一组类，这些类都实现了ViewResolver接口

​	视图解析器的作用：组成视图的完整路径，并创建视图对象，框架中把所有事项View接口的类都叫做视图。

​										每个jsp都是一视图（View）对象。

5、中央调度器DispatcherServlet调用View的方法，view.render()：视图的渲染方法。

​	view.render()的作用：

​		1）把ModelAndView中Model的数据放入到request，执行request.setAttribute();

​		2）执行视图View的foward，执行request.getRequestDispatcher("/WEB-INF/view/show.jsp").fowwrd();

~~~mermaid
graph TD
B[浏览器]-->|1.请求|DS(DispatcherServlet)
DS-->|2.请求| HM[HandleMapping]
HM-->|3.处理器执行链|DS
DS-->|4.处理器执行链|HA[HandleAdapator]
HA-->|5.处理器|C[Controller]
C-->|6.ModelAndView|HA
HA-->|7.ModelAndView|DS
DS-->|8.ModelAndVIew|VR[ViewResolver]
VR-->|9.View|DS
DS-->|10.调用执行|V[View]
V-->DS
DS-->|11.响应|B
~~~

## SpringMVC执行流程的源代码分析

1.服务器tomcat的启动过程

​	1）tomcat启动会读取web.xml文件。创建DispatcherServlet的实例队形

​		DispatcherServlet是一个Servlet，在创建时会执行init()

