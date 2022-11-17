package com.cometj03.composetimetable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
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
            .clip(RoundedCornerShape(4.dp))
            .background(cellData.color)
            .clickable { onClick(cellData) }
            .padding(4.dp)
    ) {
        Text(
            text = cellData.name,
            style = MaterialTheme.typography.caption
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = cellData.description,
            style = MaterialTheme.typography.overline
        )
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
    val data = timeTableData.scheduleDayData.get(0).scheduleEntities.get(1)

    Row(
        Modifier.fillMaxWidth()
    ) {
        repeat(5) {
            TimeTableCell(cellData = data)
        }
    }
}