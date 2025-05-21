package com.advance.accesslog.application;

import com.advance.accesslog.application.dto.AccessLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessLogService {

    public void saveAccessLog(AccessLogDto dtoo) {
        //TODO: 데이터 저장
        AccessLogDto dto = dtoo;
    }

}