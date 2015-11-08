package com.artandlogic.part1.engine;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.artandlogic.part1.engine.Decoder;

/**
 * Created by matheszabi on Nov/8/2015 0008.
 */
public class DecoderTest extends InstrumentationTestCase {
    public void testDecodeHiNegative() {
        try {
            Decoder.decode((byte) 0xFF, (byte) 0x00);
            fail("decode didn't checked the param \"hi\"");
        } catch (IllegalArgumentException ex) {
            String msg = ex.getMessage();

            assertNotNull(msg);
            assertEquals("Param \"hi\" has a leading bit 1, which is not accepted!", msg);
        }
    }

    public void testDecodeLowNegative() {
        try {
            Decoder.decode((byte) 0x0, (byte) 0xFF);
            fail("decode didn't checked the param \"low\"");
        } catch (IllegalArgumentException ex) {
            String msg = ex.getMessage();

            assertNotNull(msg);
            assertEquals("Param \"low\" has a leading bit 1, which is not accepted!", msg);
        }
    }

    public void testDecodeValues() {
        byte[] paramHi = new byte[]{0x40, 0x00, 0x7F, 0x50, 0x0A, 0x55};
        byte[] paramLow = new byte[]{0x00, 0x00, 0x7F, 0x00, 0x05, 0x00};
        int[] expectedValues = new int[]{0x00, -8192, 8191, 2048, -6907, 2688};

        if ((paramHi.length != paramLow.length) || (paramHi.length != expectedValues.length)) {
            fail("Please check the input parameters count, because they don't match");
        }

        for (int i = 0; i < paramHi.length; i++) {
            int curValue = Decoder.decode(paramHi[i], paramLow[i]);
            assertEquals(expectedValues[i], curValue);
        }
    }

    public void testDecodeValuesRequested() {

        //byte[] paramHi = new byte[]{0x0A, 0x00, 0x3F, 0x44, 0x5E};
        //byte[] paramLow = new byte[]{0x0A, 0x29, 0x0F, 0x00, 0x7F};

        // "and decoded integers for these hexadecimal values:", so you are requesting other what was specified...
        int[] input = new int[]{0x0A0A, 0x0029, 0x3F0F, 0x4400, 0x5E7F};

        for (int i = 0; i < input.length; i++) {
            Log.d("DecoderTest", "decoded value: " + Decoder.decode2bytes(input[i]));
        }

        int[] output = new int[]{-6902, -8151, -113, 512, 3967};

    }
}
