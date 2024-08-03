package com.aomerge.userservices.config.access;

public class AccesBinary extends PermitionBinary {

    public AccesBinary() {
        super();
    }

    private byte userPermition = 0;

    public Byte getPermition() {
        return userPermition;
    }

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
