package com.artandlogic.part1.engine;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.artandlogic.part1.engine.Encoder;

/**
 * Created by matheszabi on Nov/8/2015 0008.
 */
public class EncoderTest extends InstrumentationTestCase {

    public void testEncodeToLow() {

        try {
            Encoder.encode(-8192 - 1);
            fail("encode didn't checked the param lower limits");
        } catch (IllegalArgumentException ex) {
            String msg = ex.getMessage();

            assertNotNull(msg);
            assertEquals("signedInteger14bit value is to low. The minimum accepted value is: -8192", msg);
        }
    }

    public void testEncodeToHigh() {

        try {
            Encoder.encode(8191 + 1);
            fail("encode didn't checked the param upper limits");
        } catch (IllegalArgumentException ex) {
            String msg = ex.getMessage();

            assertNotNull(msg);
            assertEquals("signedInteger14bit value is to high. The maximum accepted value is: 8191", msg);
        }
    }

    public void testEncodeValues() {
        int testingValues[] = new int[]{0, -8192, 8191, 2048, -4096};
        String expectedValues[] = new String[]{"4000", "0000", "7F7F", "5000", "2000"};

        if (testingValues.length != expectedValues.length) {
            fail("Please check the input parameters count, because they don't match");
        }

        for (int i = 0; i < testingValues.length; i++) {
            int curTestingValue = testingValues[i];

            String curResult = Encoder.encode(curTestingValue);

            assertNotNull(curResult);
            assertEquals(expectedValues[i], curResult);
        }
    }

    public void testEncodeValuesRequested() {

        int testingValues[] = new int[]{6111, 340, -2628, -255, 7550};


        for (int i = 0; i < testingValues.length; i++) {
            int curTestingValue = testingValues[i];

            String curResult = Encoder.encode(curTestingValue);

            assertNotNull(curResult);

            Log.d("EncoderTest", "curResult: " + curResult);
        }

        String expectedValuesAreThisOnes[] = new String[]{"6F5F", "4254", "2B3C", "3E01", "7A7E"};
    }
}
