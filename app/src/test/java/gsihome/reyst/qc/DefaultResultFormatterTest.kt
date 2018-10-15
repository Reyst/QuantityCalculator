package gsihome.reyst.qc

import gsihome.reyst.qc.domain.DefaultResultFormatter
import gsihome.reyst.qc.domain.ResultFormatter
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DefaultResultFormatterTest {

    private lateinit var formatter: ResultFormatter

    @Before
    fun setUp() {
        formatter = DefaultResultFormatter()
    }

    @Test
    fun `should return zero with two signs after dot`() {
        Assert.assertEquals("0,00", formatter.format(0))
    }

    @Test
    fun `should return 1000,000 with three signs after dot`() {
        Assert.assertEquals("1000,000", formatter.format(1000, 3))
    }

    @Test
    fun `should return 12,34 with two signs after dot`() {
        Assert.assertEquals("12,34", formatter.format(12.335, 2))
    }

}