package com.example.quote.repository

import com.example.quote.entity.Post
import com.example.quote.entity.SequenceNumber
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface PostRepository: JpaRepository<Post, String> {


    @Modifying
    @Query("update Post p set p.hits = p.hits + 1 where p.id = :id")
    fun increaseHits(@Param("id") id: String): Int

    fun findTop10ByOrderByUpdatedAtDesc(): List<Post>
}