package com.cometj03.composetimetable

import androidx.compose.ui.graphics.Color
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

// For Just Test
internal val mondayDate = LocalDate.of(2022, 11, 7)

internal val timeTableData = TimeTableData(
    listOf(
        ScheduleDayData(
            listOf(
                ScheduleEntity(
                    "컴퓨터수학2",
                    "정보관 201호",
                    LocalDateTime.of(mondayDate, LocalTime.of(13, 30)),
                    LocalDateTime.of(mondayDate, LocalTime.of(15, 0)),
                    Color(0xFFF58284)
                )
            )
        ),
        ScheduleDayData(
            listOf(
                ScheduleEntity(
                    "APE 2",
                    "진리관 103호",
                    LocalDateTime.of(mondayDate, LocalTime.of(11, 0)),
                    LocalDateTime.of(mondayDate, LocalTime.of(12, 30)),
                    Color(0xFFF5F39B)
                ),
                ScheduleEntity(
                    "사용자 인터페이스 및 실습 (다)",
                    "정보관 205호",
                    LocalDateTime.of(mondayDate, LocalTime.of(15, 0)),
                    LocalDateTime.of(mondayDate, LocalTime.of(16, 50)),
                    Color(0xFFB3DCF5)
                )
            )
        ),
        ScheduleDayData(),
        ScheduleDayData(
            listOf(
                ScheduleEntity(
                    "확률과 통계",
                    "정보관 201호",
                    LocalDateTime.of(mondayDate, LocalTime.of(10, 30)),
                    LocalDateTime.of(mondayDate, LocalTime.of(11, 45)),
                    Color(0xFFA86263)
                )
            )
        ),
        ScheduleDayData(),
        ScheduleDayData(),
        ScheduleDayData(
            listOf(
                ScheduleEntity(
                    "확률과 통계",
                    "정보관 201호",
                    LocalDateTime.of(mondayDate, LocalTime.of(8, 30)),
                    LocalDateTime.of(mondayDate, LocalTime.of(9, 45)),
                    Color(0xFFA86263)
                )
            )
        )
    )
)