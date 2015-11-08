package com.artandlogic.part1.mainactivityhelpers;

import com.artandlogic.part1.engine.EncoderConstants;

/**
 * Validator class for user input.
 * The user must enter a valid value for Encoder.encode() method.
 * <p/>
 * This implementation could be called at when the user type the digits too, not only on click.
 * <p/>
 * Created by matheszabi on Nov/8/2015 0008.
 */
public class EncodeStringValidator {


    /**
     * Validates a string entered by user.
     *
     * @param userInput should be a value between EncoderConstants.MIN_VALUE and EncoderConstants.MAX_VALUE
     * @return null if is valid or an error message.
     */
    public static String validate(String userInput) {
        if (userInput == null || userInput.trim().length() == 0) {
            return "Please enter a signed integer";
        }
        String str = userInput.trim();

        String errorMessage = null;
        try {
            // can be used + - and the 0x signs too:
            int value = Integer.decode(str);

            // check the value:
            if (value < EncoderConstants.MIN_VALUE) {
                errorMessage = "To low, min is: " + EncoderConstants.MIN_VALUE;
            } else if (value > EncoderConstants.MAX_VALUE) {
                errorMessage = "To high, max is: " + EncoderConstants.MAX_VALUE;
            }

            // value it is accepted: leave the error message with null value

        } catch (NumberFormatException nfe) {
            errorMessage = "Please enter a number";
        }
        return errorMessage;
    }
}
