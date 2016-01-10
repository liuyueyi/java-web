## About
> 本工程下主要是学习过程中遇到的各种技术的测试类测试集中营
>
> 测试过后，有用的东西，会集成到java-web项目中
>

### 1. 注解

- 注解的使用，主要可以参考 `com.mogu.hui.study.annotation.CacheAnnotation` `com.mogu.hui.study.AnnotationTest`

- 首先是申明一个注解类，指定生命周期(Retention）,作用域(Traget)

- 实际的使用过程是在对象、类或其他的地方上面加 @CacheAnnootation

- 处理注解： 通过反射获取注解

    ```
    if(cell.getClass().isAnnotationPresent(CacheAnnotation.class)) {
        CacheAnnotation annotation = (CacheAnnotation) cell.getClass().getAnnotation(CacheAnnotation.class);
        ...
    }
    ```

### 2. 切面 AOP    这个和上面的注解配合起来也非常赞，如权限控制（在执行某些方法前，拦截住，判断一下访问权限是否给通过）
