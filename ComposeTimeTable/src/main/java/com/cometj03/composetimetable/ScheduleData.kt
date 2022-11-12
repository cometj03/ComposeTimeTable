package com.cometj03.composetimetable

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class ScheduleDayData(
    val schedules: List<ScheduleData>,
)

data class ScheduleData(
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