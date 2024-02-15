package com.example.quote.service

import com.example.quote.entity.Category
import com.example.quote.repository.CategoryRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun getCategories(): List<CategoryDto> {
        val categories = categoryRepository.findAll(PageRequest.of(0, 10)).content

        return categories.map { category -> CategoryDto(category.id, category.category) }
    }

    fun getCategoryName(categoryId: String): CategoryInfo? {
        val category = categoryRepository.findById(categoryId)

        return CategoryInfo(category.get().id, category.get().category)
    }

    fun getAllCategories(): List<CategoryDto> {
        return categoryRepository.findAll().map { category -> CategoryDto(category.id, category.category) }
    }

    fun getCategoryList(pageable: Pageable): CategoryList {
        val categories = categoryRepository.findAll(pageable)

        return CategoryList(categories.totalPages, categories.content.map {category -> CategoryDto(category.id, category.category) })
    }
}

data class CategoryInfo(val id: String, val name: String?)
data class CategoryDto(val id: String, val category: String?)

data class CategoryList(val totalPages: Int, val categoryList: List<CategoryDto>)