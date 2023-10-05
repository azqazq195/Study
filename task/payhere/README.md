# Rest Docs

http://localhost:8080/docs/index.html

# Docker Compose

해당 프로젝트 image를 docker repository에 올려두었습니다. `docker-compose up`으로 실행 가능합니다.
`./docker/docker-compose.yml`
`port:8080`

### 요구사항

- [x] 고객은 이메일과 비밀번호 입력을 통해서 회원 가입을 할 수 있습니다.
    - http://localhost:8080/docs/index.html#Auth-sign-up
- [x] 고객은 회원 가입이후, 로그인과 로그아웃을 할 수 있습니다.
    - http://localhost:8080/docs/index.html#Auth-sign-in
    - http://localhost:8080/docs/index.html#Auth-sign-out
- [x] 고객은 로그인 이후 가계부 관련 아래의 행동을 할 수 있습니다.
    - [x] 가계부에 오늘 사용한 돈의 금액과 관련된 메모를 남길 수 있습니다.
        - http://localhost:8080/docs/index.html#Account-Book-create
    - [x] 가계부에서 수정을 원하는 내역은 금액과 메모를 수정 할 수 있습니다.
        - http://localhost:8080/docs/index.html#Account-Book-update
    - [x] 가계부에서 삭제를 원하는 내역은 삭제 할 수 있습니다.
        - http://localhost:8080/docs/index.html#Account-Book-delete-one
    - [x] 삭제한 내역은 언제든지 다시 복구 할 수 있어야 한다.
        - **삭제된 내역 전부를 복구합니다.**
        - http://localhost:8080/docs/index.html#Account-Book-restore-all
    - [x] 가계부에서 이제까지 기록한 가계부 리스트를 볼 수 있습니다.
        - http://localhost:8080/docs/index.html#Account-Book-find-all
    - [x] 가계부에서 상세한 세부 내역을 볼 수 있습니다.
        - http://localhost:8080/docs/index.html#Account-Book-find-one

- [x] 로그인하지 않은 고객은 가계부 내역에 대한 접근 제한 처리가 되어야 합니다.
    - 로그인 하지 않은 고객은 가계부 서비스를 이용할 수 없습니다.
    - 로그인한 고객이라도 자신의 가계부만 사용할 수 있습니다.

# 구현

---

- [x] 언어에 상관없이 Docker를 기반으로 서버를 실행 할 수 있도록 작성해주세요.
    - `./docker/docker-compose.yml`
- [x] DB 관련 테이블에 대한 DDL 파일을 소스 디렉토리 안에 넣어주세요.
    - `./src/main/resources/schema.sql`  
- [x] 가능하다면 테스트 케이스를 작성해주세요.
    - 가계부 (Account Book) 관련 코드 전체
    - RestDocs 작성을 위한 Controller (Auth, Account Book) 테스트
- [x] 별도의 요구사항이 없는 것은 지원자가 판단해서 개발합니다.
    - 접근 제한 처리 및 삭제내역 복구 대상
- [x] 토큰을 발행해서 인증을 제어하는 방식으로 구현해주세요
    - jwt > access token 만 활용하였습니다.

# 기타사항

---

- [x] 해당 과제는 타인의 도움 없이 지원자가 직접 작성해야 합니다.
- [x] 해당 과제를 직접적으로 타인에게 유출하는 행위를 금합니다.

  (GitHub Repository를 Public으로 설정한 후 주소를 공유해주시는 것은 무방합니다.)
