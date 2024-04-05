package com.advance.crypto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 암호화 모드 상수
 * <pre>
 * {@link #DB} 데이터베이스 암호화
 * {@link #CLIENT} 클라이언트 암호화
 * </pre>
 */
@Getter
@AllArgsConstructor
public enum CryptoMode {
    DB, CLIENT
}