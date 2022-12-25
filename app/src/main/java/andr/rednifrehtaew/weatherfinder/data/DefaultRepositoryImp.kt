package andr.rednifrehtaew.weatherfinder.data

import andr.rednifrehtaew.weatherfinder.data.network.GetDataWeather
import andr.rednifrehtaew.weatherfinder.data.network.models.WeatherModel
import andr.rednifrehtaew.weatherfinder.domain.DefaultRepository
import retrofit2.Response

class DefaultRepositoryImp(private val getDataWeather: GetDataWeather) : DefaultRepository {

    override suspend fun getApiData(city: String): Response<WeatherModel> {
        return getDataWeather.getWeatherData(city)
    }


}