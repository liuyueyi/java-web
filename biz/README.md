## biz 工程

### 介绍
> 工具类集合

### 项目结构

#### 1. Tools包

> 主要是常用的工具包，如数字转换，json工具类，缓存工具类等

- `NumTools.java`  封装数字转换的工具类，使用了经典的单例模式，且保证线程安全

- `EmojTools.java` emoj表情转换工具类，将emoj表情和utf-8进行互转，便于db中存，和页面显示的问题

- `CacheTools.java`  `Cache`包中 主要提供了缓存的的工具类，当前提供guava 内存作为缓存 和 redis cache
