package fr.unice.polytech.si3.qgl.iaad.Exception;

/**
 * @author Gaetan Vialon
 *         Created the 30/11/2016.
 */
public class InvalidRequestException extends Exception {

    public InvalidRequestException(){
        super("Request Invalid");
    }

    public InvalidRequestException(String request){
        super("Request Invalid for " + request);
    }
}
