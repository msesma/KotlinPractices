package sesma.eu.kotlinweather.domain.commands

import sesma.eu.kotlinweather.domain.datasource.ForecastProvider
import sesma.eu.kotlinweather.domain.model.Forecast

class RequestDayForecastCommand(
        val id: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}