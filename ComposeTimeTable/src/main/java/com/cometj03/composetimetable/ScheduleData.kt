package com.cometj03.composetimetable

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class TimeTableData(
    val scheduleDayData: List<ScheduleDayData>
) {
    val earliestStartHour: Int by lazy {
        scheduleDayData.minOf { it.startDate.hour }
    }
    val latestEndHour: Int by lazy {
        scheduleDayData.maxOf { it.scheduleEntities.last().endTime.hour }
    }
}

data class ScheduleDayData(
    val startDate: LocalDateTime,
    val scheduleEntities: List<ScheduleEntity>,
) {

}

data class ScheduleEntity(
    val name: String,
    val description: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val color: Color = Color.Gray,
) {
    val durationInHours: Float by lazy {
        ChronoUnit.MINUTES.between(startTime, endTime) / 60f
    }
}