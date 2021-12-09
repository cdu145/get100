package cdu145.tickets.domain.digits

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.MatchResult.Match
import com.natpryce.hamkrest.MatchResult.Mismatch
import com.natpryce.hamkrest.Matcher

internal fun equivalentTo(digits: TicketDigits): Matcher<TicketDigits> {
    return object : Matcher.Primitive<TicketDigits>() {

        fun TicketDigits.asList(): List<Int> = List(size = 6) { i -> this[i] }

        override val description: String
            get() = "equivalent to ${digits.asList()}"

        override fun invoke(actual: TicketDigits): MatchResult {
            repeat(6) { i ->
                val actualValue = actual[i]
                if (actualValue != digits[i]) return Mismatch("digit#${i + 1} is $actualValue")
            }
            return Match
        }
    }
}