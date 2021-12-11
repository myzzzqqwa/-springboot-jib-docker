# 使用 Google jib 快速构建springboot项目镜像

> google jib 是Google于18年7月发布的一个针对Java应用的构建镜像的工具(支持Maven和Gradle) ，好处是能够复用构建缓存，能够加快构建，减小传输体积（后文会详细讲解），并且让Java工程师不需要理解Docker相关知识就可以简单构建镜像并且发布到指定registry里（不需要docker build , tag, push）

> 项目地址: https://github.com/hacker-and-painter/springboot-jib-docker

## 运行效果

```
docker pull gaohanghang/springboot-jib-docker

docker run -p 8080:8080 -t gaohanghang/springboot-jib-docker
```

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190715231430.png)

## 快速开始

1. dockerhub中创建Repository

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190715231800.png)

2. 创建springboot项目，添加HelloController.java

```java
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}
``` 

3. pom.xml中添加jib plugin

```
<plugin>
    <groupId>com.google.cloud.tools</groupId>
    <artifactId>jib-maven-plugin</artifactId>
    <version>1.3.0</version>
    <configuration>
        <from>
            <!--base image-->
            <image>openjdk:alpine</image>
        </from>
        <to>
            <!--<image>registry.cn-hangzhou.aliyuncs.com/m65536/jibtest</image>-->
            <!--目标镜像registry地址，为了方便测试，你需要换成自己的地址，如果你的网络不好，可以选用国内加速器，比如阿里云的-->
            <image>registry.hub.docker.com/gaohanghang/springboot-jib-docker</image>
        </to>
    </configuration>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>build</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

4. 将`<image>registry.hub.docker.com/gaohanghang/springboot-jib-docker</image>`中的gaohanghang/springboot-jib-docker修改为你在dockerhub中创建的Repository


5. 使用maven package打包项目，jib会自动构建镜像到dockerhub

![](https://raw.githubusercontent.com/gaohanghang/images/master/img20190715232421.png)

6. 拉取镜像并运行

```
docker pull gaohanghang/springboot-jib-docker

docker run -p 8080:8080 -t gaohanghang/springboot-jib-docker
```

## 参考文章

[Jib构建你的第一个java镜像](https://juejin.im/post/5b4e9c316fb9a04fa01d39d6)
[谷歌开源 Java 镜像构建工具 Jib](https://www.infoq.cn/article/2018/07/google-opensource-Jib)
[加速和简化构建Docker(基于Google jib)]([https://juejin.im/post/5c60c021f265da2dd37bf85b]())
