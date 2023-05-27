TODO

- [x] refresh token 구현
- [x] refresh token 을 이용한 access token 재발급 구현
- [x] JwtStorage Cache Memory 로 구현
- [x] 코드 정리
- [x] token expired time 수정
- [x] 권한 구현
- [ ] 폴더 정리
- [ ] test 코드 작성
- [ ] rest docs 작성
- [ ] redis restTemplate 분리해서 얻는 이점은? (blacklist)
- [ ] readme 정리
- [ ] refresh token redis repository 로 변경

# Exception Handler

Error Code 및 ApiException 을 정의해놓고 ApiException 을 상속받아 NotFoundUserException 을 새로 만드는 이유는?

- NotFoundUserException 발생시 로직을 분리하여 확장할 수 있다.

# JWT

Refresh Token의 한계
Access Token을 즉시 차단할 방법의 부재
아무리 Refresh Token이 Access Token의 유효기간을 짧게 만들어 줄 수 있다고 하더라도, 탈취된 Access Token이 유효한 그 짧은 시간 동안에 악용될 수 있다는 위험성이 존재한다.

Refresh Token 그 자체를 탈취 당할 가능성
해커에게 Refresh Token 자체를 탈취 당하면 해커는 마음껏 Access Token을 발행할 수 있다. 서버 DB에서 Refresh Token을 저장해 직접 추적하는 방법을 사용하면 조금이나마 피해를 줄일 수
있겠지만, 피해가 확인되기 전까진 탈취 여부를 알 방법이 없다.

RTR을 사용한다면 Refresh Token을 1회 사용하고 버리게 되어 더 안전하게 사용할 수 있지만, 사용하지 않은 Refresh Token을 탈취당하면 해커는 1회 한정으로 Access Token을 발급받을 수
있다.

즉, 이러나 저러나 Refresh Token을 탈취 당할 위험성이 존재한다. 따라서 클라이언트는 XSS, CSRF 공격으로부터 Refresh Token이 탈취되지 않도록 안전하게 보관해야한다.

참조:

- https://apim.docs.wso2.com/en/3.2.0/learn/api-security/oauth2/grant-types/refresh-token-grant/
- https://pragmaticwebsecurity.com/articles/oauthoidc/refresh-token-protection-implications.html

# 인가

인가 검사에는 크게 두가지 방법이 있다.

1. WebSecurityConfig를 통한 url 별 검사
    - url을 통한 검사에서는 아직 spring container에 진입하지 않았으므로 accessDeniedHandler를 통해 Exception을 핸들링한다.
2. MethodSecurityConfig를 통한 Method 별 검사
    - annotation을 통한 인가 검사에서는 spring container에 진입한 이후 이므로 controller advice를 통해 AccessDeniedException을 핸들링한다.

## WebSecurityConfig

```kotlin
@Bean
@Throws(Exception::class)
fun filterChain(http: HttpSecurity): SecurityFilterChain {
    return http
        .csrf().disable()
        .formLogin().disable()
        .rememberMe().disable()
        .logout().disable()
        .httpBasic().disable()

        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and()
        .authorizeHttpRequests()
        .requestMatchers("/auth/sign-in").permitAll()
        .requestMatchers("/auth/sign-up").permitAll()
        .requestMatchers("/auth/refresh").permitAll()
        .requestMatchers("/hello/**").hasAnyRole("USER", "ADMIN")
        .anyRequest().authenticated()

        .and()
        .exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPointImpl) // 401 에러 핸들링
        .accessDeniedHandler(accessDeniedHandlerImpl) // 403 에러 핸들링

        .and()
        .addFilterBefore(
            JwtAuthenticationFilter(tokenProvider),
            UsernamePasswordAuthenticationFilter::class.java
        )
        .build()
}
```

## MethodSecurityConfig

@Secured는 표현식 사용할 수 없고 @PreAuthroize, @PostAuthorize는 표현식 사용을 사용하여 디테일한 설정이 가능
해당 annotation은 controller class 혹은 method에 붙여 사용한다.

표현식 종류:

- hasRole([role]) : 현재 사용자의 권한이 파라미터의 권한과 동일한 경우 true
- hasAnyRole([role1,role2]) : 현재 사용자의 권한디 파라미터의 권한 중 일치하는 것이 있는 경우 true
- principal : 사용자를 증명하는 주요객체(User)를 직접 접근할 수 있다.
- authentication : SecurityContext에 있는 authentication 객체에 접근 할 수 있다.
- permitAll : 모든 접근 허용
- denyAll : 모든 접근 비허용
- isAnonymous() : 현재 사용자가 익명(비로그인)인 상태인 경우 true
- isRememberMe() : 현재 사용자가 RememberMe 사용자라면 true
- isAuthenticated() : 현재 사용자가 익명이 아니라면 (로그인 상태라면) true
- isFullyAuthenticated() : 현재 사용자가 익명이거나 RememberMe 사용자가 아니라면 true

# Rest Docs

### 이슈

- index.adoc에서 작성하지 않고 파일을 분리하여 include 하였을 경우, bootJar 로 배포시 정상 표기되나 intellij 에서 Spring Boot를 실행했을 경우 include 된 adoc 파일
  경로가 달라져 불러오지 못함.

참조:

https://narusas.github.io/2018/03/21/Asciidoc-basic.html

