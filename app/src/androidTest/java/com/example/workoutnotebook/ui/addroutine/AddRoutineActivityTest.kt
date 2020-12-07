package com.example.workoutnotebook.ui.addroutine

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.workoutnotebook.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class AddRoutineActivityTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<AddRoutineActivity> = ActivityScenarioRule(
        AddRoutineActivity::class.java
    )

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.add_routine_activity))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_onContinueClickedSuccessful() {
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

        onView(withId(R.id.weight_measurement))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.routine_rest))
            .perform(scrollTo())
            .check(matches(isDisplayed()))
            .perform(typeText("rest"))

        onView(withId(R.id.button_confirm_routine))
            .check(matches(isDisplayed()))
            .perform(click())
    }

}