package oncall.model

import java.time.DayOfWeek

class WorkCell(
    month: Int,
    date: Int,
    private val dayOfWeek: DayOfWeek?,
    var employee: String = "",
    var isHoliday: Boolean = false
) : CalendarCell(month, date) {
    fun isHolidayOrder(): Boolean {
        return dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY || isHoliday
    }

    fun isWeekdayOrder(): Boolean {
        return !isHolidayOrder()
    }

    private fun getDay(): String = when (dayOfWeek) {
        DayOfWeek.MONDAY -> "월"
        DayOfWeek.TUESDAY -> "화"
        DayOfWeek.WEDNESDAY -> "수"
        DayOfWeek.THURSDAY -> "목"
        DayOfWeek.FRIDAY -> "금"
        DayOfWeek.SATURDAY -> "토"
        DayOfWeek.SUNDAY -> "일"
        else -> ""
    }

    @Override
    override fun toString(): String {
        return "${month}월 ${date}일 ${getDay()} $employee".trimIndent()
    }
}