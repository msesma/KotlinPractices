package sesma.eu.kotlinweather.domain.commands

import sesma.eu.kotlinweather.data.ForecastRequest
import sesma.eu.kotlinweather.domain.mappers.ForecastDataMapper
import sesma.eu.kotlinweather.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}