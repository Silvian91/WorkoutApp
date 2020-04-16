package com.example.workoutapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.workoutapp.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(
        MainActivity::class.java)

    @Test
    fun test_visibility_title_addWorkout() {
        onView(withId(R.id.add_workout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_title_showWorkout() {
        onView(withId(R.id.show_workout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_nav_addWorkout() {
        onView(withId(R.id.add_workout)).perform(click())

        onView(withId(R.id.add_workout_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun test_nav_showWorkout() {
        onView(withId(R.id.show_workout)).perform(click())

        onView(withId(R.id.show_workout_activity)).check(matches(isDisplayed()))
    }
}