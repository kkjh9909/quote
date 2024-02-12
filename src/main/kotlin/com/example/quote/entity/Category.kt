package com.example.quote.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.util.*

@Entity
class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: String = UUID.randomUUID().toString()

    var category: String? = null

    var createdAt: LocalDateTime? = null

    var updatedAt: LocalDateTime? = null
}