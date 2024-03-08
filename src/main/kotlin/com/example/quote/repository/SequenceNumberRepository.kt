package com.example.quote.repository

import com.example.quote.entity.SequenceNumber
import org.springframework.data.jpa.repository.JpaRepository

interface SequenceNumberRepository: JpaRepository<SequenceNumber, Int> {
}