package com.cometj03.composetimetable

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class ScheduleGraphData(
    val scheduleCellData: List<ScheduleCellData>
) {
    val dayOfWeek: DayOfWeek by lazy {
        scheduleCellData.first().startTime.dayOfWeek
    }
}

data class ScheduleCellData(
    val name: String,
    val description: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
) {
    val minutesLength: Long by lazy {
        ChronoUnit.MINUTES.between(startTime, endTime)
    }
}