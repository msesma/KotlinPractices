package sesma.eu.kotlinweather.domain.datasource

import sesma.eu.kotlinweather.domain.model.Forecast
import sesma.eu.kotlinweather.domain.model.ForecastList

interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?

}
