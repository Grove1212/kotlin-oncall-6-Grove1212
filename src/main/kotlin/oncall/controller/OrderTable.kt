package oncall.controller

import oncall.model.CalendarCell
import oncall.model.WorkCell
import oncall.view.OutputView
import java.time.DayOfWeek

class OrderTable(
    private val outputView: OutputView,
    private val inputController: InputController
) {
    private val month: Int
    private val startWeekDay: String
    private val weekdayOrder: List<String>
    private val holidayOrder: List<String>
    private val holidays: List<CalendarCell>

    init {
        val (m, startDay) = inputController.controlGetMonthAndStartDayOfWeek()
        month = m
        startWeekDay = startDay
        weekdayOrder = inputController.controlGetWeekdayOrder()
        holidayOrder = inputController.controlGetHolidayOrder()
        holidays = inputController.controlGetHolidays()
    }

    fun run() {
        val shiftTable = makeShiftTable()
        putHoliday(shiftTable)
        putOrder(shiftTable)
        updateOrderIfWorkTwoDays(shiftTable)
        outputView.printOrderTable(shiftTable.toList())
    }

    private fun updateOrderIfWorkTwoDays(shiftTable: MutableList<WorkCell>) {
        for (index in 0 until shiftTable.size - 2) {
            val currentCell = shiftTable[index]
            val nextCell = shiftTable[index + 1]
            if (currentCell.employee == nextCell.employee) {
                switchOrder(shiftTable, index + 1)
            }
        }
    }

    private fun switchOrder(shiftTable: MutableList<WorkCell>, index: Int) {
        for (i in shiftTable[index].date + 1 until shiftTable.size) {
            if (shiftTable[index].isHolidayOrder() && shiftTable[i].isHolidayOrder()) {
                val tmp = shiftTable[index]
                shiftTable[index] = shiftTable[i]
                shiftTable[i] = tmp
                return
            }
        }
    }

    private fun makeShiftTable(): MutableList<WorkCell> {
        val shiftTable = mutableListOf<WorkCell>()
        val lastDateOfMonth = getLastDateOfMonth(month)
        var weekDay = getStartWeekDay(startWeekDay)

        for (i in 1..lastDateOfMonth) {
            shiftTable.add(WorkCell(month, i, weekDay))
            weekDay = weekDay?.plus(1)
        }
        return shiftTable
    }

    private fun putHoliday(shiftTable: MutableList<WorkCell>) {
        holidays.filter { shiftTable[0].month == it.month }
            .forEach { holiday ->
                shiftTable.find { it.date == holiday.date }?.isHoliday = true
            }
    }

    private fun putOrder(shiftTable: MutableList<WorkCell>) {
        val weekdayCells = shiftTable.filter { it.isWeekdayOrder() }
        val holidayCells = shiftTable.filter { it.isHolidayOrder() }

        assignEmployees(weekdayCells, weekdayOrder)
        assignEmployees(holidayCells, holidayOrder)
    }

    private fun assignEmployees(workCells: List<WorkCell>, order: List<String>) {
        workCells.forEachIndexed { index, cell ->
            cell.employee = order[index % order.size]
        }
    }

    private fun getStartWeekDay(startWeekDay: String): DayOfWeek? = when (startWeekDay) {
        "월" -> DayOfWeek.MONDAY
        "화" -> DayOfWeek.TUESDAY
        "수" -> DayOfWeek.WEDNESDAY
        "목" -> DayOfWeek.THURSDAY
        "금" -> DayOfWeek.FRIDAY
        "토" -> DayOfWeek.SATURDAY
        "일" -> DayOfWeek.SUNDAY
        else -> null
    }

    private fun getLastDateOfMonth(month: Int): Int = when (month) {
        2 -> 28
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        else -> -1
    }
}