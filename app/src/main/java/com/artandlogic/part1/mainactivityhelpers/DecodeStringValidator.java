package com.artandlogic.part1.mainactivityhelpers;

/**
 * Validator class for user input.
 * The user must enter a valid value for Decoder.decode() method.
 * <p/>
 * Created by matheszabi on Nov/8/2015 0008.
 */
public class DecodeStringValidator {


    /**
     * Validates a string entered by user.
     * \"0x\" will be appended as prefix
     * @param userInput a hex string. The integer must have value in binary format at last2 bytes in: 0HHHHHHH 0LLLLLLL
     * @return null if is valid or an error message.
     */
    public static String validate(String userInput) {

        if(userInput == null || userInput.trim().length() == 0 || userInput.trim().length() > 6 ){
            return "Please enter a hex string, max 2 bytes";
        }
        String str = userInput.trim();


        String errorMessage = null;

        try {
            int value = Integer.decode(str);

            if(value > 0x7F7F){
                errorMessage = "Only 2 bytes please! max 0x7F7F\n"+Integer.toBinaryString(value);;
            }
            else if( (value & 0x8000) > 0 ){
                errorMessage = "\"Hi\" byte has a leading 1 bit.\n"+Integer.toBinaryString(value);
            }
            else if( (value & 0x80) > 0 ) {
                errorMessage = "\"Low\" byte has a leading 1 bit.\n"+Integer.toBinaryString(value);
            }

            // value it is accepted: leave the error message with null value

        }catch (NumberFormatException nfe){
            errorMessage = "Please enter a number";
        }

        return errorMessage;
    }
}
