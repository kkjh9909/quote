package com.example.quote.service

import com.example.quote.entity.Author
import com.example.quote.repository.AuthorRepository
import org.hibernate.internal.util.collections.CollectionHelper.listOf
import org.springframework.stereotype.Service

@Service
class AuthorService(private val authorRepository: AuthorRepository) {

    fun getAuthors(): List<AuthorDto> {
        val authors: List<Author> = authorRepository.findAll()

        return authors.map { author -> AuthorDto(author.id, author.name) }
    }
}

data class AuthorDto(var id: String, var name: String?)