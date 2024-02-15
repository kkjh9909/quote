package com.example.quote.service

import com.example.quote.entity.Author
import com.example.quote.repository.AuthorRepository
import org.hibernate.internal.util.collections.CollectionHelper.listOf
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AuthorService(private val authorRepository: AuthorRepository) {

    fun getAuthors(): List<AuthorDto> {
        val authors: List<Author> = authorRepository.findAll(PageRequest.of(0, 10)).content

        return authors.map { author -> AuthorDto(author.id, author.name) }
    }

    fun getAllAuthors(): List<AuthorDto> {
        return authorRepository.findAll().map { author -> AuthorDto(author.id, author.name) };
    }

    fun getAuthorList(pageable: Pageable): AuthorList {
        val authors = authorRepository.findAll(pageable)

        return AuthorList(authors.totalPages, authors.content.map { author -> AuthorDto(author.id, author.name) })
    }
}

data class AuthorDto(var id: String, var name: String?)

data class AuthorList(val totalPages: Int, val authorList: List<AuthorDto>)