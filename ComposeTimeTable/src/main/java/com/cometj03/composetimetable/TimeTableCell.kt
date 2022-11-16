package com.cometj03.composetimetable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

@Composable
fun TimeTableCell(
    cellData: ScheduleEntity,
    modifier: Modifier = Modifier,
    onClick: (ScheduleEntity) -> Unit = {},
) {
    Column(
        modifier = modifier
            .height(80.dp)
            .width(80.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(cellData.color)
            .padding(4.dp)
            .clickable { onClick(cellData) }
    ) {
        Text(text = cellData.name)
        Spacer(Modifier.height(4.dp))
        Text(text = cellData.description)
    }
}

class TimeTableParentData(
    val duration: Float,
    val offset: Float
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = this@TimeTableParentData
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScheduleTimeCellPreview() {
    val data = timeTableData.scheduleDayData.get(0).scheduleEntities.get(0)

    Row(Modifier.fillMaxWidth()) {
        repeat(7) {
            TimeTableCell(cellData = data)
        }
    }
}