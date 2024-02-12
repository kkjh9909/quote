package com.example.quote.repository

import com.example.quote.entity.Category
import org.springframework.data.jpa.repository.JpaRepository


interface CategoryRepository: JpaRepository<Category, String> {

}