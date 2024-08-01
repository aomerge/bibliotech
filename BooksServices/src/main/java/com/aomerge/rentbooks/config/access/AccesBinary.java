package com.aomerge.rentbooks.config.access;

public class AccesBinary extends PermitionBinary {
    private byte userPermition = 0;

    public void setPermition(byte permition) {
       userPermition |= permition;
    }

    public void removePermition(byte permition) {
        userPermition &= ~permition;
    }

    public boolean hasPermition(byte permition) {
        return (userPermition & permition) == permition;
    }
}
