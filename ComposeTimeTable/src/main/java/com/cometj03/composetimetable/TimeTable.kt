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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TimeTable(

) {

}

@Composable
fun Board(
    modifier: Modifier = Modifier,
    scheduleList: List<ScheduleGraphData>
) {

}


/**
 * @param heightUnit height for 1 hour (60 minutes)
 */
@Composable
private fun ScheduleCell(
    data: ScheduleCellData,
    heightUnit: Dp = 30.dp,
    onClick: (ScheduleCellData) -> Unit = {}
) {
    val cellHeight = heightUnit / 60 * data.minutesLength.toInt()

    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(30.dp)
            .height(cellHeight)
            .clip(RoundedCornerShape(2.dp))
            .background(Color.Gray)
            .clickable { onClick(data) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = data.name)
        Spacer(Modifier.height(4.dp))
        Text(text = data.description)
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleCellPreview() {
    val data = scheduleData.get(0).scheduleCellData.get(0)

    Box(modifier = Modifier
        .size(width = 200.dp, height = 300.dp)
        .background(Color.White)
    ) {
        ScheduleCell(data = data)
    }
}