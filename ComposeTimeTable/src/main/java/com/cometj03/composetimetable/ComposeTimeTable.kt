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
    timeTableData: TimeTableData,
    onCellClick: (dayName: String, ScheduleEntity) -> Unit,
    modifier: Modifier = Modifier,
) = ComposeTimeTable(
    dayNames = timeTableData.dayNameList,
    timeTableData = timeTableData,
    onCellClick = onCellClick,
    modifier = modifier
)

@Composable
fun ComposeTimeTable(
    dayNames: List<String>,
    timeTableData: TimeTableData,
    onCellClick: (dayName: String, ScheduleEntity) -> Unit,
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

    // Box로 한 번 감싸주지 않으면 실제로 적용했을 때 레이아웃이 제대로 보이지 않는 버그가 있음
    // TODO: 원인 찾기
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
                onCellClick = { entity -> onCellClick(dayNames[column], entity) },
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

@Preview
@Composable
fun HoursLabelPreview() {
    HoursLabel(hours = (9..16).toList())
}

@Preview(widthDp = 300)
@Composable
fun TimeTablePreview() {
    val scrollState = rememberScrollState()

    Column {
        ComposeTimeTable(
            timeTableData = timeTableData,
            onCellClick = { _, _ -> Unit },
            modifier = Modifier
                .verticalScroll(scrollState)
                .wrapContentSize()
        )
    }
}