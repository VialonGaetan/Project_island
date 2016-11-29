package fr.unice.polytech.si3.qgl.iaad.init;

/**
 * Created on 10/11/2016.
 * @author Gaetan VIALON
 */
/**
 * Contract contains :
 * amount
 * ressource
 */

public class Contract {
    private int amount;
    private String resource;

    /**
     * param amount and ressource
     */

    public Contract(int amount, String resource){
        this.amount=amount;
        this.resource=resource;
    }

    /**
     * Recupere le nombre de ressource que veut le client
     * @return nombre (entier)
     */
    public int getAmount() { return amount; }

    /**
     * Recupere le type de ressource du client
     * @return String de la ressource
     */
    public String getResource() { return resource; }
}
