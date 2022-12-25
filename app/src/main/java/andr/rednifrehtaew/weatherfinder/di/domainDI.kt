package andr.rednifrehtaew.weatherfinder.di

import andr.rednifrehtaew.weatherfinder.domain.GetCustomWeatherModel
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetCustomWeatherModel()
    }

}