package com.cometj03.composetimetable

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

// For Just Test
val mondayDate = LocalDate.of(2022, 11, 7)

val timeTableData = TimeTableData(
    listOf(
        ScheduleDayData(
            listOf(
                ScheduleEntity(
                    "확률과 통계",
                    "정보관 201호",
                    LocalDateTime.of(mondayDate, LocalTime.of(10, 30)),
                    LocalDateTime.of(mondayDate, LocalTime.of(11, 45))
                ),
                ScheduleEntity(
                    "컴퓨터수학2",
                    "정보관 201호",
                    LocalDateTime.of(mondayDate, LocalTime.of(13, 30)),
                    LocalDateTime.of(mondayDate, LocalTime.of(15, 0))
                ),
                ScheduleEntity(
                    "사용자 인터페이스 및 실습 (다)",
                    "정보관 205호",
                    LocalDateTime.of(mondayDate, LocalTime.of(16, 30)),
                    LocalDateTime.of(mondayDate, LocalTime.of(19, 0))
                )
            )
        )
    )
)