package com.example.quote.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import java.util.*

@Entity
class Post {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    var id: String = UUID.randomUUID().toString()

    lateinit var title: String

    lateinit var content: String

    var number: Int? = null

    lateinit var writer: String

    lateinit var address: String

    var hits: Int? = null

    lateinit var createdAt: LocalDateTime

    lateinit var updatedAt: LocalDateTime
}