package com.savethechildren.users.exception;

public class RecordNotFoundException extends Exception{
    /**
     * Used to throw the Exception with a personalized message.
     */
    public RecordNotFoundException(String message){
        super(message);
    }
}
