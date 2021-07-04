package com.example.nkh.uiautomatortools;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExamplesInstrumentedTest {
    private static final String TAG = "ExamplesInstrumentedTest";
    private static final String TEST_APP_PACKAGE = "com.android.chrome";
    private static final String TEST_APP_TEXT = "Chrome";
    private static final String LAUNCHER_PACKAGE = "com.google.android.apps.nexuslauncher";
    private static final long LAUNCH_TIMEOUT_MS = 3000;
    private static UiDevice device;

    @BeforeClass
    public static void init() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Before
    public void goHome() {

        UiObject2 icon = device.findObject(By
                .res("com.android.systemui", "home_button")
        );

        if (icon.isEnabled() && icon.isClickable()) {
            icon.click();
        }

        assertEquals(true, device.wait(Until.hasObject(By.pkg(LAUNCHER_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT_MS));
    }


    @Test
    public void clickAndCheckLaunch() {
        UiObject2 icon = device.findObject(By
                .pkg(LAUNCHER_PACKAGE)
                .text(TEST_APP_TEXT)
        );

        if (icon.isEnabled() && icon.isClickable()) {
            icon.click();
        }

        // check and wait for the app to appear
        assertEquals(true, device.wait(Until.hasObject(By.pkg(TEST_APP_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT_MS));
    }

    @Test
    public void clickAndCheckAlive() {

        UiObject2 icon = device.findObject(By
                .pkg(LAUNCHER_PACKAGE)
                .text(TEST_APP_TEXT)
        );

        if (icon.isEnabled() && icon.isClickable()) {
            icon.click();
        }

        // Wait and check if appear and still alive
        sleepInSec(3);

        assertNotNull(device.findObject(By.pkg(TEST_APP_PACKAGE).depth(0)));
    }

    private void sleepInSec(int value) {
        try {
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}