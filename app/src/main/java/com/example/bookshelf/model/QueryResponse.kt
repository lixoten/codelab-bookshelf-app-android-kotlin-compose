package com.example.bookshelf.model

import kotlinx.serialization.Serializable

// Notes: the null here is KEY, we need it, in case nothing is found
@Serializable
data class QueryResponse(
    val items: List<Book>?,
    val totalItems: Int,
    val kind: String,
)
