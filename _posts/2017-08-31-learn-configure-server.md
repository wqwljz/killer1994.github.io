---
layout: post
title: "在服务器上配置javaweb项目"
description: ""
category: 
tags: []
---
{% include JB/setup %}

用的是腾讯个人7天免费云主机

注册，实名认证就不多讲了，讲一下服务器的配置流程。

给的是 centos7.3/64bit/1core/1G 免费的还要啥自行车。

[使用密钥登录有公网IP的Linux云服务器](https://www.qcloud.com/document/product/213/2036)主要参考1.1，1.2。

配置好了，就可以用Putty远程登录自己的服务器了。

接下来就是在服务器上安装java环境.

### 1.更换yum源 

[点击打开链接](http://mirrors.aliyun.com/help/centos)

### 2.安装jdk1.7 

安装命令：`yum -y install  java-1.7.0-openjdk*`;

测试：`javac -version`

### 3.安装tomcat7 

安装命令：`sudo yum install tomcat`; 

安装管理包（就是webapp下面的东西）：`sudo yum install tomcat-webapps tomcat-admin-webapps`; 

安装在线文档：`sudo yum install tomcat-docs-webapp tomcat-javadoc`;

启动/关闭Tomcat:`sudo systemctl start/stop tomcat`; 

启动服务（每次开机启动）:`sudo systemctl enable tomcat` ;

测试:访问 http://主机IP:8080

### 4.tomcat绑定80端口

利用firewalld在数据包路由之前进行端口转发，把所有发往80的tcp包转发到8080。

首先要确保firewalld开启

查看firewalld状态：`systemctl status firewalld`

如果当前是dead状态，即防火墙未开启。

开启firewalld：`systemctl start firewalld`

再次通过systemctl status firewalld查看firewalld状态，显示running即已开启了。

然后在/etc/firewalld/services/目录下新建一个名为tomcat.xml。内容如下：

{% highlight xml linenos %} 
<?xml version="1.0" encoding="utf-8"?>  
<service>  
  <short>Tomcat Webserver</short>  
  <description>HTTPS is a modified HTTP used to serve Web pages when security is important. Examples are sites that require logins like stores or web mail. This option is not required for viewing pages locally or developing Web pages. You need the httpd package installed for this option to be useful.</description>  
  <port protocol="tcp" port="8080"/>  
</service>  
{% endhighlight %}

然后把上面的服务加入防火墙规则中
```
firewall-cmd --reload
firewall-cmd --add-service=tomcat
firewall-cmd --permanent --add-service=tomcat
```

把所有发往80的tcp包转发到8080
```
firewall-cmd --add-forward-port=port=80:proto=tcp:toport=8080
firewall-cmd --permanent --add-forward-port=port=80:proto=tcp:toport=8080
```
测试:访问 http://主机IP:8080 以及 http://主机IP

### 5.部署javaweb项目

这里，我通过WinSCP，将windows上的文件上传linux服务器上

首先安装 WinSCP，如果没有putty.exe，则加入一开始的putty。

WinSCP目录如下

![winscp目录](/Resources/pics/WinSCP.png)

PuTTY目录如下

![putty目录](/Resources/pics/PuTTY.png)

WinSCP通过PuTTY导入站点

![导入站点1](/Resources/pics/winscp1.png)

![导入站点2](/Resources/pics/winscp3.png)

点击登录，输入用户名（root），就连接上服务器了。

![部署war包](/Resources/pics/upload_war.png)

测试:访问 http://主机IP/项目名/

### 参考资料

[腾讯云Centos6.6搭建javaweb环境:mysql5.1+jdk1.7+tomcat6完整过程](http://blog.csdn.net/a445849497/article/details/51436051)

[centos7中使用yum安装tomcat](http://blog.csdn.net/qq_27252133/article/details/64443109)

[开启firewalld](https://jingyan.baidu.com/article/5552ef47f509bd518ffbc933.html)

[80端口转发至8080端口](http://blog.csdn.net/smstong/article/details/39958675)
