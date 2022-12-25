package andr.rednifrehtaew.weatherfinder.domain.models

import androidx.annotation.Keep

@Keep
data class CustomWeatherModel(
    val country: String,
    val name: String,
    val icon: String,
    val text: String,
    val last_updated: String,
    val temp_c: Double,
)