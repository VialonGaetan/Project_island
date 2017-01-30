package fr.unice.polytech.si3.qgl.iaad.aerial;

import fr.unice.polytech.si3.qgl.iaad.Biomes;
import fr.unice.polytech.si3.qgl.iaad.Direction;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.Action;
import fr.unice.polytech.si3.qgl.iaad.actions.Area;
import fr.unice.polytech.si3.qgl.iaad.actions.Scan;
import fr.unice.polytech.si3.qgl.iaad.islandMap.Element;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;

import java.awt.*;

/**
 * On Scan pour trouver une crique.
 * @author Alexandre Clement
 * @since 30/01/2017.
 */
class ScanToFindCreek extends Oriented implements Protocol
{
    ScanToFindCreek(IslandMap map, Direction heading, Direction sense)
    {
        super(map, heading, sense);
    }

    /**
     * @return Scan
     */
    @Override
    public Action nextAction()
    {
        return new Scan();
    }

    /**
     * On ajoute les éléments trouver à la map
     *
     * @param result le résultat de l'action effectué
     * @return Land si on trouve une crique
     * FlyOnBeach si on il y a au moins deux biomes dont un OCEAN
     * Sinon, turn pour revenir vers les côtes
     */
    @Override
    public Protocol setResult(Area result) throws InvalidMapException
    {
        // on ajoute les biomes à la carte
        for (int i = 0; i < result.nbBiomes(); i++)
        {
            getMap().addBiomes(Biomes.valueOf(result.getBiome(i)));
        }
        // Si on trouve une crique, on Land dessus
        if (result.nbCreeks() > 0)
        {
            getMap().addCreeks(result.getCreeks(0));
            return new LandOnIsland(getMap(), getMap().getLocation());
        }
        // Si il n'y a que de l'OCEAN, alors on tourne pour revenir vers une côte
        if (result.nbBiomes() == 1 && Biomes.valueOf(result.getBiome(0)) == Biomes.OCEAN)
            return turn(getSense(), getHeading().getBack());
        // Si il y a au moins deux biomes dont un OCEAN, alors on est sur une côte
        for (int i = 0; i < result.nbBiomes(); i++)
        {
            if (Biomes.valueOf(result.getBiome(i)) == Biomes.OCEAN)
                return new FlyOnBeach(getMap(), getHeading(), getSense());
        }
        // Sinon, on est au dessus de l'île, sans OCEAN et donc, on tourne pour revenir vers une côte
        return turn(getSense().getBack(), getHeading());
    }

    /**
     * Tourne dans le direction voulue, si possible et si cela n'a pas déjà été fait
     *
     * @param target la direction cibler
     * @param sense  le sens de recherche après avoir tournée
     * @return le nouveau protocole
     * @throws InvalidMapException si on sort de la carte
     */
    private Protocol turn(Direction target, Direction sense) throws InvalidMapException
    {
        if (canDroneMoveToAndNotAlreadyVisited(target))
            return turnBuilder(target, sense);

        if (canDroneMoveToAndNotAlreadyVisited(target.getBack()))
            return turnBuilder(target.getBack(), sense.getBack());

        if (getMap().getNumberOfAvailablePoints(getHeading()) > 1)
            return new FlyOnBeach(getMap(), getHeading(), getSense());

        if (canDroneMoveTo(target))
            return turnBuilder(target, sense);

        if (canDroneMoveTo(target.getBack()))
            return turnBuilder(target.getBack(), sense.getBack());

        return new StopAerial();
    }

    /**
     * Calcule la position sur laquelle le drone se situera après avoir tourner dans la direction donnée.
     *
     * @param direction la direction vers laquelle tourne le drone.
     * @return la position d'arrivée.
     */
    private Point turnTo(Direction direction)
    {
        Point position = new Point(getMap().getLocation());
        position.translate(getHeading().getVecteur().x, getHeading().getVecteur().y);
        position.translate(direction.getVecteur().x, direction.getVecteur().y);
        return position;
    }

    /**
     * Vérifie si le drone peut tourner dans la direction donnée.
     *
     * @param direction la direction vers laquelle tourne le drone.
     * @return true si le drone peut tourner dans la direction donnée, false sinon.
     */
    private boolean canDroneMoveTo(Direction direction)
    {

        return getMap().getNumberOfAvailablePoints(getHeading()) > 0 && getMap().getNumberOfAvailablePoints(direction) > 1;
    }

    /**
     * Vérifie si une case a déjà été visitée.
     *
     * @param position la position a vérifié.
     * @return true si la position a déjà été visité, false sinon.
     */
    private boolean alreadyVisited(Point position) throws InvalidMapException
    {
        return getMap().getBiomes(position).length != 0;
    }

    /**
     * Vérifie si le drone peut tourner dans la direction donnée et si la case n'a pas déjà été visitée.
     *
     * @param direction la direction vers laquelle le drone tourne.
     * @return true si le drone peut tourner vers la case qui n'a pas encore été visitée, false sinon.
     */
    private boolean canDroneMoveToAndNotAlreadyVisited(Direction direction) throws InvalidMapException
    {
        Point position = turnTo(direction);
        return canDroneMoveTo(direction) && !alreadyVisited(position);
    }

    private Turn turnBuilder(Direction target, Direction sense) throws InvalidMapException
    {
        return new Turn(new ScanBeach(getMap(), target, sense), getMap(), getHeading(), target);
    }
}
