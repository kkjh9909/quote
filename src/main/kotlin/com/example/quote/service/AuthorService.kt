package com.example.quote.service

import com.example.quote.entity.Author
import com.example.quote.repository.AuthorRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AuthorService(private val authorRepository: AuthorRepository) {

    fun getAuthors(): List<AuthorDto> {
        val authors: List<Author> = authorRepository.findAll(PageRequest.of(0, 10)).content

        return authors.map { author -> AuthorDto(author.id, author.name, author.quoteCount) }
    }

    fun getAllAuthors(): List<AuthorDto> {
        return authorRepository.findAll().map { author -> AuthorDto(author.id, author.name, author.quoteCount) };
    }

    fun getAuthorList(pageable: Pageable, order: String): AuthorList {
        val authors: Page<Author> = if (order == "popular")
            authorRepository.findAllByOrderByQuoteCountDesc(pageable)
        else
            authorRepository.findAllByOrderByName(pageable)

        return AuthorList(authors.totalPages, authors.content.map { author -> AuthorDto(author.id, author.name, author.quoteCount) })
    }

    fun getAuthorName(authorId: String): String {
        val author = authorRepository.findById(authorId)

        return author.get().name
    }
}

data class AuthorDto(val id: String, val name: String, val count: Int)

data class AuthorList(val totalPages: Int, val authorList: List<AuthorDto>)