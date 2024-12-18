package net.catte.ical

import com.github.ajalt.clikt.testing.test
import net.catte.ical.filter.FilterCli
import org.assertj.core.api.Assertions.*
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.Test

class FilterCliTest {
    @Test fun filter_example() {
        val filter = FilterCli()
        val tmp = Files.createTempDirectory("FilterCliTest_filter_example")
        val exampleDir = Paths.get("src/test/resources")
        val inputFile = exampleDir.resolve("events.ics")
        val expectedFile = exampleDir.resolve("codir.ics")
        val outputFile = tmp.resolve("output.ics")

        filter.test("-f $inputFile -p 'Réunion du Comité de Direction' -o $outputFile")
        assertThat(outputFile)
            .exists()
            .isNotEmptyFile()
            .hasSameTextualContentAs(expectedFile)
    }
}
