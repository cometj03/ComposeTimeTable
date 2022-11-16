package com.cometj03.composetimetable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

@Composable
fun TimeTableColumn(
    cellCount: Int,
    hoursLabelHeight: Int, // TODO: hoursLabelHeight 다른 곳에서 받아오게 변경하기
    timeCell: @Composable TimeTableColumnScope.(Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val timeCells = @Composable {
        repeat(cellCount) { TimeTableColumnScope.timeCell(it) }
    }

    Layout(
        modifier = modifier,
        content = timeCells
    ) { measurables, constraints ->

        // Measure Step
        var totalHeight = 0

        val cellPlaceables = measurables.map { measurable ->
            val cellParentData = measurable.parentData as TimeTableParentData
            val cellHeight = (cellParentData.duration * hoursLabelHeight).roundToInt()
            val offset = (cellParentData.offset * hoursLabelHeight).roundToInt()

            totalHeight += cellHeight + offset

            measurable.measure(
                constraints.copy(
                    maxHeight = cellHeight,
                    minHeight = cellHeight
                )
            )
        }

        // Place Step
        val columnWidth = cellPlaceables.first().width

        layout(columnWidth, totalHeight) {
            var yPosition = 0

            cellPlaceables.forEach { cellPlaceable ->
                val cellParentData = cellPlaceable.parentData as TimeTableParentData
                val offset = (cellParentData.offset * hoursLabelHeight).roundToInt()
                yPosition += offset

                cellPlaceable.placeRelative(0, yPosition)

                yPosition += cellPlaceable.height
            }
        }
    }
}

object TimeTableColumnScope {
    @Stable
    fun Modifier.timeTableCell(
        beforeCellEnd: LocalDateTime,
        start: LocalDateTime,
        end: LocalDateTime,
        hoursLabelSize: Int,
    ): Modifier {
        val durationInHours = ChronoUnit.MINUTES.between(start, end) / 60f
        val offset = ChronoUnit.MINUTES.between(beforeCellEnd, start) / 60f

        return then(
            TimeTableParentData(
                duration = durationInHours / hoursLabelSize,
                offset = offset / hoursLabelSize
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun TimeTableColumnPreview() {
    val scheduleData = timeTableData.scheduleDayData[0].scheduleEntities
    val earliestHour = timeTableData.earliestStartHour
    val latestHour = timeTableData.latestEndHour
    val hours = (earliestHour..latestHour).toList()

    TimeTableColumn(
        cellCount = scheduleData.size,
        hoursLabelHeight = 2500,
        timeCell = {
            val data = scheduleData[it]
            val beforeCellEnd = if (it == 0) {
                data.startTime
            } else {
                scheduleData[it - 1].endTime
            }
            TimeTableCell(
                cellData = data,
                modifier = Modifier.timeTableCell(
                    data.startTime,
                    data.startTime,
                    data.endTime,
                    hours.size
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    )
}