package com.example.android.baking_app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
@RunWith(AndroidJUnit4.class)
public class app {


        @Test
        public void useAppContext() {
            // Context of the app under test.
            Context appContext = InstrumentationRegistry.getTargetContext();

            assertEquals("com.example.android.baking_app", appContext.getPackageName());
        }
    }

