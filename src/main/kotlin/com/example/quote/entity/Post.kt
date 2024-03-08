package com.example.quote.entity

import com.example.quote.controller.WritePostRequest
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
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

    @OneToOne
    var number: SequenceNumber? = null

    lateinit var writer: String

    lateinit var address: String

    var hits: Int? = null

    lateinit var createdAt: LocalDateTime

    lateinit var updatedAt: LocalDateTime

    companion object {
        fun create(request: WritePostRequest, ip: String, number: SequenceNumber): Post {
            val post = Post();

            post.title = request.title
            post.content = request.content
            post.address = ip
            post.hits = 0
            post.writer = request.writer
            post.number = number

            val now = LocalDateTime.now()
            post.createdAt = now
            post.updatedAt = now

            return post
        }
    }
}