package andr.rednifrehtaew.weatherfinder.data.internet

import kotlinx.coroutines.flow.Flow

interface InternetListener {

    fun changeStateConnection(): Flow<StatusInternet>

    fun isInternet(): StatusInternet

    enum class StatusInternet {
        Lost, Available
    }
}


