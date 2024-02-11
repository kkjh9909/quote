package com.example.quote.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.util.UUID

@Entity
class Quote {

    @Id
    var id: String = UUID.randomUUID().toString()

    var content: String? = null

    var createdAt: LocalDateTime? = null

    var updatedAt: LocalDateTime? = null
}