package net.catte.ical.filter

import com.github.ajalt.clikt.core.*
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.path
import net.fortuna.ical4j.data.CalendarBuilder
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.util.CompatibilityHints
import java.nio.file.Files

fun main(args: Array<String>) {
    FilterCli().main(args)
}

class FilterCli : CliktCommand() {
    private val inputFile by option("-f", "--input-file").path(mustExist = true).required()
    private val pattern by option("-p", "--pattern").required()
    private val outputFile by option("-o", "--output-file").path()

    override fun run() {
        System.setProperty(CompatibilityHints.KEY_RELAXED_VALIDATION, "true")
        val cal = parseCal()
        val buffer = StringBuffer()
        cal.split()
            .filter {
                it.toString().contains(pattern)
            }
            .forEach {
                buffer.append(it.toString())
            }
        if (outputFile != null) {
            Files.writeString(outputFile, buffer.toString())
        } else {
            println(buffer.toString())
        }
    }

    private fun parseCal(): Calendar {
        val fin = Files.newInputStream(inputFile)
        val builder = CalendarBuilder()
        return builder.build(fin)
    }
}
