package com.advance.mail.infrastructure;

import com.advance.mail.enums.TemplateType;
import com.advance.mail.service.dto.MqMailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Component
@RequiredArgsConstructor
public class MqMailService {

    private final SpringTemplateEngine engine;
    private final JavaMailSender sender;

    public void sendMessage(MqMailDto dto) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

        helper.setSubject("메일 전송 테스트");
        helper.setTo(dto.getReceivers());
        helper.setText(innerHtml(), true);
        helper.addAttachment("테스트 파일", new ByteArrayResource(dto.getFile()));
        sender.send(message);
    }

    private String innerHtml() {
        return this.engine.process(TemplateType.MAIL_TEMPLATE.fileName(), context());
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
