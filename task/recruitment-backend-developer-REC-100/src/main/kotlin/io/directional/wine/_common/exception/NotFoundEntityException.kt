package io.directional.wine._common.exception

open class NotFoundEntityException(clazz: Class<*>) : ApiException("존재하지 않는 ${clazz.simpleName} 입니다.")