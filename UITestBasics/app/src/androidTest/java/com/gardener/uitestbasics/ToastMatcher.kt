package com.gardener.uitestbasics

import android.os.IBinder
import android.view.WindowManager
import androidx.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

// it only requires one parameter we are using instead of BoundedMatcher
class ToastMatcher : TypeSafeMatcher<Root?>() {

    override fun describeTo(description: Description?) {
        description?.appendText("is toast")
    }

    override fun matchesSafely(item: Root?): Boolean {
        val type: Int? = item?.windowLayoutParams?.get()?.type
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            // retrieve an unique token of that window that view is attached to.

            val windowToken: IBinder = item.decorView.windowToken
            val appToken: IBinder = item.decorView.applicationWindowToken
            if (windowToken === appToken) { // means this window isn't contained by any other windows.
                return true
            }
        }
        return false
    }

}