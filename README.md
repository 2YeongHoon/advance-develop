# [Server] advance-develop

![Java](https://img.shields.io/badge/Java-17-red.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.3-green.svg)

이것 저것 시도해보는 스프링 부트 프로젝트

# 패키지 정보
```
[ai]
 - Spring AI 템플릿 스트

[crypto]
 - DB 암호화, Client 통신 암호화 테스트

[mail]
 - 매일 발송 고도화
 - 1단계: 비동기 처리 발송 24.08.27
 - 2단게: Rebbit MQ로 큐잉하여 발송 24.10.09

[multitenancy]
 - 런타임에 동일DB 인스턴스에 다른 스키마로 접속 변경하기 위한 테스트 
```

# 접속정보 
```
RebbitMQ
 - http:// http://localhost:15672/
 - ID / PW : guest / guest
Redis 
```
# 명령어
```
Ollama
 - 실행: ollama run llama3
Redis 
 - 전체 키 조회: keys *
 - 값 조회: get "key"
 - ttl 조회: ttl "key"
```