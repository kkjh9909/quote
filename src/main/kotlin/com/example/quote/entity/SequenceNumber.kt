package com.example.quote.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class SequenceNumber {

    @Id @GeneratedValue
    var id: Int? = null
}