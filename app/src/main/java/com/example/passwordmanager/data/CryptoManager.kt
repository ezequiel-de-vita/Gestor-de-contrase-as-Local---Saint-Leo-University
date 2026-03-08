package com.example.passwordmanager

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

object CryptoManager {

    private const val KEY_NAME = "password_key"

    fun generateKey() {

        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            "AndroidKeyStore"
        )

        val parameterSpec = KeyGenParameterSpec.Builder(
            KEY_NAME,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()

        keyGenerator.init(parameterSpec)
        keyGenerator.generateKey()
    }

    fun encrypt(text: String): String {

        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)

        val secretKey = keyStore.getKey(KEY_NAME, null) as SecretKey

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val iv = cipher.iv
        val encrypted = cipher.doFinal(text.toByteArray())

        val combined = iv + encrypted

        return Base64.encodeToString(combined, Base64.DEFAULT)
    }

    fun decrypt(data: String): String {

        val combined = Base64.decode(data, Base64.DEFAULT)

        val iv = combined.copyOfRange(0, 12)
        val encrypted = combined.copyOfRange(12, combined.size)

        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)

        val secretKey = keyStore.getKey(KEY_NAME, null) as SecretKey

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")

        cipher.init(
            Cipher.DECRYPT_MODE,
            secretKey,
            GCMParameterSpec(128, iv)
        )

        val decrypted = cipher.doFinal(encrypted)

        return String(decrypted)
    }
}