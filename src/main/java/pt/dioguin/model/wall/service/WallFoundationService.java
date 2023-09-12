package pt.dioguin.model.wall.service;

import pt.dioguin.model.wall.Wall;

import java.util.List;

public interface WallFoundationService {

    List<Wall> getAll();

    Wall get(String identifier);

    void put(Wall wall);

}
