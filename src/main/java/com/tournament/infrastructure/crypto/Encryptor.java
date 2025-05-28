package com.tournament.infrastructure.crypto;

public interface Encryptor {
    String encrypt(String plainText);
    String decrypt(String cipherText);
}