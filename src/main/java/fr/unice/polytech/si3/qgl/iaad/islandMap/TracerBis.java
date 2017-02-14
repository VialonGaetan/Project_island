package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Théo on 30/01/2017.
 */


public class TracerBis{
    private Point point; // point en question
    private List<CommandMap> commandMaps; // liste qui retient les actions

    /*
     Tracer est initialisé avec le point en question
     il initialise la liste de command à new ArrayList (Tracer va être utilisé plusieurs fois)
     */
    public TracerBis(Point point)
    {
        this.point = new Point(point);
        // continue
    }

    public void addInstructionMap(InstructionMap instructionMap, int numberOfPoints)
    {
        // ajoute la commande correspondante dans la liste
    }

    public Point getPointInCurrentMap()
    {
        // en parcourant la liste des commandes enregistrées, calcule les nouvelles coordonnées et renvoie le point
        return point;
    }

    /*
    CommandMap associe à une action le nombre de lignes ou colonnes qui ont été ajoutées
     */
    private class CommandMap
    {
        private InstructionMap instructionMap;
        private int numberOfPoints;

        CommandMap(InstructionMap instructionMap, int numberOfPoints)
        {
            this.instructionMap = instructionMap;
            this.numberOfPoints = numberOfPoints;
        }

        InstructionMap getInstructionMap() { return instructionMap; }

        int getNumberOfPoints() { return numberOfPoints; }
    }

    /*
    une action
     */
    private enum InstructionMap
    {
        OUT_OF_RANGE,
        GROUND
    }
}
