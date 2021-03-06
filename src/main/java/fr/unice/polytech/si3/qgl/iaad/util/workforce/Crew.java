package fr.unice.polytech.si3.qgl.iaad.util.workforce;

import fr.unice.polytech.si3.qgl.iaad.util.contract.Basket;
import fr.unice.polytech.si3.qgl.iaad.util.contract.ManufacturedContract;
import fr.unice.polytech.si3.qgl.iaad.util.contract.PrimaryContract;
import fr.unice.polytech.si3.qgl.iaad.util.map.Compass;
import fr.unice.polytech.si3.qgl.iaad.util.resource.Manufactured;
import fr.unice.polytech.si3.qgl.iaad.util.resource.PrimaryResource;

import java.awt.*;
import java.util.Map;

/**
 * @author Theo Cholley
 */

public class Crew{

    private final int people;
    private final Point location;
    private final Basket<PrimaryResource> basket;
    private final Point homePort;

    /**
     * Constructor :
     * @param people : The number of marines
     * @param homePort The starting point
     */
    public Crew(int people, Point homePort)
    {
        this.people=people;
        this.location = homePort;
        this.homePort = new Point(homePort);
        this.basket = new Basket<>(PrimaryResource.class);
    }

    public Crew(Crew crew)
    {
        this(crew.getPeople(), crew.getLocation());
        this.basket.addAll(crew.basket);
    }

    /**
     *
     * @return the distance from the crew to the homePort
     */
    public double distanceToHomePort()
    {
        return location.distance(homePort);
    }

    /**
     *
     * @return the number of marines
     */
    public int getPeople() {
        return this.people;
    }

    /**
     *
     * @return The current location
     */
    public Point getLocation() {
        return new Point(this.location);
    }

    /**
     * Can actualise the current marines' location
     * @param direction
     */
    public void move(Compass direction) {
        this.location.translate(direction.getVector().x, direction.getVector().y);
    }

    /**
     * Add in the basket a given Resource and the number of collected ressources
     * @param primaryResource
     * @param quantity
     */
    public void collect(PrimaryResource primaryResource, int quantity)
    {
        basket.add(primaryResource, quantity);
    }

    /**
     * Actualise the content of the basket
     * @param manufactured : the resource created
     * @param quantity : the number of manufactured ressources crafted
     */
    public void craft(Manufactured manufactured, int quantity)
    {
        for (Map.Entry<PrimaryResource, Double> entry : manufactured.getRecipe().entrySet())
        {
            basket.remove(entry.getKey(), (int) Math.ceil(entry.getValue() * quantity));
        }
    }

    public boolean canComplete(PrimaryContract primaryContract)
    {
        return basket.get(primaryContract.getResource()) >= primaryContract.getRemainingQuantity();
    }

    public void complete(PrimaryContract primaryContract)
    {
        int quantity = Math.min(basket.get(primaryContract.getResource()), primaryContract.getRemainingQuantity());
        basket.remove(primaryContract.getResource(), quantity);
        primaryContract.collect(quantity);
    }

    /**
     *
     * @param manufacturedContract : manufactured contract
     * @return Boolean : if we have enough primary resource to craft the associated manufactured ressource
     */
    public boolean haveEnoughResourceToTransform(ManufacturedContract manufacturedContract)
    {
        return basket.contains(manufacturedContract.getNecessaryPrimaryResource());
    }

    public void land(Point homePort)
    {
        this.homePort.setLocation(homePort);
        this.location.setLocation(homePort);
    }
}
