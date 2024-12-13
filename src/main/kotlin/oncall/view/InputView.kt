package oncall.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun getMonthAndStartDayOfTheWeek(): Pair<Int, String> {
        println(MONTHANDSTARTDAYOFWEEK)
        val input = Console.readLine().split(",") ?: throw IllegalArgumentException("[ERROR] 입력을 받을 수 없습니다.")
        val month = input[0].toInt()
        require(month in 1..12) {
            "[ERROR] Invalid month: $month 다시 입력해 주세요."
        }
        val dayOfWeek = input[1]
        val validDays = setOf("월", "화", "수", "목", "금", "토", "일")
        require(dayOfWeek in validDays) { "[ERROR] Invalid dayOfWeek: $dayOfWeek 다시 입력해 주세요." }

        return Pair(month, dayOfWeek)
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
        input.forEach { require(it.length <= 5) { "[ERROR] 닉네임 길이는 5자를 초과하지 않습니다. 다시 입력해 주세요." } }
        require(input.size in 5..35) { "[ERROR] 순번은 5명에서 35명 사이여야 합니다." }
        val inputMap = input.toSet()
        require(input.size == inputMap.size) { "[ERROR] 이름 중복입니다. 다시 입력해주세요." }
        return input
    }

    companion object{
        const val MONTHANDSTARTDAYOFWEEK = "비상 근무를 배정할 월과 시작 요일을 입력하세요> "
        const val WEEKDAYTURNNUMBER = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "
        const val HOLIDAYTURNNUMBER = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> "
    }
}