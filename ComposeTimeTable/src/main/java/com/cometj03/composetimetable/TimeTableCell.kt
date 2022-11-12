package com.cometj03.composetimetable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun TimeTableCell(
    cellData: ScheduleData,
    onClick: (ScheduleData) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(2.dp))
            .background(cellData.color)
            .padding(
                vertical = 8.dp,
                horizontal = 4.dp
            )
            .clickable { onClick(cellData) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = cellData.name)
        Spacer(Modifier.height(4.dp))
        Text(text = cellData.description)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScheduleTimeCellPreview() {
    val data = scheduleData.get(0).schedules.get(0)

    Row(Modifier.fillMaxWidth()) {
        repeat(7) {
            TimeTableCell(cellData = data)
        }
    }
}