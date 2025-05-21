package com.advance.accesslog.application.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessLogDto {

    private String userId;
    private String queryString;
    private String ip;
    private String uri;
    private String method;
    private String params;
    private LocalDateTime accessedAt;

    public void setParams(String params) {
        this.params = params;
    }

}