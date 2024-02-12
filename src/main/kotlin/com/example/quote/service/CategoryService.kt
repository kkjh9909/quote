package com.example.quote.service

import com.example.quote.repository.CategoryRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun getCategories(): List<CategoryDto> {
        val categories = categoryRepository.findAll(PageRequest.of(0, 10)).content

        return categories.map { category -> CategoryDto(category.id, category.category) }
    }
}

data class CategoryDto(val id: String, val category: String?)