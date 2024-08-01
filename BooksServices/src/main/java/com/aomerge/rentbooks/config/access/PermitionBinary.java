package com.aomerge.rentbooks.config.access;

public class PermitionBinary {
     protected final Byte READ =    0b0000001;
     protected final Byte WRITE =   0b0000010;
     protected final Byte DELETE =  0b0000100;
     protected final Byte RESERVE = 0b0001000;
     protected final Byte RETURN =  0b0010000;
     protected final Byte BORROW =  0b0100000;
     protected final Byte ADMIN =   0b1000000;

}
