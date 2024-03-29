package com.cometj03.composetimetable

import androidx.compose.ui.graphics.Color
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

// For Just Test
internal val timeTableData = TimeTableData(
    ScheduleDayData(
        ScheduleEntity(
            "컴퓨터수학2",
            "정보관 201호",
            LocalTime.of(13, 30),
            LocalTime.of(15, 0),
            Color(0xFFF58284)
        )
    ),
    ScheduleDayData(
        ScheduleEntity(
            "APE 2",
            "진리관 103호",
            LocalTime.of(11, 0),
            LocalTime.of(12, 30),
            Color(0xFFF5F39B)
        ),
        ScheduleEntity(
            "사용자 인터페이스 및 실습 (다)",
            "정보관 205호",
            LocalTime.of(15, 0),
            LocalTime.of(16, 50),
            Color(0xFFB3DCF5)
        )
    ),
    ScheduleDayData(),
    ScheduleDayData(
        ScheduleEntity(
            "확률과 통계",
            "정보관 201호",
            LocalTime.of(10, 30),
            LocalTime.of(11, 45),
            Color(0xFFA86263)
        )
    ),
    ScheduleDayData(),
    ScheduleDayData(),
    ScheduleDayData(
        ScheduleEntity(
            "확률과 통계",
            "정보관 201호",
            LocalTime.of(8, 30),
            LocalTime.of(9, 45),
            Color(0xFFA86263)
        )
    )
)