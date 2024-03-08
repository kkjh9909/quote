package com.example.quote.service

import com.example.quote.controller.WritePostRequest
import com.example.quote.entity.Post
import com.example.quote.entity.SequenceNumber
import com.example.quote.repository.PostRepository
import com.example.quote.repository.SequenceNumberRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrElse

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

    @Transactional
    fun getPost(postId: String): PostDetailDto {
        postRepository.increaseHits(postId)

        val post = postRepository.findById(postId).get()

        return PostDetailDto(post.id, post.title, post.number?.id, post.writer, post.hits, post.address, post.content, post.updatedAt)
    }
}

data class PostDto(val id: String,
                   val title: String,
                   val number: Int?,
                   val writer:String,
                   val hits: Int?,
                   val address: String,
                   val date: LocalDateTime)

data class PostDetailDto(val id: String,
                   val title: String,
                   val number: Int?,
                   val writer:String,
                   val hits: Int?,
                   val address: String,
                   val content: String,
                   val date: LocalDateTime)

data class PostsResponse(val totalPages: Int, val posts: List<PostDto>)
data class RelatedPostDto(val id: String, val title: String, val date: LocalDateTime)