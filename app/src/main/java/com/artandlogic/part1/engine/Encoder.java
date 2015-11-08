package com.artandlogic.part1.engine;

/**
 * Created by matheszabi on Nov/8/2015 0008.
 */
public class Encoder implements EncoderConstants{


    /**
     * Convert an integer into a special text encoding.
     * @param signedInteger14bit signed integer in the 14-bit range [-8192..+8191]
     * @return a 4 character string.
     */
    public static String encode(int signedInteger14bit){
        //  verify the param:
        if(signedInteger14bit < MIN_VALUE){
            throw new IllegalArgumentException("signedInteger14bit value is to low. The minimum accepted value is: -8192");
        }
        else if(signedInteger14bit > MAX_VALUE){
            throw new IllegalArgumentException("signedInteger14bit value is to high. The maximum accepted value is: 8191");
        }
        // "1. Add 8192 to the raw value, so its range is translated to [0..16383]"
        signedInteger14bit += 8192; // stack value modify , do not use another stack variable

        // "2. Pack that value into two bytes such that the most significant bit of each is cleared"
        // why to create another 2 variable? - the processor is working with ints ( 32 bit) ! - not shorts
        // or a 16 bit processor is very-very old, deprecated system must be...  I don't want to work with those systems.


        // here is the requested implementation:

        //short unencodedIntermediateValue = (short) (signedInteger14bit & 0x3FFF); // waste of processor time.
        //short encodedValue = (short) (((unencodedIntermediateValue  & 0x3F80) << 1) | (unencodedIntermediateValue & 0x7F)); // even more ...

        // instead of 2, use 4 bytes, it is Android, so 32/64 bits. Anyway the processor is using his registry which is 32 or 64 bytes and Integer.toHexString:
        // use binary | instead of + arithmetical operator, because it is faster:
        int hex = (((signedInteger14bit << 1)  & 0x7F00) ) | (signedInteger14bit & 0x7F); // only 1 variable created and only in stack

        String hexString =  Integer.toHexString(hex);
        hexString = hexString.toUpperCase();// 7f7f -> 7F7F
        // check to be 4 characters
        switch (hexString.length()){
            case 1:
                return "000"+hexString;
            case 2:
                return "00"+hexString;
            case 3:
                return "0"+hexString;
            case 4:
                return hexString;
            default:
                throw new IllegalStateException("Parameters checking probably wrong at encode(), please review it!");
        }
    }

}
