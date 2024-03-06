package com.example.quote.repository

import com.example.quote.entity.Post
import org.springframework.data.jpa.repository.JpaRepository


interface PostRepository: JpaRepository<Post, String> {
}