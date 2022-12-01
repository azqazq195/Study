package com.example.converter.utils

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class AES256Util(
    @Value("\${config.aes256.algorithm}") private var alg: String,
    @Value("\${config.aes256.key}") private var key: String
) {
    @Throws(Exception::class)
    fun encrypt(text: String): String {
        val iv = key.substring(0, 16)
        val cipher = Cipher.getInstance(alg)
        val keySpec = SecretKeySpec(key.toByteArray(), "AES")
        val ivParamSpec = IvParameterSpec(iv.toByteArray())
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec)
        val encrypted = cipher.doFinal(text.toByteArray(StandardCharsets.UTF_8))
        return Base64.getEncoder().encodeToString(encrypted)
    }

    @Throws(Exception::class)
    fun decrypt(cipherText: String?): String {
        val iv = key.substring(0, 16)
        val cipher = Cipher.getInstance(alg)
        val keySpec = SecretKeySpec(key.toByteArray(), "AES")
        val ivParamSpec = IvParameterSpec(iv.toByteArray())
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec)
        val decodedBytes = Base64.getDecoder().decode(cipherText)
        val decrypted = cipher.doFinal(decodedBytes)
        return String(decrypted, StandardCharsets.UTF_8)
    }
}