package sesma.eu.kotlinweather

import org.junit.Assert.assertEquals
import org.junit.Test
import sesma.eu.kotlinweather.extensions.toDateString
import java.text.DateFormat


class ExtensionsTest {
    @Test fun testLongToDateString() {
        assertEquals("19-Oct-2015", 1445275635000L.toDateString())
    }

    @Test fun testDateStringFullFormat() {
        assertEquals("Monday, 19 October 2015",
                1445275635000L.toDateString(DateFormat.FULL))
    }
}