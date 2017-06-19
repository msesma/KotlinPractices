package sesma.eu.kotlinweather.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import sesma.eu.kotlinweather.R
import sesma.eu.kotlinweather.domain.commands.RequestForecastCommand
import sesma.eu.kotlinweather.domain.model.ForecastList
import sesma.eu.kotlinweather.extensions.DelegatesExt
import sesma.eu.kotlinweather.ui.adapters.ForecastListAdapter

class MainActivity : AppCompatActivity(), ToolbarManager {

    //http://openweathermap.org
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

//    private fun loadForecast() = doAsync {
//        val result = RequestForecastCommand(zipCode).execute()
//
//        uiThread {
//            val adapter = ForecastListAdapter(result) {
//                startActivity<DetailActivity>(DetailActivity.ID to it.id,
//                        DetailActivity.CITY_NAME to result.city)
//            }
//            forecastList.adapter = adapter
//            toolbarTitle = "${result.city} (${result.country})"
//        }
//    }

    private fun loadForecast() = async(UI) {
        val result = bg { RequestForecastCommand(zipCode).execute() }
        updateUI(result.await())
    }

    private fun updateUI(result: ForecastList) {
        val adapter = ForecastListAdapter(result) {
            startActivity<DetailActivity>(DetailActivity.ID to it.id,
                    DetailActivity.CITY_NAME to result.city)
        }
        forecastList.adapter = adapter
        toolbarTitle = "${result.city} (${result.country})"
    }
}
