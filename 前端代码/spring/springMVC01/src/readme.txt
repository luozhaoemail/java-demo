使用springmvc拦截器实现登陆验证

1. 把页面放入到web-inf中.
	1.1 放入到web-inf中后必须通过控制器转发到页面.
	1.2 springmvc拦截器拦截的是控制器,不能拦截jsp
	
2. 通过拦截器拦截全部控制器,需要在拦截器内部放行login控制器



spring-mvc.xml 和 application-context.xml的区别
application-context.xml是全局的，应用于多个serverlet，配合listener一起使用，web.xml中配置如下：
<!-- 配置监听器 -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>

spring-mvc.xml 是spring mvc的配置，web.xml中配置如下：
<!--配置springmvc DispatcherServlet-->
<servlet>
  <servlet-name>springMVC</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:config/spring-mvc.xml</param-value>
  </init-param>
  <load-on-startup>1</load-on-startup>
  <async-supported>true</async-supported>
</servlet>
application-context.xml这个一般是采用非spring mvc架构，用来加载Application Context。
如果直接采用SpringMVC，只需要把所有相关配置放到spring-mvc.xml中就好，一般spring mvc项目用不到多个serverlet