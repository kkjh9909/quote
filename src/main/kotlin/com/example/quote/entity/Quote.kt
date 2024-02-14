package com.example.quote.entity

import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Builder
class Quote {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    var id: String = UUID.randomUUID().toString()

    var content: String? = null

    var createdAt: LocalDateTime? = null

    var updatedAt: LocalDateTime? = null

    @ManyToOne
    var category: Category? = null

    @ManyToOne
    var author: Author? = null

    companion object {
        fun create(category: Category, author: Author, content: String): Quote {
            val quote = Quote()
            quote.content = content

            val now = LocalDateTime.now()
            quote.createdAt = now
            quote.updatedAt = now

            quote.category = category
            quote.author = author

            return quote
        }
    }
}