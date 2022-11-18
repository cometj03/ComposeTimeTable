package com.cometj03.composetimetable

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

data class TimeTableData(
    val scheduleDayDataList: List<ScheduleDayData>
) {
    val earliestStartHour: Int by lazy {
        scheduleDayDataList.minOf {
            if (it.scheduleEntities.isEmpty()) tableStartHour
            else it.startTimeOfDay.hour
        }
    }
    val latestEndHour: Int by lazy {
        scheduleDayDataList.maxOf {
            if (it.scheduleEntities.isEmpty()) tableEndHour
            else it.endTimeOfDay.hour
        }
    }
    val entitiesCountList: List<Int> by lazy {
        List(scheduleDayDataList.size) {
            scheduleDayDataList[it].scheduleEntities.size
        }
    }
}

data class ScheduleDayData(
    val scheduleEntities: List<ScheduleEntity> = emptyList(),
) {
    val startTimeOfDay: LocalDateTime by lazy {
        scheduleEntities.first().startTime
    }
    val endTimeOfDay: LocalDateTime by lazy {
        scheduleEntities.last().endTime
    }
}

data class ScheduleEntity(
    val name: String,
    val description: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val color: Color = Color.Gray,
)
