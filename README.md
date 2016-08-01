## Tools

这是一个工具类的集合, 当前包含如下模块:


### code-gen

这是通过java对象自动生成对应sqlMapper及schema的功能

使用方式:

* 在code-gen模块的pom.xml中添加你包含你要处理的java对象的依赖, 例如:

  ```
  <dependency>
     <groupId>io.terminus.parana</groupId>
     <artifactId>parana-trade-api</artifactId>
     <version>4.1-SNAPSHOT</version>
  </dependency>
  ```

* 用户选择生成方式， datasource, beans(beans 目录下的Class 生成对应的Class)
    ```
        mvn spring-boot:run -Drun.arguments='beans'  指名对应的生成方式
    ```
  
**特殊约定**
1.  Bean serialId 用户手动生成对应的Id
2.  Mapper, 默认第一个字段Id 类型, 处理方式.(id 默认第一个， auto increment), at 时间， 默认now()
    
**敬请期待**
1.  Dao 生成对应的 对应的方法， 用不命令指定的方式。
2.  Service 指定生成的内容。自动注入的方式， 实现固定的逻辑内容。
3.  通过实体类查询的方式。