# 总结

> 1、使用热部署

有两种热部署的使用方式，第一种是使用`springboot`官方提供的`starter`，这种方式并不是真正意义上的热部署，而是使用的一种更快的启动方式，**本质上是重启的方式**，在项目中使用这种方式只需要在`pom`依赖文件中加入`spring-boot-devtools`即可：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional> <!-- 可选 -->
</dependency>
```

然后打开自动编译，设置方式，点击 IDEA 的菜单 `IntelliJ IDEA` -> `Preference...`，然后选择 `Compiler` 选项卡，将 `Build project automatically` 勾选上。

另外，将 `compiler.automake.allow.when.app.running` 勾选上，原因是，自动编译在 **Running 运行中**默认是不生效的，通过勾选上 `compiler.automake.allow.when.app.running`，允许在 **Running 运行中**也生效。

第二种方式就是`IDEA`热部署了，通过插件的方式，IDEA 提供了 [HotSwap](https://www.jetbrains.com/help/idea/debugger-hotswap.html) 插件，可以实现真正的热部署。使用也非常方便，安装完插件以后，在我们修改代码之后，只需要重新变一下代码，即点击 IDEA 的菜单 `Build` -> `Build Project`，**手动**进行编译，此时，我们在 IDEA 中可以看修改的类被重载的提示。

