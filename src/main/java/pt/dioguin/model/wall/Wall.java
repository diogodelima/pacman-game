package pt.dioguin.model.wall;

import pt.dioguin.model.orientation.Orientation;
import pt.dioguin.model.point.Point;

public record Wall(String identifier, Point point, Orientation orientation, int size) {

}
