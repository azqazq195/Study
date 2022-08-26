# Filter, Interceptor, AOP

## 요약

| Filter                                                                                                | Interceptor                                                                          | AOP           |
|-------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|---------------|
| 웹 컨테이너                                                                                                | 스프링 컨테이너                                                                             | 스프링 컨테이너      |
| - 공통된 보안 및 인증/인가 관련 작업</br>- 모든 요청에 대한 로깅 또는 검사</br>- 이미지/데이터 압축 및 문자열 인코딩</br>- Spring 과 분리되어야 하는 기능 | - 세부적인 보안 및 인증/인가 공통 작업</br>- API 호출에 대한 로깅 또는 검사</br>- Controller 로 넘겨주는 정보(데이터) 가공 | Service 로직 로깅 |

## Filter

### [LoggingFilter](src/main/java/com/example/filterinterceptoraop/config/filter/LoggingFilter.java)

- 설정
    - [WebMvcConfig](src/main/java/com/example/filterinterceptoraop/config/WebMvcConfig.java) 에서 loggingFilter 를 Bean 으로
      등록.
- init
    - Filter 가 등록될 때 호출된다.
- doFilter
    - 요청 내용을 처리한다.
- destroy
    - Filter 가 사라질 때 호출된다.
    - Spring 이 종료될 때.

### [ServletWrappingFilter](src/main/java/com/example/filterinterceptoraop/config/filter/ServletWrappingFilter.java)

- OncePerRequestFilter
    - 해당 클래스를 상속받아 한번의 요청에 한번만 수행함을 보장한다.
- ContentCachingRequestWrapper
    - HttpServletRequest 의 InputStream 의 값에 접근(로깅 등)은 한번 밖에 할 수 없다.
    - spring-web 에서 제공하는 ContentCachingRequestWrapper 으로 request 를 Wrapping 하여 여러번 접근 가능하도록 만들어 준다.
    - `wrappingResponse.copyBodyToResponse();` 는 필수로 작성. (response 에 값이 담기지 않음.)

> logging은 interceptor로 구현하였다. 이 코드는 간단하게 구현하느라 validation들을 모두 제외하였다. 직접 구현할 때는 wrapping이 안됐을 경우나 caching된 content가 없을
> 경우 등, 검증해야 할 부분이 많다.caching된 content는 byte array로 들고 있기 때문에 objectMapper를 이용해 JsonNode로 읽어주고 로깅했다.

## Interceptor

### [LoggingInterceptor](src/main/java/com/example/filterinterceptoraop/config/interceptor/LoggingInterceptor.java)

- 설정
    - [WebMvcConfig](src/main/java/com/example/filterinterceptoraop/config/WebMvcConfig.java) 에서 addInterceptors 로
      Interceptor 등록
    - `.addPathPatterns("/**");` Interceptor 할 요청 url
    - `.excludePathPatterns("/**");`Interceptor 제외할 요청 url
- `preHandle`
    - 컨트롤러의 핸들러 메서드를 실행하기 전에 호출
    - 핸들러 메서드가 호출되지 않게 하고 싶을 때 메서드 반환값으로 `false`
- `postHandle`
    - 컨트롤러의 핸들러 메서드가 정상적으로 종료된 후에 호출
    - 핸들러 메서드에서 예와가 발생하면 호출 안됨
- `afterCompletion`
    - 컨트롤러의 핸들러 메서드의 처리가 종료된 후에 호출
    - 예외가 발생해도 호출

## AOP

### [ServiceAspect](src/main/java/com/example/filterinterceptoraop/config/aop/ServiceAspect.java)

- `@Before`
    - 어드바이스가 주입될 타겟이 **호출되기 전** 어드바이스 내용이 주입, 수행
- `@After`
    - 타겟이 수행한 내용 **결과에 관계없이 수행이 끝나면** 어드바이스 내용이 주입, 수행
- `@AfterReturning`
    - 타겟에 대한 내용이 **예외없이 성공적으로 수행되면 결과값을 반환 후** 어드바이스 내용이 주입, 수행
- `@AfterThrowing`
    - 타겟에 대한 내용이 **예외를 던지게 되면** 어드바이스 내용이 주입, 수행
- `@Around`
    - 타겟에 대한 내용이 **수행 전, 후**를 감싸 어드바이스 내용이 주입, 수행

**`@Pointcut` 문법:**

```java
public class Aspect {
    // 1. 매개변수 없고 리턴타입 String, public 인 hello 메서드에 적용 가능하다. (여러 클래스에서도 해당 조건을 만족하면 hello()메서드가 전부 적용된다.)
    @Pointcut("execution(public String hello())")
    public void pointcut() {
    }

    // 2. 매개변수가 0개 이상인 hell 로 시작하는 메서드)
    @Pointcut("execution(* hell*(..))")
    public void pointcut() {
    }

    // 3. aopController 안의 매개변수가 1개인 모든 메서드
    @Pointcut("execution(* com.example.springmvc.restController.aopController.*(*))")
    public void pointcut() {
    }

    // 4. com.example.springmvc 패키지의 모든 클래스의 적용 (서브패키지는 미포함)
    @Pointcut("execution(* com.example.springmvc.*.*(..))")
    public void pointcut() {
    }

    // 5. com.example.springmvc 패키지의 모든 클래스의 적용 (서브패키지도 포함)
    @Pointcut("execution(* com.example.springmvc..*.*(..))")
    public void pointcut() {
    }
}

```

```shell

...
[LOGGING FILTER] init
Tomcat started on port(s): 8080 (http) with context path ''
...

# Post Method
[LOGGING FILTER] before doFilter
[WRAPPING FILTER] before servlet wrapping filter
[INTERCEPTOR] preHandle [POST] /auth/signup, param: 'name=seongha&age=2'
------------- request body:
------------- response body:           
[AOP] around before 'signup' service
[AOP] before 'signup' service
[AOP] after returning 'signup' service
[AOP] after 'signup' service
[AOP] around after 'signup' service
[INTERCEPTOR] postHandle [POST] /auth/signup, param: 'name=seongha&age=2'
------------- request body: {"name":"qwe","email":"qwe","password":"qwe"}
------------- response body: {"message":"회원가입 완료"}
[INTERCEPTOR] afterCompletion [POST] /auth/signup, param: 'name=seongha&age=2'
------------- request body: {"name":"qwe","email":"qwe","password":"qwe"}
------------- response body: {"message":"회원가입 완료"}
[WRAPPING FILTER] after servlet wrapping filter
[WRAPPING FILTER] done servlet wrapping filter
[LOGGING FILTER] after doFilter

# Get Method
[LOGGING FILTER] before doFilter
[WRAPPING FILTER] before servlet wrapping filter
[INTERCEPTOR] preHandle [GET] /auth/me, param: 'name=seongha&age=2'
------------- request body:
------------- response body:
[AOP] around before 'me' service
[AOP] before 'me' service
[AOP] after returning 'me' service
[AOP] after 'me' service
[AOP] around after 'me' service
[INTERCEPTOR] postHandle [GET] /auth/me, param: 'name=seongha&age=2'
------------- request body:
------------- response body: {"id":2,"name":"seongha","email":"azqazq195@gmail.com","password":"qwe"}
[INTERCEPTOR] afterCompletion [GET] /auth/me, param: 'name=seongha&age=2'
------------- request body:
------------- response body: {"id":2,"name":"seongha","email":"azqazq195@gmail.com","password":"qwe"}
[WRAPPING FILTER] after servlet wrapping filter
[WRAPPING FILTER] done servlet wrapping filter
[LOGGING FILTER] after doFilter

# RuntimeException 발생시 postHandle 은 호출되지 않았다.
# AOP around after 가 수행되지 않았다.
# AOP after 와 after throwing 만 실행
# INTERCEPTOR 를 벗어나면서 Exception 이 보인다.
[LOGGING FILTER] before doFilter
[WRAPPING FILTER] before servlet wrapping filter
[INTERCEPTOR] preHandle [GET] /auth/error, param: 'null'
------------- request body:
------------- response body:
[AOP] around before 'error' service
[AOP] before 'error' service
[AOP] after throwing 'error' service
[AOP] after 'error' service
[INTERCEPTOR] afterCompletion [GET] /auth/error, param: 'null'
------------- request body:
------------- response body:
Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.RuntimeException: RuntimeException] with root cause
java.lang.RuntimeException: RuntimeException
...
```
