package com.gardener.uitestbasics

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.gardener.uitestbasics.ImageViewHasDrawableMatcher.hasDrawable
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CameraIntentActivityTest{

    @get: Rule
    val intentsTestRule = IntentsTestRule(CameraIntentActivity::class.java)

    @Test
    fun test_cameraIntent_isBitmapSetToImageView() {

        // GIVEN
        val activityResult = createImageCaptureActivityResultStub()
        // we only have single action
        val expectedIntent: Matcher<Intent> = hasAction(MediaStore.ACTION_IMAGE_CAPTURE)
        intending(expectedIntent).respondWith(activityResult)

        // Execute and Verify
        onView(withId(R.id.image)).check(matches(not(hasDrawable())))
        onView(withId(R.id.button_launch_camera)).perform(click())
        intending(expectedIntent)
        onView(withId(R.id.image)).check(matches(hasDrawable()))
    }

    private fun createImageCaptureActivityResultStub(): Instrumentation.ActivityResult {
        val bundle = Bundle()
        bundle.putParcelable(
            KEY_IMAGE_DATA,
            BitmapFactory.decodeResource(
                intentsTestRule.activity.resources,
                R.drawable.ic_launcher_background
            )
        )
        val result = Intent()
        result.putExtras(bundle)
        return Instrumentation.ActivityResult(Activity.RESULT_OK, result)
    }
}