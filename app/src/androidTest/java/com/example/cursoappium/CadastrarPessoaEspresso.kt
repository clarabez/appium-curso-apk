package com.example.cursoappium

import android.widget.Spinner
import androidx.appcompat.widget.AppCompatSpinner
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CadastrarPessoaEspresso {

    val toastMessageSuccess = "Cadastro realizado com sucesso"
    val toastMessageDuplicated = "Email já cadastrado"
    val device: UiDevice
    get() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    // definição da activity a ser testada
    @get:Rule var activityScenarioRule = ActivityScenarioRule(RegisterUsers::class.java)

    private fun getRandomString(length: Int) : String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        return List(length) { charset.random() }
            .joinToString("")
    }

    @Test
    fun testCadastrarPessoaValida(){
        val randomName = getRandomString(10)
        Espresso.onView(ViewMatchers.withId(R.id.TextInputNome))
            .perform(ViewActions.typeText(randomName), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.TextInputEmail))
            .perform(ViewActions.typeText("$randomName@test.com"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.radioButton_mulher)).perform(ViewActions.click())
        // TODO: get random radioButton option
        Espresso.onView(ViewMatchers.withId(R.id.spinner_estados)).perform(ViewActions.click())
        Espresso.onView(withParentIndex(5)).perform(ViewActions.click())
        // TODO: get random values from the list of states
        Espresso.onView(withId(R.id.BotaoCadastrar)).perform(ViewActions.click())

        assertTrue(device.hasObject(By.text(toastMessageSuccess)))
//        Espresso.onView(withText(toastMessageDuplicated))
//            .inRoot(ToastMatcher())
//            .check(matches(withText(toastMessageDuplicated)))
        // TODO: solve inRoot for toast message
    }

    @Test
    fun testCadastrarContaDuplicada(){
        Espresso.onView(withId(R.id.TextInputNome))
            .perform(typeText("Maria"), closeSoftKeyboard())
        Espresso.onView(withId(R.id.TextInputEmail))
            .perform(typeText("maria@test.com"), closeSoftKeyboard())
        Espresso.onView(withId(R.id.radioButton_mulher)).perform(click())
        Espresso.onView(withId(R.id.spinner_estados)).perform(click())
        Espresso.onView(withParentIndex(5)).perform(click())
        Espresso.onView(withId(R.id.BotaoCadastrar)).perform(click())

        assertTrue(device.hasObject(By.text(toastMessageDuplicated)))

//        Espresso.onView(withText("Acre (AC)")).perform(swipeUp())
//            .perform(repeatedlyUntil(swipeUp(),  hasDescendant(
//            withText("Pernambuco (PE)")), 30))
        // TODO: fix swipeUp() until find string
    }

    @Test
    fun testBotaoVoltar(){
        Espresso.onView(withId(R.id.BotaoVoltar))
    }

    @Test
    fun testCadastrarEmailInvalido(){

    }

    @Test
    fun testCadastrarComNomeVazio(){
    }

    fun testCadastrarComEmailVazio(){

    }
}