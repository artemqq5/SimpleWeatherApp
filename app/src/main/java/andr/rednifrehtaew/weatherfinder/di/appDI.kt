package andr.rednifrehtaew.weatherfinder.di

import andr.rednifrehtaew.weatherfinder.presenter.viewmodel.ViewModelConnection
import andr.rednifrehtaew.weatherfinder.presenter.viewmodel.ViewModelLoadData
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        ViewModelLoadData(
            repository = get(),
            getCustomWeatherModel = get()
        )
    }

    viewModel {
        ViewModelConnection(
            internetListener = get()
        )
    }

}