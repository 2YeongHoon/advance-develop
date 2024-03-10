package com.advance.multitenancy.config.datasoruce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 데이터소스 컨택스트에 저장된 정보로 데이터소스를 선택한다
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String key = DataSourceContextHolder.get();

        if (null != key) {
            log.info("Routing     \t:  [{}]", DataSourceContextHolder.get());
        }

        return key;
    }
}