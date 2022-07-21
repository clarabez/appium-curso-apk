package com.example.cursoappium

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainMenuTest {

    val device: UiDevice
        get() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testIsAppLoaded(){
        assertTrue(device.hasObject(By.text("CADASTRAR PESSOA")))
    }

    @Test
    fun testAreButtonsDisplayed(){
        assertTrue(device.hasObject(By.text("CADASTRAR PESSOA")))
        assertTrue(device.hasObject(By.text("LISTAR CADASTROS")))
        assertTrue(device.hasObject(By.text("SOBRE O APLICATIVO")))
        assertTrue(device.hasObject(By.text("ACESSAR O CURSO")))
    }

}