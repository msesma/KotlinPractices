package sesma.eu.kotlinweather.data.server

import sesma.eu.kotlinweather.data.db.ForecastDb
import sesma.eu.kotlinweather.domain.datasource.ForecastDataSource
import sesma.eu.kotlinweather.domain.mappers.ServerDataMapper
import sesma.eu.kotlinweather.domain.model.ForecastList


class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()
}
