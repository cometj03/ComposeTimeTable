package com.cometj03.composetimetable

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

// For Just Test
val mondayDate = LocalDate.of(2022, 11, 7)

val timeTableData = TimeTableData(
    listOf(
        ScheduleDayData(
            LocalDateTime.of(mondayDate, LocalTime.of(0, 0)),
            listOf(
                ScheduleEntity(
                    "컴수",
                    "정보관 201호",
                    LocalDateTime.of(mondayDate, LocalTime.of(13, 30)),
                    LocalDateTime.of(mondayDate, LocalTime.of(15, 0))
                )
            )
        )
    )
)