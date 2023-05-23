# spring-boot-30529

1. Invoke `./gradlew bootRun`
2. Check the message `com.example.demo.EnsureIndexesAspect     : Aspect invoke`
3. Stop it
4. Run `./gradlew nativeCompile && build/native/nativeCompile/demo`
5. No message `com.example.demo.EnsureIndexesAspect     : Aspect invoke`