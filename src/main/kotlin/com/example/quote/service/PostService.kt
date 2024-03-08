package com.example.quote.service

import com.example.quote.controller.WritePostRequest
import com.example.quote.entity.Post
import com.example.quote.entity.SequenceNumber
import com.example.quote.repository.PostRepository
import com.example.quote.repository.SequenceNumberRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostService(
    private val postRepository: PostRepository,
    private val sequenceNumberRepository: SequenceNumberRepository,
) {

    fun getPosts(pageable: Pageable): PostsResponse {
        val posts = postRepository.findAll(pageable)

        return PostsResponse(posts.totalPages, posts.content.map { PostDto(it.id, it.title, it.number?.id, it.writer, it.hits, it.address, it.updatedAt) })
    }

    fun writePost(writePostRequest: WritePostRequest, address: String) {
        val number = sequenceNumberRepository.save(SequenceNumber())
        val post = Post.create(writePostRequest, address, number)


        postRepository.save(post)
    }
}

data class PostDto(val id: String,
                   val title: String,
                   val number: Int?,
                   val writer:String,
                   val hits: Int?,
                   val address: String,
                   val date: LocalDateTime)

data class PostsResponse(val totalPages: Int, val posts: List<PostDto>)