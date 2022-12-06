package com.cometj03.composetimetable

import androidx.compose.ui.graphics.Color
import java.time.LocalTime

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
        scheduleDayDataList.map { it.scheduleEntities.size }
    }
    val dayNameList: List<String> by lazy {
        scheduleDayDataList.map { it.dayName }
    }
}

data class ScheduleDayData(
    val dayName: String,
    val scheduleEntities: List<ScheduleEntity> = emptyList(),
) {
    val startTimeOfDay: LocalTime by lazy {
        scheduleEntities.sortedBy { it.startTime }.first().startTime
    }
    val endTimeOfDay: LocalTime by lazy {
        scheduleEntities.sortedBy { it.endTime }.last().endTime
    }
}

data class ScheduleEntity(
    val name: String,
    val description: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val color: Color = Color.Gray,
)
