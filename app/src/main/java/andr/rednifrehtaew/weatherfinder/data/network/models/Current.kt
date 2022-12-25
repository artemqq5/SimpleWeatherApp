package andr.rednifrehtaew.weatherfinder.data.network.models

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Current(
    val condition: Condition,
    val feelslike_c: Double,
    val last_updated: String,
    val temp_c: Double,
    val uv: Double
)