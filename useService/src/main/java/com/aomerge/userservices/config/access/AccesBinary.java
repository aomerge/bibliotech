package com.aomerge.userservices.config.access;

public class AccesBinary extends PermitionBinary {

    public AccesBinary() {
        super();
    }

    private byte userPermition = 0;

    public Byte savePermition(String permition) {
        switch (permition.toUpperCase()) {
            case "USER":
                setPermition(READ);
                setPermition(BORROW);
                setPermition(RETURN);
                break;
            case "EMPLOYEE":
                setPermition(READ);
                setPermition(WRITE);
                setPermition(ADMIN);
                break;
            case "MANAGER":
                setPermition(READ);
                setPermition(WRITE);
                setPermition(DELETE);
                setPermition(INFO);
                setPermition(ADMIN);
                break;
            case "ADMIN":
                setPermition(READ);
                setPermition(WRITE);
                setPermition(DELETE);
                setPermition(INFO);
                setPermition(BORROW);
                setPermition(ADMIN);
                break;
            default:
                throw new IllegalArgumentException("Permition not found"+ permition);


        }
        return userPermition;
    }

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
