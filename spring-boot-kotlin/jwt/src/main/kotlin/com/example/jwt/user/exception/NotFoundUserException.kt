package com.example.jwt.user.exception

import com.example.jwt._common.exception.ApiException
import com.example.jwt._common.exception.ErrorCode

class NotFoundUserException : ApiException(ErrorCode.NOT_FOUND_USER)