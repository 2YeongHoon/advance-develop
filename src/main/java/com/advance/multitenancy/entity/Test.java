package com.advance.multitenancy.entity;

import com.advance.crypto.converter.DbCryptoConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Comment("이름")
    @Convert(converter = DbCryptoConverter.class)
    @Column(name = "name")
    private String name;

    public Test() {}

    public Test(String name) {
        this.name = name;
    }

}
