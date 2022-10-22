package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_noTasks_returnsZero() {
        // given
        val tasks = emptyList<Task>()

        // when
        val result = getActiveAndCompletedStats(tasks)

        // then
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_nullTasks_returnsZero() {
        // when
        val result = getActiveAndCompletedStats(null)

        // then
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_noCompletedTasks_returnsZero() {
        // given
        val tasks = listOf<Task>(
            Task("title", "description", isCompleted = false)
        )

        // when
        val result = getActiveAndCompletedStats(tasks)

        // then
        assertThat(result.activeTasksPercent, `is`(100f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_allCompletedTasks_returnsHundred() {
        // given
        val tasks = listOf<Task>(
            Task("title", "description", isCompleted = true)
        )

        // when
        val result = getActiveAndCompletedStats(tasks)

        // then
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_halfCompletedTasks_returnsFifty() {
        // given
        val tasks = listOf<Task>(
            Task("title", "description", isCompleted = true),
            Task("title", "description", isCompleted = true),
            Task("title", "description", isCompleted = false),
            Task("title", "description", isCompleted = false),
            Task("title", "description", isCompleted = false),
            Task("title", "description", isCompleted = false),
            Task("title", "description", isCompleted = false),
            Task("title", "description", isCompleted = false)
        )

        // when
        val result = getActiveAndCompletedStats(tasks)

        // then
        assertThat(result.activeTasksPercent, `is`(75f))
        assertThat(result.completedTasksPercent, `is`(25f))
    }
}