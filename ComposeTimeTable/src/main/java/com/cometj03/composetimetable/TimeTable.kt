package com.cometj03.composetimetable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlin.math.max
import kotlin.math.min

@Composable
fun TimeTable(
    timeTableData: TimeTableData,
    onCellClick: (ScheduleEntity) -> Unit,
    modifier: Modifier = Modifier,
) {
    val startHour = min(timeTableData.earliestStartHour, tableStartHour)
    val endHour = max(timeTableData.latestEndHour, tableEndHour)
    val hours = (startHour..endHour).toList()


}