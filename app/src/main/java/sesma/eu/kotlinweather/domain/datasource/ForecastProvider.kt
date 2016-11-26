package sesma.eu.kotlinweather.domain.datasource

import sesma.eu.kotlinweather.data.db.ForecastDb
import sesma.eu.kotlinweather.data.server.ForecastServer
import sesma.eu.kotlinweather.domain.model.Forecast
import sesma.eu.kotlinweather.domain.model.ForecastList
import sesma.eu.kotlinweather.extensions.firstResult
import java.util.concurrent.TimeUnit

class ForecastProvider(val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1)
        val SOURCES by lazy { listOf(ForecastDb(), ForecastServer()) }
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size() >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }

}