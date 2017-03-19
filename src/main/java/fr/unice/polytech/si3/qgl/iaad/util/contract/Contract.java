package fr.unice.polytech.si3.qgl.iaad.util.contract;

import fr.unice.polytech.si3.qgl.iaad.util.resource.Resource;

/**
 * @author Alexandre Clement
 * @since 21/02/2017.
 */
public class Contract extends Basket
{
    public Contract(Basket basket)
    {
        super(basket);
    }

    public boolean allCompleted()
    {
        return keySet().stream().allMatch(this::completed);
    }

    public boolean completed(Resource resource)
    {
        return !containsKey(resource) || get(resource) == 0;
    }

    public void collect(Resource resource, Integer collectedQuantity)
    {
        merge(resource, collectedQuantity, (previous, collected) -> Math.max(0, previous - collected));
    }
}
