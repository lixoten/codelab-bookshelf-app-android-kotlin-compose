package com.example.bookshelf.model

import kotlinx.serialization.Serializable

// Notes: descrition needs to be init to ""
//  - in some cases it failed with out init value
@Serializable
data class VolumeInfo(
    val title: String,
    val description: String = "",
    val imageLinks: ImageLinks? = null,
)
