package com.example.quote.service

import com.example.quote.repository.PostRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostService(
    private val postRepository: PostRepository
) {

    fun getPosts(pageable: Pageable): List<PostDto> {
        val posts = postRepository.findAll(pageable)

        return posts.content.map { PostDto(it.id, it.title, it.number, it.writer, it.hits, it.address, it.updatedAt) }
    }
}

data class PostDto(val id: String,
                   val title: String,
                   val number: Int?,
                   val writer:String,
                   val hits: Int?,
                   val address: String,
                   val date: LocalDateTime)