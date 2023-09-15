package pt.dioguin;

import pt.dioguin.model.direction.Direction;
import pt.dioguin.model.point.Point;

import java.awt.*;

public class PacmanConstants {

    public static final Point INITIAL_POINT = new Point(39, 22);
    public static final Direction INITIAL_DIRECTION = Direction.RIGHT;
    public static final int WINDOW_WIDTH = 1600;
    public static final int WINDOW_HEIGHT = 900;
    public static final int CELL_SIZE = 20;
    public static final int NUM_CELLS_X = WINDOW_WIDTH / CELL_SIZE;
    public static final int NUM_CELLS_Y = WINDOW_HEIGHT / CELL_SIZE;
    public static final Color BORDER_COLOR = new Color(40, 40, 40);
    public static final Color WALL_COLOR = new Color(3, 14, 61);
    public static final Color BACKGROUND_COLOR = new Color(0, 0, 0);
    public static final Color PLAYER_COLOR = Color.YELLOW;
    public static final Color FOOD_COLOR = Color.YELLOW;
    public static final Color END_GAME = Color.RED;

}
