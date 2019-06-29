# HSHE 在线评测系统

主要使用 Spring Boot 开发 Web 前后台，评测模块和查重模块。评测模块内核使用 C++ 开发。使用垂直型的应用架构和分布式集群部署，服务间通过消息中间件通信。使用了前后端分离的开发模式，前端使用 Vue 框架开发 SPA 应用，后端 Web 模块全 RESTful API ，使用 Redis 做缓存，提供了良好的使用体验。部署使用Docker 容器，保证系统的安全性与可靠性。

## 运行截图

#### 用户登录

学生和教师使用同一登录入口，教师登录后会出现后台管理按钮。

![](D:\Projects\JavaProject\hshe\screenshots\test-login.JPG)

#### 首页

展示系统内作业题目信息。

![](D:\Projects\JavaProject\hshe\screenshots\test-index.png)

#### 题目界面

展示题目详情，输入和输出。由于评测服务器目前只安装了C编译器和JDK，暂时不能选择其他语言。

![](D:\Projects\JavaProject\hshe\screenshots\test-problem.png)

#### 提交结果

检查提交结果，同时显示资源占用情况。

![](D:\Projects\JavaProject\hshe\screenshots\test-subs.JPG)

#### 管理后台 

教师对班级、学生、作业的增删改管理。系统概览还可以查看目前各个评测机的运行状态。

**★ 加入了代码相似度分析（仅教师可见），比较并显示同一题目的某同学代码和其他同学代码的最高相似度，并把互相相似的代码作业标记为一组雷同作业。以帮助教师方便判定存在的作业抄袭情况。**

![](D:\Projects\JavaProject\hshe\screenshots\test-anysics.PNG)

![](D:\Projects\JavaProject\hshe\screenshots\test-addp.PNG)

![](D:\Projects\JavaProject\hshe\screenshots\test-groups.PNG)

## 简易架构图

![](D:\Projects\JavaProject\hshe\screenshots\arch.png)

## 说明

项目目录中，`core` 是指后端API服务（命名不规范请见谅）；`judge` 是指后端评测服务，收到来自前端的代码提交调用后，通知 `judge` 模块对代码编译、运行、判定结果；`sim` 是指后端查重服务，其实就是对开源查重工具SIM的调用封装，若 `judge` 模块判定代码通过，会通知 `sim` 模块对代码进行查重，并更新结果。`static` 目录是前端项目，需要单独发布。

`judge` 和 `sim` 是内部服务，不提供外部调用入口，总体的调用顺序是 `core` -> `judge` -> `sim` ，但为了避免同步调用，所以才使用了 MQ 来做服务通信，同时启动多个 `judge` 和 `sim` 来消费，很大程度的提升了系统的性能和稳定性。

服务端运行来自用户的代码非常危险，因此服务运行于容器，保证了评测服务的安全。