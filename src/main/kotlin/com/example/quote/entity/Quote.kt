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
    @Column(columnDefinition = "VARCHAR(36)")
    var id: String = UUID.randomUUID().toString()

    lateinit var content: String

    var createdAt: LocalDateTime? = null

    var updatedAt: LocalDateTime? = null

    @ManyToOne
    lateinit var category: Category

    @ManyToOne
    lateinit var author: Author

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