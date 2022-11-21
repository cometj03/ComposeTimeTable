package com.cometj03.composetimetable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

@Composable
fun TimeTableCell(
    cellData: ScheduleEntity,
    onCellClick: (ScheduleEntity) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(cellData.color)
            .clickable { onCellClick(cellData) }
            .padding(4.dp)
    ) {
        Text(
            text = cellData.name,
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = cellData.description,
            style = MaterialTheme.typography.caption
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
    val entities = timeTableData.scheduleDayDataList[0].scheduleEntities

    Row(
        Modifier.fillMaxWidth()
    ) {
        repeat(3) {
            TimeTableCell(cellData = entities[it], onCellClick = {})
        }
    }
}