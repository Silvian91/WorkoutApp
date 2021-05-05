package com.rosianu.workoutnotebook.http.inspirationalquote


import com.rosianu.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import com.google.gson.annotations.SerializedName

data class InspirationalQuoteResponse(
    @SerializedName("author") val author: String = "",
    @SerializedName("tags") val tags: String = "",
    @SerializedName("id") val id: Int = 0,
    @SerializedName("length") val length: Int = 0,
    @SerializedName("quote") val quote: String = ""

) {
    fun toModel(): QuoteModel {
        return QuoteModel(
            quote, author
        )
    }
}