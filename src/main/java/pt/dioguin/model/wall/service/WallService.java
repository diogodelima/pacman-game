package pt.dioguin.model.wall.service;

import pt.dioguin.model.wall.Wall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WallService implements WallFoundationService {

    private final Map<String, Wall> cache;

    public WallService(){
        this.cache = new HashMap<>();
    }

    @Override
    public List<Wall> getAll() {
        return this.cache
                .keySet()
                .stream()
                .map(this::get)
                .collect(Collectors.toList());
    }

    @Override
    public Wall get(String identifier) {
        return this.cache.get(identifier);
    }

    @Override
    public void put(Wall wall) {
        this.cache.put(wall.identifier(), wall);
    }
}
