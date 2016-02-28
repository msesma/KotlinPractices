package sesma.eu.kotlinweather.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
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

        forecastList.layoutManager = LinearLayoutManager(this)

        async() {
            val result = RequestForecastCommand(3104703).execute()
            uiThread {
                val adapter = ForecastListAdapter(result, { toast(it.description) })
                forecastList.adapter = adapter
            }
        }
    }

}
