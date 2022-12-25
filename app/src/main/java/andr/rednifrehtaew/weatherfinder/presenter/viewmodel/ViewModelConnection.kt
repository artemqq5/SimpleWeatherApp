package andr.rednifrehtaew.weatherfinder.presenter.viewmodel

import andr.rednifrehtaew.weatherfinder.data.internet.InternetListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ViewModelConnection(
    private val internetListener: InternetListener
) : ViewModel() {

    private val mutableConnection =
        MutableLiveData<InternetListener.StatusInternet>(internetListener.isInternet())
    val connection: LiveData<InternetListener.StatusInternet>
        get() = mutableConnection

    fun getListenConnection() {
        viewModelScope.launch(Dispatchers.IO) {
            internetListener.changeStateConnection().collectLatest {
                mutableConnection.postValue(it)
            }
        }

    }

}