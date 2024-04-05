package com.advance.crypto.utils;


import com.advance.crypto.enums.CryptoMode;
import com.advance.crypto.utils.Aes256Utils;
import java.io.FileWriter;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Aes256UtilsTest {

    @Autowired
    private Aes256Utils aes256Utils;

    @DisplayName("DB 암 복호화 테스트")
    @ParameterizedTest(name = "{0}")
    @CsvSource({
        "1 , 19930716, 김명일",
        "2 , 19930716, 홍길동",
        "3 , 20000716, 이영훈",
    })
    void dbEncrypt(String id, String birth, String name) throws Exception {

        String encryptBirth = aes256Utils.encrypt(CryptoMode.CLIENT, birth);

        try (FileWriter writer = new FileWriter("test.txt", true)) {
            writer.write(String.valueOf(id) + " ");
            writer.write(String.valueOf(name) + " ");
            writer.write(String.valueOf(birth) + " ");
            writer.write(String.valueOf(encryptBirth) + " ");
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}