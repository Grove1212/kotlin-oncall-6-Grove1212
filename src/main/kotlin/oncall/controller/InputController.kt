package oncall.controller

import oncall.model.CalendarCell
import oncall.view.InputView

class InputController(private val inputView: InputView) {
    fun controlGetHolidays(): List<CalendarCell> {
        return inputView.loadHolidays()
    }

    fun controlGetMonthAndStartDayOfWeek(): Pair<Int, String> {
        while (true) {
            try {
                val input = inputView.getMonthAndStartDayOfTheWeek()
                return input
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun controlGetWeekdayOrder(): List<String> {
        while (true) {
            try {
                val weekdayTurnNumber = inputView.getWeekdayOrder()
                return weekdayTurnNumber
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun controlGetHolidayOrder(): List<String> {
        while (true) {
            try {
                val holidayTurnNumber = inputView.getHolidayOrder()
                return holidayTurnNumber
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}
