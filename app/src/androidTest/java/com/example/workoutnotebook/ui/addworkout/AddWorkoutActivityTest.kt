package com.example.workoutnotebook.ui.addworkout


import android.app.Instrumentation
import android.content.Context
import android.widget.TextView
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.workoutnotebook.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AddWorkoutActivityTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<AddWorkoutActivity> =
        ActivityScenarioRule(AddWorkoutActivity::class.java)

    private val context: Context = ApplicationProvider.getApplicationContext()
    private val errorTitle: String = context.getString(R.string.text_error_add_workout_empty_title)

    @Test
    fun isActivityInView() {
        onView(withId(R.id.add_workout_activity))
            .check(matches(isDisplayed()))
    }

    @Test
    fun visibilityTitleDescriptionInputFieldContinueButton() {
        onView(withId(R.id.add_workout_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.add_workout_description))
            .check(matches(isDisplayed()))

        onView(withId(R.id.workout_title_field))
            .check(matches(isDisplayed()))

        onView(withId(R.id.button_confirm_workout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun isTitleTextCorrect() {
        onView(withId(R.id.add_workout_title))
            .check(matches(withText(R.string.text_add_workout_title)))
    }

    @Test
    fun isDescriptionTextCorrect() {
        onView(withId(R.id.add_workout_title))
            .check(matches(withText(R.string.text_add_workout_description)))
    }

    @Test
    fun isErrorMessageCorrect() {
        onView(withId(R.id.button_confirm_workout))
            .perform(click())

        onView(hasErrorText(errorTitle))
            .check(matches(isDisplayed()))
    }
}