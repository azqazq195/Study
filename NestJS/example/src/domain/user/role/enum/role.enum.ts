// * value 를 int 로 하지 않고 string 으로 지정한 이유
// * 1. DB 조회 > value 가 int 경우 조회가 번거롭거나 comment 필수
// * 2. 개발 편의성 > enum 값 순서 변경, 추가 등에 따른 int 값 변동
// * 3. int 값 비교나 string 값 비교의 성능상 차이가 크지 않음.
export enum Role {
  // * MASTER 권한
  최고관리자 = 'MASTER_ADMIN',

  // * 오늘의 꽃 권한
  오늘의꽃_관리자 = 'OKKOT_ADMIN',
  오늘의꽃_임직원 = 'OKKOT_EMPLOYEE',

  // * 오프라인 매장 권한
  오프라인매장_관리자 = 'OFFLINE_ADMIN',
  오프라인매장_임직원 = 'OFFLINE_EMPLOYEE',

  // * 외부업체 고객 권한
  외부업체_관리자 = 'INDUSTRY_ADMIN',
  외부업체_임직원 = 'INDUSTRY_EMPLOYEE',

  // * 일반 고객 권한
  일반고객 = 'CUSTOMER',
}
