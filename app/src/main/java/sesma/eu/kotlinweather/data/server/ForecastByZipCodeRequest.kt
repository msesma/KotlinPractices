package sesma.eu.kotlinweather.data.server

import android.util.Log
import com.google.gson.Gson
import sesma.eu.kotlinweather.data.server.ForecastResult
import java.net.URL

class ForecastByZipCodeRequest(val zipCode: Long, val gson: Gson = Gson()) {

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
//        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?id=3104703&appid=15646a06818f61f7b8d7823ca833e1ce
        private val COMPLETE_URL = "${URL}&APPID=${APP_ID}&id="
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
        return gson.fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}
