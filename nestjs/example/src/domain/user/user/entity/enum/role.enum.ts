// * value 를 int 로 하지 않고 string 으로 지정한 이유
// * 1. DB 조회 > value 가 int 경우 조회가 번거롭거나 comment 필수
// * 2. 개발 편의성 > enum 값 순서 변경, 추가 등에 따른 int 값 변동
// * 3. int 값 비교나 string 값 비교의 성능상 차이가 크지 않음.

// * 권한 레벨
// * 최고관리자 > 관리자 > 팀장 > 직원
export enum Role {
  // * MASTER 권한으로 관리자를 관리한다.
  // * 타부서에 부여하지 않는다.
  최고관리자 = 'MASTER',

  관리자 = 'ADMIN',
  팀장 = 'MANAGER',
  직원 = 'EMPLOYEE',

  // * 일반 고객 권한
  일반고객 = 'CUSTOMER',
}
