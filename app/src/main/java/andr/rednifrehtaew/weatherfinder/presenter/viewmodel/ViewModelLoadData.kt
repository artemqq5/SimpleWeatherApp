package andr.rednifrehtaew.weatherfinder.presenter.viewmodel

import andr.rednifrehtaew.weatherfinder.domain.DefaultRepository
import andr.rednifrehtaew.weatherfinder.domain.GetCustomWeatherModel
import andr.rednifrehtaew.weatherfinder.domain.models.CustomWeatherModel
import andr.rednifrehtaew.weatherfinder.logInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelLoadData(
    private val repository: DefaultRepository,
    private val getCustomWeatherModel: GetCustomWeatherModel
) : ViewModel() {

    private val mutableWeatherData = MutableLiveData<CustomWeatherModel?>()
    val weatherData: LiveData<CustomWeatherModel?>
        get() = mutableWeatherData

    fun loadWeatherData(city: String) {
        val coroutinesExc = CoroutineExceptionHandler { _, throwable ->
            logInfo("coroutine load weather data exception: $throwable")
            mutableWeatherData.postValue(null)
        }
        viewModelScope.launch(Dispatchers.IO + coroutinesExc) {
            val dataWeather = repository.getApiData(city).body()
            val customDataWeather = getCustomWeatherModel.getCustomModel(dataWeather)
            mutableWeatherData.postValue(customDataWeather)
        }
    }

}