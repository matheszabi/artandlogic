package com.artandlogic.part1.engine;

/**
 * Created by matheszabi on Nov/8/2015 0008.
 */
public class Decoder {

    /**
     * Recombine bytes to return the corresponding integer between [-8192..+8191]
     * @param hi in the range [0x00..0x7F]
     * @param low in the range [0x00..0x7F]
     * @return
     */
    public static int decode(byte hi, byte low){
        // check the params:
        if( (hi & 0x80) > 0){
            throw  new IllegalArgumentException("Param \"hi\" has a leading bit 1, which is not accepted!");
        }
        if( (low & 0x80) > 0){
            throw  new IllegalArgumentException("Param \"low\" has a leading bit 1, which is not accepted!");
        }

        //"recombine them to return the  corresponding integer between [-8192..+8191]"
        return (( hi << 7) | low ) - 8192;
    }



    public static int decode2bytes(int twoBytes){
        return decode((byte) ((twoBytes>>8) & 0x7F), (byte) (twoBytes & 0x7F));
    }
}
