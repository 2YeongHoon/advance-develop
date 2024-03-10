package com.advance.multitenancy.config.datasoruce;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 커스텀 데이터소스 프로퍼티
 */
@Getter
@ConfigurationProperties(prefix = "custom.datasource")
public class CustomDatasourceProperties {

    private final String url;
    private final String urlOption;
    private final String schema;
    private final String username;
    private final String password;
    private final List<String> tenancyIds;
    private final List<TargetDatasource> targets;

    public CustomDatasourceProperties(
        String url,
        String urlOption,
        String schema,
        String username,
        String password,
        List<String> tenancyIds,
        String schemaPrefix
    ) {
        this.url = url;
        this.urlOption = urlOption;
        this.schema = schema;
        this.username = username;
        this.password = password;
        this.tenancyIds = tenancyIds;

        this.targets = tenancyIds.stream()
            .map(id -> TargetDatasource.builder()
                .key("TEST")
                .name(id)
                .schema(id)
                .username(id)
                .password(id)
                .schemaPrefix(schemaPrefix)
                .build())
            .collect(Collectors.toList());
    }

    @Getter
    public static class TargetDatasource {

        @Getter(AccessLevel.NONE)
        private final String key;
        private final String name;
        private final String schema;
        private final String username;
        private final String password;

        @Builder
        private TargetDatasource(
            String key,
            String name,
            String schema,
            String username,
            String password,
            String schemaPrefix
        ) {
            this.key = key;
            this.name = name;
            this.schema = schemaPrefix + schema;
            this.username = "TEST" + username;
            this.password = "test" + password + "@" ;
        }
    }

}