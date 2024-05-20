package com.advance.mail.service;

import com.advance.mail.enums.TemplateType;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void send(MultipartFile file) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            helper.setSubject("메일 전송 테스트");
            helper.setTo("mail addres");
            helper.setText(innerHtml(), true);
            helper.addAttachment("테스트 파일", file);
            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String innerHtml() {
        return this.templateEngine.process(TemplateType.MAIL_TEMPLATE.fileName(), context());
    }

    private Context context() {
        final String name = "lee";
        final String mobile = "01012345678";
        final String address = "기흥구";

        Context context = new Context();
        HashMap<String, Object> map = new LinkedHashMap<>();
        map.put("name", name);
        map.put("mobile", mobile);
        map.put("address", address);

        context.setVariables(map);
        return context;
    }

}
