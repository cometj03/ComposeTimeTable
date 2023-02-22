package com.cometj03.composetimetable

import androidx.compose.ui.graphics.Color
import java.time.LocalTime

class TimeTableData(
    vararg val scheduleDayDataList: ScheduleDayData
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
}

class ScheduleDayData(
    vararg val scheduleEntities: ScheduleEntity,
) {
    val startTimeOfDay: LocalTime by lazy {
        scheduleEntities.sortedBy { it.startTime }.first().startTime
    }
    val endTimeOfDay: LocalTime by lazy {
        scheduleEntities.sortedBy { it.endTime }.last().endTime
    }
}

class ScheduleEntity(
    val name: String,
    val description: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val color: Color = Color.Gray,
)
