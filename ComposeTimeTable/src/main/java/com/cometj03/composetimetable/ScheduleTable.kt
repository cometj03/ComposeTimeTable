package com.cometj03.composetimetable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScheduleTable(
    columnCount: Int,
    dayHeader: @Composable (Int) -> Unit,
    hoursLabel: @Composable () -> Unit,
    cellCountList: List<Int>,
    timeCell: @Composable TimeTableColumnScope.(column: Int, index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    require(columnCount == cellCountList.size) {
        "columnCount should be equal to cellCountList's size"
    }

    val dayHeaders = @Composable {
        repeat(columnCount) { dayHeader(it) }
    }
    val timeCells = @Composable {
        repeat(columnCount) { column ->
            repeat(cellCountList[column]) { cellIndex ->
                TimeTableColumnScope.timeCell(column, cellIndex)
            }
        }
    }

    Layout(
        contents = listOf(dayHeaders, hoursLabel, timeCells),
        modifier = modifier,
    ) { (dayHeaderMeasurables, hoursLabelMeasurables, timeCellMeasurables),
        constraints ->

        require(hoursLabelMeasurables.size == 1) {
            "hoursLabel should emit only one composable"
        }

        val hoursLabelPlaceable = hoursLabelMeasurables.first().measure(constraints)
        val dayHeaderPlaceables = dayHeaderMeasurables.map {
            it.measure(constraints)
        }
        val timeCellPlaceables = timeCellMeasurables.map { measurable ->
            val cellParentData = measurable.parentData as TimeTableParentData
            val cellHeight = (cellParentData.duration * hoursLabelPlaceable.height).roundToInt()

            measurable.measure(
                constraints.copy(
                    minHeight = cellHeight,
                    maxHeight = cellHeight
                )
            )
        }

        val headerWidth = dayHeaderPlaceables.first().width
        val headerHeight = dayHeaderPlaceables.first().height
        val totalWidth = hoursLabelPlaceable.width + headerWidth * columnCount
        val totalHeight = hoursLabelPlaceable.height + headerHeight

        layout(width = totalWidth, height = totalHeight) {
            val yPosition = headerHeight
            var xPosition = hoursLabelPlaceable.width
            var cellIndex = 0

            hoursLabelPlaceable.placeRelative(0, yPosition)

            dayHeaderPlaceables.forEachIndexed { index, headerPlaceable ->
                headerPlaceable.placeRelative(xPosition, 0)

                var yOffset = 0
                val cellCount = cellCountList[index]
                repeat(cellCount) {
                    val cellPlaceable = timeCellPlaceables[cellIndex++]
                    val cellParentData = cellPlaceable.parentData as TimeTableParentData
                    val offset = (cellParentData.offset * hoursLabelPlaceable.height).roundToInt()
                    yOffset += offset

                    cellPlaceable.placeRelative(xPosition, yPosition + yOffset)

                    yOffset += cellPlaceable.height
                }

                xPosition += headerPlaceable.width
            }
        }
    }
}

object TimeTableColumnScope {
    @Stable
    fun Modifier.timeTableCell(
        beforeCellEndTime: LocalDateTime,
        start: LocalDateTime,
        end: LocalDateTime,
        hoursLabelSize: Int,
    ): Modifier {
        val durationInHours = ChronoUnit.MINUTES.between(start, end) / 60f
        val offset = ChronoUnit.MINUTES.between(beforeCellEndTime, start) / 60f

        return then(
            TimeTableParentData(
                duration = durationInHours / hoursLabelSize,
                offset = offset / hoursLabelSize
            )
        )
    }
}