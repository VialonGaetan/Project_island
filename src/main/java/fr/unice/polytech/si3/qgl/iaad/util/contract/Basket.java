package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexandre Clement
 * @since 21/03/2017.
 */
public class Basket<T extends Enum<T> & Resource>
{
    private final Map<T, Integer> map;

    public Basket(Class<T> enumType)
    {
        this.map = new EnumMap<>(enumType);
    }

    public Basket(Basket<T> basket)
    {
        this.map = new EnumMap<>(basket.map);
    }

    public void add(T resource, int quantity)
    {
        map.put(resource, get(resource) + quantity);
    }

    public void add(Basket<T> basket)
    {
        for (Map.Entry<T, Integer> entry : basket.entrySet())
        {
            add(entry.getKey(), entry.getValue());
        }
    }

    public void remove(T resource, int quantity)
    {
        map.put(resource, Math.max(0, get(resource) - quantity));
    }

    public int get(T resource)
    {
        return map.getOrDefault(resource, 0);
    }

    public Set<T> getKeys()
    {
        return map.keySet();
    }

    public Set<Map.Entry<T, Integer>> entrySet()
    {
        return map.entrySet();
    }

    public boolean isEmpty()
    {
        return entrySet().stream().map(Map.Entry::getValue).allMatch(integer -> integer == 0);
    }

    public void addAll(Basket<T> basket)
    {
        for (Map.Entry<T, Integer> tIntegerEntry : basket.entrySet())
        {
            add(tIntegerEntry.getKey(), tIntegerEntry.getValue());
        }
    }

    public boolean contains(T resource)
    {
        return map.containsKey(resource) && map.get(resource) > 0;
    }

    public boolean contains(Basket<T> basket)
    {
        Basket<T> other = new Basket<>(basket);
        for (Map.Entry<T, Integer> entry : entrySet())
        {
            if (other.get(entry.getKey()) <= entry.getValue())
                other.remove(entry.getKey(), entry.getValue());
            else
                return false;
        }
        return other.isEmpty();
    }
}
