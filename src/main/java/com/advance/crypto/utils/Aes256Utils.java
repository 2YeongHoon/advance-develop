package com.advance.crypto.utils;

import com.advance.common.exception.BaseRuntimeException;
import com.advance.crypto.config.properties.CryptoProperties;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Aes256Utils {

    private static final String AES_ALGORITHM = "AES";
    private static final String PADDING_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int GCM_IV_LENGTH = 16;
    private final CryptoProperties cryptoProperties;

    public String encrypt(String text, String key) {
        try {
            cryptoProperties.getKey();
            byte[] encrypted =
                    getChipher(Cipher.ENCRYPT_MODE, key).doFinal(text.getBytes(StandardCharsets.UTF_8.name()));
            return Base64.getEncoder().encodeToString(encrypted);
        }catch (Exception e){
            throw new BaseRuntimeException("암호화 처리중 예외가 발생했습니다.");
        }
    }

    public String decrypt(String cipherText) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = getChipher(Cipher.DECRYPT_MODE, cryptoProperties.getKey()).doFinal(decodedBytes);
            return new String(decrypted, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            throw new BaseRuntimeException("암호화 처리중 예외가 발생했습니다.");
        }
    }

    private Cipher getChipher(int decryptMode, String key) throws Exception {
        String iv = key.substring(GCM_IV_LENGTH);
        Cipher cipher = Cipher.getInstance(PADDING_ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), AES_ALGORITHM);
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(decryptMode, keySpec, ivParamSpec);
        return cipher;
    }

}
