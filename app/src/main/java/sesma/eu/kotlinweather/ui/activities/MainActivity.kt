package sesma.eu.kotlinweather.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import sesma.eu.kotlinweather.R
import sesma.eu.kotlinweather.domain.commands.RequestForecastCommand
import sesma.eu.kotlinweather.ui.adapters.ForecastListAdapter

class MainActivity : AppCompatActivity() {

    //http://openweathermap.org

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList: RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

        async() {
            val result = RequestForecastCommand("3104703").execute()
            uiThread {
                val adapter = ForecastListAdapter(result) { toast(it.date) }
                forecastList.adapter = adapter
            }
        }
    }

}
