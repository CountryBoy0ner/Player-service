package com.tournament.infrastructure.crypto;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
@Slf4j
public class AesEncryptor implements Encryptor {

    private final String secretKey;
    private static final String ALGORITHM = "AES";

    public AesEncryptor(@Value("${encryption.secret}") String secretKey) {
        if (secretKey.length() != 16) {
            log.warn("AesEncryptor: Encryption key must be exactly 16 characters for AES-128");
            throw new IllegalArgumentException("Encryption key must be exactly 16 characters for AES-128");
        }
        this.secretKey = secretKey;
    }

    @Override
    public String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            log.info("AesEncryptor: encrypt ={}",Base64.getEncoder().encodeToString(encrypted));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting: " + e.getMessage(), e);
        }
    }

    @Override
    public String decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decoded = Base64.getDecoder().decode(cipherText);
            log.info("AesEncryptor: decrypt ={}",new String(cipher.doFinal(decoded)));
            return new String(cipher.doFinal(decoded));
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting: " + e.getMessage(), e);
        }
    }
}