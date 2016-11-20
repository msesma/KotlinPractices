package sesma.eu.kotlinweather.domain.commands

import sesma.eu.kotlinweather.domain.datasource.ForecastProvider
import sesma.eu.kotlinweather.domain.model.ForecastList

class RequestForecastCommand(
        val zipCode: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)
}