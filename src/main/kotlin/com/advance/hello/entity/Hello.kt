package com.advance.hello.entity

import jakarta.persistence.*

@Entity
@Table(name = "hello")
class Hello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null

    @Column(nullable = false)
    open var name: String = ""

}