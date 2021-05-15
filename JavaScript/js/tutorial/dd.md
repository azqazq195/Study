문제 보기
매장 관리 페이지
여러분은 매장 관리 페이지 개발 업무를 맡았습니다. 보안을 위해서 로그인 페이지를 붙이고 API 호출 시 인증 정보를 추가해서 호출하도록 만들어주세요. 그리고 정렬과 검색 기능이 가능한 매장 목록 페이지를 만들어주세요.

수행 기술
Vanilla JavaScript(ES6+) 또는 TypeScript를 활용해서 문제를 풀어주세요.
설치되어있는 모듈(node_modules) 외에 다른 외부 라이브러리는 사용하지 않도록 합니다.
TypeScript로 진행하는 방법:

VSCode에서 터미널을 띄웁니다.
다음 명령어를 입력합니다.
# 브랜치 변경
$ git checkout typescript
만약 파일을 수정해서 브랜치 변경이 안될 경우 git stash 또는 git reset HEAD --hard 명령어를 입력 후 진행하시면 됩니다.

만약, 다시 Vanilla JavaScript(ES6+)로 진행하려면, $ git checkout master 명령어를 실행합니다.

요구사항
아래 요구사항 중 (필수) 라고 표기된 부분은 반드시 구현해야 합니다.

주의: lib/router.js 파일은 수정하지 마세요.

공통 요구사항
코드의 가독성과 재사용성을 고려해서 구현하세요.
가급적 각 스크립트 파일은 컴포넌트 단위로 재사용이 가능한 단위로 나누어주세요.
API 호출 시 데이터가 불려지고 있음을 알리는 UI 처리가 필요합니다.
API 예외를 UI적으로 사용자에게 알리는 UI 처리가 필요합니다.
컴포넌트를 비동기로 불러오면 가산점이 있습니다.
css/style.css를 참고해서 화면을 구성하세요.
$router.push를 이용해서 페이지를 이동해 주세요.
요구사항 1. 로그인 페이지
사용자로부터 아이디와 패스워드를 입력받고 로그인 API를 통해 인증 정보를 획득하는 로그인 페이지를 구현해 주세요.

로그인 정보는 다음과 같습니다.

EMAIL: woowa@tech.camp

PASSWORD: Woow@qlalfqjsgh

LoginPage.png

(필수) 로그인 API를 통해서 인증 토큰을 받습니다.
(필수) 인증 토큰을 인증 해더에 추가하고 사용자 정보 API를 호출해서 사용자 정보를 가져오면 로그인 기능이 완료됩니다.
로그인이 완료되면 매장 목록 화면으로 이동해 주세요.
인증 토큰을 브라우저에 저장해서 화면을 다시 불러와도 정보가 없어지지 않도록 합니다.
로그인 버튼 클릭 또는 이메일, 비밀번호 입력란에서 엔터를 눌러서 로그인 기능이 동작하도록 합니다.
입력값을 검증해 주세요. 그리고 사용자 편의를 위해 검증 실패 원인을 사용자에게 알려주고 입력란에 포커스를 주세요.
요구사항 2. 매장 목록 페이지
정렬과 검색이 가능한 매장 목록 페이지를 구현해 주세요.

StorePage.png

(필수) 페이지 처음 로딩 또는 검색 버튼 클릭 시 매장 목록 API를 통해 매장 목록을 조회합니다.
(필수) 입점일(epoch time)을 읽을 수 있는 날짜 형식(YYYY-MM-DD)으로 표시해주세요.
(필수) 우측 상단에 사용자 이름과 로그아웃 버튼을 표시해주세요.
로그아웃 버튼 클릭 시 사용자에게 로그아웃 여부를 confirm 후 로그인 페이지로 이동해주세요.
테이블 해더 클릭 시 오름차순 또는 내림차순으로 정렬이 되어 매장 목록이 정렬이 되어 표시해주세요.
검색어를 입력 후 엔터 또는 검색 버튼 클릭 시 매장명 또는 주소, 전화번호에 키워드가 포함되어 있는 매장을 필터링해서 테이블에 표시해주세요.
검색 후 매장 목록이 없을 경우 "찾으시는 매장이 없습니다."를 테이블에 표시해주세요.
마지막 정렬과 검색어를 브라우저에 저장해서 화면을 다시 불러와도 매장 목록이 그대로 표시되도록 합니다.
API 명세
API endpoint: https://osis08bx4c.execute-api.ap-northeast-2.amazonaws.com/dev

API Errors
code	status
400	Bad Request
403	Forbidden
{
statusCode: number,
message: string
}
로그인 API
method	path	header
POST	/api/login	- Content-type: application/json
request:

{
email: string,
password: string
}
response:

{
token: string
}
사용자 정보 API
method	path	header
GET	/api/me	- Authorization: token
response:

{
email: string,
name: string
}
매장 목록 API
method	path	header
GET	/api/stores	- Authorization: token
response:

[
{
id: number,
name: string,
phone: string,
address: string,
isActivated: boolean,
category: string,
createdAt: number, // epoch time
},
...
]
