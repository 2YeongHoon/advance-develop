package com.advance.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * 사용자 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Comment("별칭(닉네임)")
    @Column(name = "name")
    private String name;

    @Comment("직업")
    @Column(name = "age")
    private String age;

    private User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public static User of(String name, String age) {
        return new User(name, age);
    }

}
