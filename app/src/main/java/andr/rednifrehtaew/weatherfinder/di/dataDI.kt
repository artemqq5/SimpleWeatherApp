package andr.rednifrehtaew.weatherfinder.di

import andr.rednifrehtaew.weatherfinder.data.DefaultRepositoryImp
import andr.rednifrehtaew.weatherfinder.data.internet.InternetListener
import andr.rednifrehtaew.weatherfinder.data.internet.InternetListenerImp
import andr.rednifrehtaew.weatherfinder.data.network.GetDataWeather
import andr.rednifrehtaew.weatherfinder.data.network.GetDataWeatherImp
import andr.rednifrehtaew.weatherfinder.domain.DefaultRepository
import org.koin.dsl.module

val dataModule = module {

    single<GetDataWeather> {
        GetDataWeatherImp()
    }

    single<DefaultRepository> {
        DefaultRepositoryImp(getDataWeather = get())
    }

    factory<InternetListener> {
        InternetListenerImp(context = get())
    }



}