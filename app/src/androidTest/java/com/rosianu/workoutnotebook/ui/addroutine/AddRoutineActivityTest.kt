package com.rosianu.workoutnotebook.ui.addroutine

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.rosianu.workoutnotebook.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class AddRoutineActivityTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<AddRoutineActivity> = ActivityScenarioRule(
        AddRoutineActivity::class.java
    )

    private val context: Context = ApplicationProvider.getApplicationContext()
    private val errorText: String = context.getString(R.string.text_field_cannotEmpty)

    @Test
    fun isActivityInView() {
        onView(withId(R.id.add_routine_activity))
            .check(matches(isDisplayed()))
    }

    @Test
    fun nextRoutineClickedSuccess() {
        onView(withId(R.id.routine_name))
            .check(matches(isDisplayed()))
            .perform(typeText("name"))

        onView(withId(R.id.routine_sets))
            .check(matches(isDisplayed()))
            .perform(typeText("sets"))

        onView(withId(R.id.routine_reps))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .perform(typeText("reps"))

        onView(withId(R.id.routine_weight))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .perform(typeText("weight"))

        onView(withId(R.id.routine_rest))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .perform(typeText("rest"))

        onView(withId(R.id.button_next_routine))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.routine_name))
            .check(matches(isDisplayed()))
            .check(matches(withText("")))

        onView(withId(R.id.routine_sets))
            .check(matches(isDisplayed()))
            .check(matches(withText("")))

        onView(withId(R.id.routine_reps))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .check(matches(withText("")))

        onView(withId(R.id.routine_weight))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .check(matches(withText("")))

        onView(withId(R.id.routine_rest))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .check(matches(withText("")))
    }

    @Test
    fun nextRoutineClickedFailName() {
        onView(withId(R.id.button_next_routine))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.routine_name))
            .check(matches(hasFocus()))

        onView(hasErrorText(errorText))
            .check(matches(isDisplayed()))
    }

    @Test
    fun nextRoutineClickedFailSets() {
        onView(withId(R.id.routine_name))
            .check(matches(isDisplayed()))
            .perform(typeText("name"))

        onView(withId(R.id.button_next_routine))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.routine_sets))
            .check(matches(hasFocus()))

        onView(hasErrorText(errorText))
            .check(matches(isDisplayed()))
    }

    @Test
    fun nextRoutineClickedFailReps() {
        onView(withId(R.id.routine_name))
            .check(matches(isDisplayed()))
            .perform(typeText("name"))

        onView(withId(R.id.routine_sets))
            .check(matches(isDisplayed()))
            .perform(typeText("sets"))

        onView(withId(R.id.button_next_routine))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.routine_reps))
            .perform(scrollTo())
            .check(matches(hasFocus()))

        onView(hasErrorText(errorText))
            .check(matches(isDisplayed()))
    }

    @Test
    fun nextRoutineClickedFailWeight() {
        onView(withId(R.id.routine_name))
            .check(matches(isDisplayed()))
            .perform(typeText("Bench Press"))

        onView(withId(R.id.routine_sets))
            .check(matches(isDisplayed()))
            .perform(typeText("4"))

        onView(withId(R.id.routine_reps))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .perform(typeText("12 12 12 12"))

        onView(withId(R.id.button_next_routine))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.routine_weight))
            .perform(scrollTo())
            .check(matches(hasFocus()))

        onView(hasErrorText(errorText))
            .check(matches(isDisplayed()))
    }

    @Test
    fun nextRoutineClickedFailRest() {
        onView(withId(R.id.routine_name))
            .check(matches(isDisplayed()))
            .perform(typeText("Bench Press"))

        onView(withId(R.id.routine_sets))
            .check(matches(isDisplayed()))
            .perform(typeText("4"))

        onView(withId(R.id.routine_reps))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .perform(typeText("12 12 12 12"))

        onView(withId(R.id.routine_weight))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .perform(typeText("2 mins"))

        onView(withId(R.id.button_next_routine))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.routine_rest))
            .perform(scrollTo())
            .check(matches(hasFocus()))

        onView(hasErrorText(errorText))
            .check(matches(isDisplayed()))
    }

    @Test
    fun saveRoutineClickedFailName() {
        onView(withId(R.id.button_save_routine))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.routine_name))
            .check(matches(hasFocus()))

        onView(hasErrorText(errorText))
            .check(matches(isDisplayed()))
    }

    @Test
    fun saveRoutineClickedFailSets() {
        onView(withId(R.id.routine_name))
            .check(matches(isDisplayed()))
            .perform(typeText("name"))

        onView(withId(R.id.button_save_routine))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.routine_sets))
            .check(matches(hasFocus()))

        onView(hasErrorText(errorText))
            .check(matches(isDisplayed()))
    }

    @Test
    fun saveRoutineClickedFailReps() {
        onView(withId(R.id.routine_name))
            .check(matches(isDisplayed()))
            .perform(typeText("name"))

        onView(withId(R.id.routine_sets))
            .check(matches(isDisplayed()))
            .perform(typeText("sets"))

        onView(withId(R.id.button_save_routine))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.routine_reps))
            .perform(scrollTo())
            .check(matches(hasFocus()))

        onView(hasErrorText(errorText))
            .check(matches(isDisplayed()))
    }

    @Test
    fun saveRoutineClickedFailWeight() {
        onView(withId(R.id.routine_name))
            .check(matches(isDisplayed()))
            .perform(typeText("Bench Press"))

        onView(withId(R.id.routine_sets))
            .check(matches(isDisplayed()))
            .perform(typeText("4"))

        onView(withId(R.id.routine_reps))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .perform(typeText("12 12 12 12"))

        onView(withId(R.id.button_save_routine))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.routine_weight))
            .perform(scrollTo())
            .check(matches(hasFocus()))

        onView(hasErrorText(errorText))
            .check(matches(isDisplayed()))
    }

    @Test
    fun saveRoutineClickedFailRest() {
        onView(withId(R.id.routine_name))
            .check(matches(isDisplayed()))
            .perform(typeText("Bench Press"))

        onView(withId(R.id.routine_sets))
            .check(matches(isDisplayed()))
            .perform(typeText("4"))

        onView(withId(R.id.routine_reps))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .perform(typeText("12 12 12 12"))

        onView(withId(R.id.routine_weight))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .perform(typeText("2 mins"))

        onView(withId(R.id.button_save_routine))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.routine_rest))
            .perform(scrollTo())
            .check(matches(hasFocus()))

        onView(hasErrorText(errorText))
            .check(matches(isDisplayed()))
    }

}