package com.example.quote.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: String = UUID.randomUUID().toString()

    var content: String? = null

    var createdAt: LocalDateTime? = null

    var updatedAt: LocalDateTime? = null

    @ManyToOne
    var category: Category? = null

    @ManyToOne
    var author: Author? = null
}