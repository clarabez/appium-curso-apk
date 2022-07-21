package com.example.cursoappium

import androidx.test.platform.app.InstrumentationRegistry
import android.app.Activity
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
//    Definição da activity a ser aberta antes da execução do teste
    @get:Rule var activityScenarioRule = ActivityScenarioRule(RegisterUsers::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.cursoappium", appContext.packageName)
    }

    @Test
    fun is_app_loaded() {
        onView(withId(R.id.button_cadastrar))
                .check(matches(isDisplayed()))
    }

    @Test
    fun write_name() {
        onView(withId(R.id.TextInputNome)).perform(typeText("Maria"), closeSoftKeyboard())
        onView(withId(R.id.TextInputEmail)).perform(typeText("teste@maria.com"), closeSoftKeyboard())
        onView(withId(R.id.BotaoCadastrar)).perform(click())

//        onView(withText(R.string.toast_message))
//            .check(matches(isDisplayed()))

    }
}

