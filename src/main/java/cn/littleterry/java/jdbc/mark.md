 ## 说明
 * jdbc就是java database connection,是java提供连接数据库的一套规范,不管是哪一种类型的数据库(mysql/oracle..),核心都是:对java.sql.Driver 接口的实现,就像在找驱动的时候,Class.forName(),其实就是找Driver的实现类。
 * JDBC API可帮助我们通过数据库驱动程序实现和数据库事务的松散耦合
 
 ## jdbc的连接步骤:
    JDBC Driver————————>Connection————————>StatusMent————————>ResultSet
    
 1. 加载jdbc驱动,找到Driver的实现类
 2. Driver管理器通过数据库配置文件获取连接实例
 3. StatusMent和数据库进行通信,就是执行SQL
 4. 返回执行的结果集
 
 ## PreparedStatement
 当我们触发一个关系数据库执行的查询时，它会经历以下几个步骤。
 
 1. 解析SQL查询
 2. 编译SQL查询
 3. 数据采集路径的规划和优化
 4. 执行优化查询并返回结果数据
 
 JDBC API提供了两种与数据库进行通信的方式 `Statement`和`PreparedStatement`。Statement声明易于使用，但可能导致SQL注入，这是黑客攻击任何应用程序的常见方式。
   
 > PreparedStatement继承了Statement接口

 从StatusMentTest.java的例子可以看出来,在第4个步骤的时候,Statement是执行`stmt.executeQuery(query)`这个方法，它经历了所有四个步骤
 而StatusMentTest.java的例子可以看出来,PreparedStatement是执行`ps.executeQuery()`这个方法，它已经执行好了前面3三个步骤。因此查询的
 执行花费更少的时间和更快的语句。
 
 不仅如此，PreparedStatement提供的addBatch()和executeBatch()方法也提供了对批处理的支持。
 PreparedStatement的主要要点：
 * 自动转义特殊字符,防止SQL注入攻击
 * 允许我们使用参数输入执行动态查询
 * 提供了不同类型的setter方法来设置查询的输入参数
 * 比Statement快,在批处理方法执行多个查询时非常明显
 * 用编写面向对象的思想(占位符,setter)而非拼接字符串来写SQL
 * PreparedStatement返回`FORWARD_ONLY`的ResultSet，所以我们只能向前移动。
 * 与Java Arrays或List不同，PreparedStatement占位符变量的索引从1开始。
 * PreparedStatement的局限之一是我们不能将它用于IN子句中的SQL查询，因为PreparedStatement不允许我们为单个占位符(?)绑定多个值。但是，使用PreparedStatement for IN子句的替代方法很少。
 
