package com.example.jwt.auth.exception

import com.example.jwt._common.exception.ApiException
import com.example.jwt._common.exception.ErrorCode

class NotFoundRefreshTokenException : ApiException(ErrorCode.NOT_FOUND_REFRESH_TOKEN)