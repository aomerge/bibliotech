package com.aomerge.rentbooks.config.access;

public class PermitionBinary {
     public final Byte READ =    0b0000001;
     public final Byte WRITE =   0b0000010;
     public final Byte DELETE =  0b0000100;
     public final Byte RESERVE = 0b0001000;
     public final Byte RETURN =  0b0010000;
     public final Byte BORROW =  0b0100000;
     public final Byte ADMIN =   0b1000000;
}
