package com.advance.mail.enums;

/**
 * 이메일 발송을 위한 템플릿 분류
 *
 */
public enum TemplateType {

    MAIL_TEMPLATE {
        @Override
        public String fileName() {
            return "mail_template.html";
        }
    };

    public abstract String fileName();

}
