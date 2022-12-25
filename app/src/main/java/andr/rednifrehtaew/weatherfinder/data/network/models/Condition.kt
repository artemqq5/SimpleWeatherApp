package andr.rednifrehtaew.weatherfinder.data.network.models

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Condition(
    val icon: String,
    val text: String
)