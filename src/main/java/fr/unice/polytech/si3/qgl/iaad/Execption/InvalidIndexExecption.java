package fr.unice.polytech.si3.qgl.iaad.Execption;

/**
 * @author Gaetan Vialon
 *         Created the 30/11/2016.
 */
public class InvalidIndexExecption extends Exception {

    public InvalidIndexExecption(){
        super("Index Invalid");
    }

    public InvalidIndexExecption(int index){
        super("Index " + index +" Invalid");
    }

    public InvalidIndexExecption(int index,String action){
        super("For " + action + ", the index : " + index + " is invalid");
    }

}
