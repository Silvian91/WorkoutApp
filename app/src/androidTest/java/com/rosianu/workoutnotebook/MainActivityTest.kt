package com.rosianu.workoutnotebook

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.rosianu.workoutnotebook.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun showWorkoutsButtonIsDisplayed() {
        onView(withId(R.id.action_holder)).check(matches(isDisplayed()))
    }

    @Test
    fun floatButtonIsDisplayed() {
        onView(withId(R.id.fab_menu)).check(matches(isDisplayed()))
    }

    @Test
    fun bottomNavigationIsDisplayed() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
    }

    @Test
    fun homeBottomNavIsDisplayed() {
        onView(withId(R.id.nav_home)).check(matches(isDisplayed()))
    }

    @Test
    fun profileBottomNavIsDisplayed() {
        onView(withId(R.id.nav_profile)).check(matches(isDisplayed()))
    }

    @Test
    fun navToShowWorkouts() {
        onView(withId(R.id.action_holder)).perform(click())

        onView(withId(R.id.show_workout_activity)).check(matches(isDisplayed()))
    }
}