package com.saintjean.operation;

public class IllegalParamISIException extends Exception{
    private int n=0;
    public IllegalParamISIException (String message) throws IllegalParamISIException{
        super (message);
    }
}
