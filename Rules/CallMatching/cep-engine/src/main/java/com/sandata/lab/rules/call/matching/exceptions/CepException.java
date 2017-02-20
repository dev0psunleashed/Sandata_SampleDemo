package com.sandata.lab.rules.call.matching.exceptions;

import org.apache.camel.CamelException;

/**
 * Created with IntelliJ IDEA.
 * User: Tom Dornseif
 * Date: 12/15/2015
 * Time: 7:16 AM
 */
public class CepException extends CamelException{
    public CepException(String s) {
        super(s);
    }

    /**
     * Returns a short description of this throwable.
     * The result is the concatenation of:
     * <ul>
     * <li> the {@linkplain Class#getName() name} of the class of this object
     * <li> ": " (a colon and a space)
     * <li> the result of invoking this object's {@link #getLocalizedMessage}
     * method
     * </ul>
     * If {@code getLocalizedMessage} returns {@code null}, then just
     * the class name is returned.
     *
     * @return a string representation of this throwable.
     */
    @Override
    public String toString() {
        String fromSuper = super.toString();
        //TBD SPLUNKAFY
        return fromSuper;
    }
}
