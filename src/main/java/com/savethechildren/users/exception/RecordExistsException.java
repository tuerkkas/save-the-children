package com.savethechildren.users.exception;

public class RecordExistsException extends  Exception {

    public RecordExistsException(){
        super  ();
    }

    /**
     * Used to throw the Exception with a personalized message.
     */
    public RecordExistsException(String message) {
        super(message);
    }
}
