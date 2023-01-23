package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val description: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo
) {
    // Notes: This works too...
    fun getPrice() : String {
        if (saleInfo.listPrice == null) {
            return ""
        }
        return "${saleInfo.listPrice.amount} ${saleInfo.listPrice.currency}"
    }

}

@Serializable
data class VolumeInfo(
    val title: String,
    val subtitle: String,
    val description: String,
    val imageLinks: ImageLinks? = null,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
) {
    val allAuthorsx: String
        get() = allAuthors()

    fun allAuthors() : String {
        var x= ""
        for (author in authors) {
            x += "$author, "
        }
        return x.trimEnd(',', ' ')
    }
}

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String,
) {
    val httpsThumbnail : String
        get() = thumbnail.replace("http", "https")
}


@Serializable
data class SaleInfo(
    val country: String,
    val isEbook: Boolean,
    val listPrice: ListPrice?
) {
    // Notes: This works...
    val getPrice2 : String
        get() = "${listPrice?.amount ?: "N/A"} ${listPrice?.currency ?: "N/A"}"

}

@Serializable
data class ListPrice(
    val amount: Float?,
    val currency: String? = ""
)