---
layout: post
title: "学习Spring MVC"
description: ""
category: [Java]
tags: []
---
{% include JB/setup %}

SpringMVC 的流程图（网图，侵删）

![SpringMVC流程图](/Resources/pics/springmvc-1.jpg)

通过图片我们可以知道，主要到配置在 `DispatcherServelt`, `HandlerMapping`, `HandlerAdapter`, `ViewResolver`.

通过配置`web.xml` 我们可以将路由配置到 `DispatcherServelt`.
```
<servlet>
	<servlet-name>exam</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
</servlet>
<servlet-mapping>
	<servlet-name>exam</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
```

那么，我们需要创建一个 `exam-servlet.xml` 文件，并在里面配置

```
<mvc:annotation-driven />
<!-- ①：对web包中的所有类进行扫描，以完成Bean创建和自动依赖注入的功能 -->
<context:component-scan base-package="com.web.controller" />

<beans:bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter" />

<!-- 这个类用于Spring MVC视图解析 -->
<beans:bean id="viewResolver"
	class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<beans:property name="prefix" value="/WEB-INF/pages/" />
	<beans:property name="suffix" value=".jsp" />
</beans:bean>
```

最后，在 `Controller` 里面通过注解进行处理业务逻辑并返回试图模型，例如：

```
@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
```

最后，附上能跑到完整DEMO.
待续
