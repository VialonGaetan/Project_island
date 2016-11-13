package fr.unice.polytech.si3.qgl.iaad;

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

    public int getAmount() { return amount; }
    public String getResource() { return resource; }
}
