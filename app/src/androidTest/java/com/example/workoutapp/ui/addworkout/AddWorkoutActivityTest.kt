package com.example.workoutapp.ui.addworkout


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.codingwithmitch.espressouitestexamples.ToastMatcher
import com.example.workoutapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AddWorkoutActivityTest{

    @get: Rule
    val activityRule:ActivityScenarioRule<AddWorkoutActivity> = ActivityScenarioRule(AddWorkoutActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.add_workout_activity))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_title_inputField_continueButton(){
        onView(withId(R.id.add_workout_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.workout_title_field))
            .check(matches(isDisplayed()))

        onView(withId(R.id.button_confirm_workout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_isTitleTextCorrect() {
        onView(withId(R.id.add_workout_title))
            .check(matches(withText(R.string.text_add_workout_title)))
    }

    @Test
    fun test_is_toastMessage_correct() {
        onView(withId(R.id.button_confirm_workout))
            .perform(click())

        onView(withText(R.string.text_toast_add_workout))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }
}