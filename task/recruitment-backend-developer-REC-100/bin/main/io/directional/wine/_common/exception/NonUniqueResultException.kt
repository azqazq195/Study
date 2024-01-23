package io.directional.wine._common.exception

class NonUniqueResultException(clazz: Class<*>) : ApiException("${clazz.simpleName} 테이블의 조회 결과가 하나가 아닙니다.")

