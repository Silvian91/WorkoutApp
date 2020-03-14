package com.example.workoutapp.http.chucknorris


import com.example.workoutapp.domain.chucknorrisquote.model.ChuckNorrisQuoteModel
import com.google.gson.annotations.SerializedName

data class ChuckNorrisQuoteResponse(
    @SerializedName("icon_url") val iconUrl: String = "",
    @SerializedName("id") val id: String = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("value") val value: String = ""
) {
    fun toModel(): ChuckNorrisQuoteModel {
        return ChuckNorrisQuoteModel(
            url,
            value
        )
    }
}