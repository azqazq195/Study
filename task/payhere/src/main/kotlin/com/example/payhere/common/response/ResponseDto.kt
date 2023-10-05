package com.example.payhere.common.response

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

class ResponseDto {
    companion object {
        fun <T> of(responseCode: ResponseCode, data: List<T>): ResponseEntity<ListResult<T>> {
            return ResponseEntity(
                ListResult(
                    success = responseCode.code >= 0,
                    code = responseCode.code,
                    msg = responseCode.msg,
                    data = data
                ), responseCode.status
            )
        }

        fun <T> of(responseCode: ResponseCode, data: T): ResponseEntity<SingleResult<T>> {
            return ResponseEntity(
                SingleResult(
                    success = responseCode.code >= 0,
                    code = responseCode.code,
                    msg = responseCode.msg,
                    data = data
                ), responseCode.status
            )
        }

        fun of(responseCode: ResponseCode): ResponseEntity<EmptyResult> {
            return ResponseEntity(
                EmptyResult(
                    success = responseCode.code >= 0,
                    code = responseCode.code,
                    msg = responseCode.msg,
                ), responseCode.status
            )
        }

        fun of(responseCode: ResponseCode, msg: String): ResponseEntity<EmptyResult> {
            return ResponseEntity(
                EmptyResult(
                    success = responseCode.code >= 0,
                    code = responseCode.code,
                    msg = msg,
                ), responseCode.status
            )
        }

        fun writeResponse(responseCode: ResponseCode, response: HttpServletResponse) {
            val objectMapper = ObjectMapper()
            val json = objectMapper.writeValueAsString(
                EmptyResult(
                    success = false,
                    code = responseCode.code,
                    msg = responseCode.msg,
                )
            )

            response.status = responseCode.status.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = "UTF-8"
            response.writer.write(json)
            response.writer.flush()
        }
    }
}