package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.model.CalendarCell
import java.io.File

class InputView {
    fun loadHolidays(): List<CalendarCell> {
        return File(HOLIDAYSPAHT).useLines { lines ->
            lines.drop(1)
                .map { line ->
                    val (month, date) = line.split(",").map { it.toInt() }
                    CalendarCell(month, date)
                }
                .toList()
        }
    }

    fun getMonthAndStartDayOfTheWeek(): Pair<Int, String> {
        println(MONTHANDSTARTDAYOFWEEK)
        val input = Console.readLine().split(",") ?: throw IllegalArgumentException("[ERROR] 입력을 받을 수 없습니다.")
        InputValidator.validateGetMonthAndStartDayOfTheWeek(input)
        return Pair(input[0].toInt(), input[1])
    }

    fun getWeekdayOrder(): List<String> {
        println(WEEKDAYTURNNUMBER)
        return getOrder()
    }

    fun getHolidayOrder(): List<String> {
        println(HOLIDAYTURNNUMBER)
        return getOrder()
    }

    private fun getOrder(): List<String> {
        val input = Console.readLine().split(",") ?: throw IllegalArgumentException("[ERROR] 입력을 받을 수 없습니다.")
        InputValidator.validateGetOrder(input)
        return input
    }

    companion object {
        const val HOLIDAYSPAHT = "src/main/resources/holidays.md"
        const val MONTHANDSTARTDAYOFWEEK = "비상 근무를 배정할 월과 시작 요일을 입력하세요> "
        const val WEEKDAYTURNNUMBER = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "
        const val HOLIDAYTURNNUMBER = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> "
    }
}