package com.liteworm.comtroller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @ClassName MyController
 * @Decription
 * 在配置式的springmvc项目中，处理类需要时间接口（Controller）
 * MyController要处理用户的请求，叫做处理器类，也叫做后端控制器（back controller）
 * @AUthor LiteWorm
 * @Date 2020/5/3 14:13
 * @Version 1.0
 **/
public class MyController implements Controller {

    /**
    * @auther LiteWorm
    * @ClassName MyController
    * @FunctionName handleRequest
    * @Description
     * 处理用户的请求，等同于servlet中的doService
    * @Date 14:16 2020/5/3
    * @Param [httpServletRequest, httpServletResponse]
    * @return org.springframework.web.servlet.ModelAndView ：鄙视给用户的处理结果
     * Model：表示数据
     * View：表示视图，在试图中显示Model中的数据
    **/
    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {

        //调用Service处理请求，把结果显示给用户
        ModelAndView mv = new ModelAndView();
        //把数据放入Model
        mv.addObject("msg","hello SpringMvc");
        //指定视图，setViewNme（“视图的完整路径”）
        //框架对视图执行的forward，等同于 request.getRequestDispatcher(/show.jsp").forward();
//        mv.setViewName("/WEB-INF/view//show.jsp");

        /**
        * 在配置了视图解析器后，可以使用逻辑视图名称（视图文件名称）
         * show会通过中央调度器，交给视图解析器，根据“前缀+逻辑视图+后缀”组成完整的视图路径
        **/
        mv.setViewName("show");
        return mv;
    }
}