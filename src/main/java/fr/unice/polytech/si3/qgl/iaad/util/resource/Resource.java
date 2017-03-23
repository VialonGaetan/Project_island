package fr.unice.polytech.si3.qgl.iaad.util.resource;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alexandre Clement
 * @since 19/03/2017.
 */
@FunctionalInterface
public interface Resource
{
    ResourceCategory getCategory();

    static Resource valueOf(String name)
    {
        Map<String, Resource> map = Stream.of(PrimaryResource.values(), Manufactured.values())
                .flatMap(Stream::of)
                .collect(Collectors.toMap(Object::toString, Function.identity()));
        return map.get(name);
    }
}
