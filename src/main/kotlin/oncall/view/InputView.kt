package oncall.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun getMonthAndStartDayOfTheWeek(): Pair<Int, String> {
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

}