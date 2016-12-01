package fr.unice.polytech.si3.qgl.iaad.Exception;

/**
 * @author Gaetan Vialon
 *         Created the 30/11/2016.
 */
public class InvalidIndexException extends Exception {

    public InvalidIndexException(){
        super("Index Invalid");
    }

    public InvalidIndexException(int index){
        super("Index " + index +" Invalid");
    }

    public InvalidIndexException(int index, String action){
        super("For " + action + ", the index : " + index + " is invalid");
    }

}
