package com.cometj03.composetimetable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.math.max
import kotlin.math.min

@Composable
fun ComposeTimeTable(
    dayNames: Array<String>,
    timeTableData: TimeTableData,
    onCellClick: (column: Int, row: Int, ScheduleEntity) -> Unit,
    modifier: Modifier = Modifier,
) {
    val startHour = min(timeTableData.earliestStartHour, tableStartHour)
    val endHour = max(timeTableData.latestEndHour, tableEndHour)
    val hours = (startHour..endHour).toList()

    val cellCountList = timeTableData.entitiesCountList

    require(dayNames.size == cellCountList.size) {
        "size of dayNames should be equal to count of scheduleEntities\n" +
                "dayNames의 길이와 timeTableData.scheduleEntities의 길이가 같아야 합니다"
    }

    TimeTable(
        columnCount = cellCountList.size,
        dayHeader = {
            DayHeader(dayNames[it])
        },
        hoursLabel = {
            HoursLabel(hours)
        },
        cellCountList = cellCountList,
        timeCell = { column, index ->
            val dayData = timeTableData.scheduleDayDataList[column]
            val scheduleEntity = dayData.scheduleEntities[index]

            val beforeCellEndTime = if (index == 0) {
                LocalTime.of(hours.first(), 0)
            } else {
                dayData.scheduleEntities[index - 1].endTime
            }

            TimeTableCell(
                cellData = scheduleEntity,
                onCellClick = { entity -> onCellClick(column, index, entity) },
                modifier = Modifier
                    .padding(minBorderWidth)
                    .timeTableCell(
                        beforeCellEndTime,
                        scheduleEntity.startTime,
                        scheduleEntity.endTime,
                        hours.size
                    )
            )
        },
        modifier = modifier
    )
}

@Composable
fun DayHeader(dayName: String) {
    Text(
        text = dayName,
        style = MaterialTheme.typography.subtitle1, // TODO: TextStyle
        modifier = Modifier
            .padding(vertical = 4.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun HoursLabel(hours: List<Int>) {
    Column(Modifier.width(IntrinsicSize.Min)) {
        hours.forEach {
            Divider()
            Text(
                text = "$it",
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .width(40.dp)
                    .height(60.dp)
                    .padding(4.dp),
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TimeTablePreview() {
    val scrollState = rememberScrollState()

    MaterialTheme {
        ComposeTimeTable(
            dayNames = arrayOf("월", "화", "수", "목", "금", "토", "일"),
            timeTableData = timeTableData,
            onCellClick = { _, _, _ -> },
            modifier = Modifier
                .verticalScroll(scrollState)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OneColumnTest() {
    val data = TimeTableData(
        ScheduleDayData(
            ScheduleEntity(
                "Test",
                "desc",
                LocalTime.of(10, 30),
                LocalTime.of(13, 0)
            )
        )
    )

    MaterialTheme {
        ComposeTimeTable(
            dayNames = arrayOf("Mon"),
            timeTableData = data,
            onCellClick = { _, _, _ -> })
    }
}