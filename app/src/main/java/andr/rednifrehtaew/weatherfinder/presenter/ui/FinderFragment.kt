package andr.rednifrehtaew.weatherfinder.presenter.ui

import andr.rednifrehtaew.weatherfinder.R
import andr.rednifrehtaew.weatherfinder.data.ListUkraineCity
import andr.rednifrehtaew.weatherfinder.data.internet.InternetListener
import andr.rednifrehtaew.weatherfinder.databinding.FragmentFinderBinding
import andr.rednifrehtaew.weatherfinder.logInfo
import andr.rednifrehtaew.weatherfinder.presenter.viewmodel.ViewModelConnection
import andr.rednifrehtaew.weatherfinder.presenter.viewmodel.ViewModelLoadData
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class FinderFragment : Fragment() {

    private lateinit var binding: FragmentFinderBinding

    private val viewModelLoadData by activityViewModel<ViewModelLoadData>()
    private val viewModelConnection by activityViewModel<ViewModelConnection>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinderBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelConnection.connection.observe(viewLifecycleOwner) {
            logInfo(it)
            if (it == InternetListener.StatusInternet.Available) {
                binding.internetState.visibility = View.INVISIBLE
                binding.getLocationButton.isEnabled = true
            } else {
                binding.internetState.visibility = View.VISIBLE
                binding.getLocationButton.isEnabled = false
            }
        }

        viewModelLoadData.weatherData.observe(viewLifecycleOwner) {
            logInfo(it)

            if (it != null) {
                binding.lastUpdate.text = "last update: " + it.last_updated.split(" ").toList()[1]
                binding.nameLocation.text =
                    "${it.name}, ${if (it.name in ListUkraineCity) "Ukraine" else it.country}"
                Picasso.get()
                    .load("https:" + it.icon)
                    .placeholder(R.drawable.ic_img_preview)
                    .into(binding.imgWeather)
                binding.textWeather.text = it.text
                binding.tempC.text =
                    "${it.temp_c} ${Html.fromHtml("&#8451;", Html.FROM_HTML_MODE_COMPACT)}"

                binding.noData.visibility = View.INVISIBLE
                binding.mainData.visibility = View.VISIBLE
            } else {
                binding.noData.visibility = View.VISIBLE
                binding.mainData.visibility = View.INVISIBLE
            }

            binding.getLocationButton.isEnabled = true
        }

        viewModelConnection.getListenConnection()

        binding.getLocationButton.setOnClickListener {
            binding.getLocationButton.isEnabled = false
            val locationName = binding.inputLocation.text.toString()
            viewModelLoadData.loadWeatherData(locationName)
        }

    }

}