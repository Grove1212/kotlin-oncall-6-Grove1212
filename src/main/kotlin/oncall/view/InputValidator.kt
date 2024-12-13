package oncall.view

import camp.nextstep.edu.missionutils.Console

 object InputValidator {
    fun validateGetMonthAndStartDayOfTheWeek(input: List<String>) {
        val month = input[0].toInt()
        require(month in 1..12) {
            "[ERROR] Invalid month: $month 다시 입력해 주세요."
        }
        val dayOfWeek = input[1]
        val validDays = setOf("월", "화", "수", "목", "금", "토", "일")
        require(dayOfWeek in validDays) { "[ERROR] Invalid dayOfWeek: $dayOfWeek 다시 입력해 주세요." }
    }

    fun validateGetOrder(input: List<String>) {
        input.forEach { require(it.length <= 5) { "[ERROR] 닉네임 길이는 5자를 초과하지 않습니다. 다시 입력해 주세요." } }
        require(input.size in 5..35) { "[ERROR] 순번은 5명에서 35명 사이여야 합니다." }
        val inputMap = input.toSet()
        require(input.size == inputMap.size) { "[ERROR] 이름 중복입니다. 다시 입력해주세요." }
    }
}