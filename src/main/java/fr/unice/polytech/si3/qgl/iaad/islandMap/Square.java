package fr.unice.polytech.si3.qgl.iaad.islandMap;

import fr.unice.polytech.si3.qgl.iaad.Biome;

import java.util.ArrayList;
import java.util.List;

/**
 * @author romain
 * Created on 02/02/17.
 */
class Square
{
    private List<Element> elements;
    private List<Biome> biomes;
    private List<String> creekIds;
    private boolean isAnOldGroundLocation;
    private boolean isAnOldAerialLocation;

    Square()
    {
        elements = new ArrayList<>();
        biomes = new ArrayList<>();
        creekIds = new ArrayList<>();
    }

    void copy(Square square)
    {
        elements = new ArrayList<>(square.elements);
        biomes = new ArrayList<>(square.biomes);
        creekIds = new ArrayList<>(square.creekIds);
        isAnOldGroundLocation = square.isAnOldGroundLocation;
        isAnOldAerialLocation = square.isAnOldAerialLocation;
    }

    void setAsOldAerialLocation() { isAnOldAerialLocation = true; }

    void setAsOldGroundLocation() { isAnOldGroundLocation = true; }

    boolean isAnOldAerialLocation() { return isAnOldAerialLocation; }

    boolean isAnOldGroundLocation() { return isAnOldGroundLocation; }

    void addElement(Element element)
    {
        for(Element current : elements)
        {
            if(current == element)
            {
                return;
            }
        }

        elements.add(element);
    }

    void addElements(Element... elements)
    {
        for(Element element : elements)
        {
            addElement(element);
        }
    }

    Element[] getElements()
    {
        Element[] elements = new Element[this.elements.size()];

        for(int i=0; i<elements.length; i++)
        {
            elements[i] = this.elements.get(i);
        }

        return elements;
    }

    private void addBiome(Biome biome)
    {
        for(Biome current : biomes)
        {
            if(current == biome)
            {
                return;
            }
        }

        biomes.add(biome);
    }

    void addBiomes(Biome... biomes)
    {
        for(Biome biome : biomes)
        {
            addBiome(biome);
        }
    }

    Biome[] getBiomes()
    {
        Biome[] biomes = new Biome[this.biomes.size()];

        for(int i=0; i<biomes.length; i++)
        {
            biomes[i] = this.biomes.get(i);
        }

        return biomes;
    }

    private void addCreek(String id)
    {
        for(String current : creekIds)
        {
            if(current.equals(id))
            {
                return;
            }
        }

        addElement(Element.CREEK);
        creekIds.add(id);
    }

    void addCreeks(String... ids)
    {
        for(String id : ids)
        {
            addCreek(id);
        }
    }

    String[] getCreekIds()
    {
        String[] creekIds = new String[this.creekIds.size()];

        for(int i=0; i<creekIds.length; i++)
        {
            creekIds[i] = this.creekIds.get(i);
        }

        return creekIds;
    }

    void deleteBiomes(Biome... biomes)
    {
        for(Biome current : biomes)
        {
            if(this.biomes.size() == 0)
            {
                break;
            }

            else
            {
                this.biomes.remove(current);
            }
        }
    }
}
