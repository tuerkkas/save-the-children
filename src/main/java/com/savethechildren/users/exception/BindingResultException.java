package com.savethechildren.users.exception;

import java.util.List;
import java.util.stream.Collectors;

public class BindingResultException extends  Exception {

    private List<String> messageErrors;
    public BindingResultException(){
        super  ();
    }

    /**
     * Used to throw the Exception with a personalized message.
     */
    public BindingResultException(List<String> messageErrors){
        super(messageErrors.stream().collect(Collectors.joining()));
        this.messageErrors = messageErrors;
    }
    public List<String> getMessageErrors() {
        return messageErrors;
    }
}
