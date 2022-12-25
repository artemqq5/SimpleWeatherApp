package andr.rednifrehtaew.weatherfinder.domain

import andr.rednifrehtaew.weatherfinder.data.network.models.WeatherModel
import andr.rednifrehtaew.weatherfinder.domain.models.CustomWeatherModel

class GetCustomWeatherModel {

    fun getCustomModel(weatherModel: WeatherModel?): CustomWeatherModel? {
        return if (weatherModel != null) CustomWeatherModel(
            country = weatherModel.location.country,
            icon = weatherModel.current.condition.icon,
            text = weatherModel.current.condition.text,
            last_updated = weatherModel.current.last_updated,
            temp_c = weatherModel.current.temp_c,
            name = weatherModel.location.name
        ) else null
    }

}