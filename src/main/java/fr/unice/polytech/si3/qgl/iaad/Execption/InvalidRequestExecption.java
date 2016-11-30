package fr.unice.polytech.si3.qgl.iaad.Execption;

/**
 * @author Gaetan Vialon
 *         Created the 30/11/2016.
 */
public class InvalidRequestExecption extends Exception {

    public InvalidRequestExecption(){
        super("Request Invalid");
    }

    public InvalidRequestExecption(String request){
        super("Request Invalid for " + request);
    }
}
