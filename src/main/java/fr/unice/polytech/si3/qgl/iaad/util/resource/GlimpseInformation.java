package fr.unice.polytech.si3.qgl.iaad.util.resource;

/**
 * @author Gaetan Vialon
 *         Created the 11/03/2017.
 */
public class GlimpseInformation {

    private Biome biome;
    private int range;
    private double percentage;

    public GlimpseInformation(Biome biome, double percentage, int range) {

        this.biome = biome;
        this.range = range;
        this.percentage = percentage;
    }

    public GlimpseInformation(Biome biome,int range){
        this.biome = biome;
        this.range = range;
        this.percentage = -1;
    }

    public Biome getBiome() {
        return biome;
    }

    public int getRange() {
        return range;
    }

    public double getPercentage() {
        return percentage;
    }
}
