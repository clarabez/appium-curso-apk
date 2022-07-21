package com.example.cursoappium

import android.view.WindowManager
import androidx.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class ToastMatcher : TypeSafeMatcher<Root>() {
    override fun matchesSafely(item: Root): Boolean {
        val type = item.windowLayoutParams.get().type
        if (type == WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY) {
            val windowToken = item.decorView.windowToken
            val appToken = item.decorView.applicationWindowToken
            return windowToken === appToken
        }
        return false
    }

    override fun describeTo(description: Description) {
        description.appendText("is toast")
    }
}