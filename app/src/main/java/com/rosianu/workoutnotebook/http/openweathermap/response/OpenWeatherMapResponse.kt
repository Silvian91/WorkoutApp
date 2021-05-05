package com.rosianu.workoutnotebook.http.openweathermap.response


import com.rosianu.workoutnotebook.domain.weather.model.WeatherModel
import com.google.gson.annotations.SerializedName

data class OpenWeatherMapResponse(
    @SerializedName("base") val base: String = "",
    @SerializedName("clouds") val clouds: Clouds? = null,
    @SerializedName("cod") val cod: Int = 0,
    @SerializedName("coord") val coordinates: Coordinates? = null,
    @SerializedName("dt") val dt: Int = 0,
    @SerializedName("id") val id: Int = 0,
    //find out what main is and name it properly
    @SerializedName("main") val main: Main? = null,
    @SerializedName("name") val name: String = "",
    @SerializedName("sys") val sys: Sys = Sys(),
    @SerializedName("timezone") val timezone: Int = 0,
    @SerializedName("visibility") val visibility: Int = 0,
    @SerializedName("weather") val weather: List<Weather> = listOf(),
    @SerializedName("wind") val wind: Wind = Wind()
) {
    fun toModel() = WeatherModel(main!!.temp, name)
}