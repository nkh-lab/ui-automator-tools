package com.example.nkh.uiautomatortools;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ToolsInstrumentedTest {
    private static final String TAG = "ToolsInstrumentedTest";
    private static UiDevice device;

    @BeforeClass
    public static void init() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void dumpWindowHierarchy() {
        OutputStream output = new OutputStream() {
            private final StringBuilder string = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                this.string.append((char) b);
            }

            public String toString() {
                return this.string.toString();
            }
        };

        try {
            device.dumpWindowHierarchy(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, output.toString());
    }

    @Test
    public void findByResourceIdAndClick() {
        // From dumpWindowHierarchy() output:
        // resource-id="com.android.systemui:id/home_button
        UiObject2 icon = device.findObject(By
                .res("com.android.systemui", "home_button")
        );

        if (icon.isEnabled() && icon.isClickable()) {
            icon.click();
        }
    }
}