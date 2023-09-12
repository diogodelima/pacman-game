package pt.dioguin.model.gameboard;

import pt.dioguin.Pacman;
import pt.dioguin.model.wall.Wall;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameBoard {

    public static final int WINDOW_WIDTH = 1600;
    public static final int WINDOW_HEIGHT = 900;
    public final static int CELL_SIZE = 20;
    public static final int NUM_CELLS_X = WINDOW_WIDTH / CELL_SIZE;
    public static final int NUM_CELLS_Y = WINDOW_HEIGHT / CELL_SIZE;
    public static final Color BORDER_COLOR = new Color(255, 255, 255);
    public final static Color WALL_COLOR = new Color(3, 14, 61);
    public final static Color BACKGROUND_COLOR = new Color(0, 0, 0);

    private final JFrame frame;

    public GameBoard(){

        frame = new JFrame("Pacman");

        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.add(new GenerateGamePanel(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public class GenerateGamePanel extends JPanel{

        public GenerateGamePanel(){

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(BACKGROUND_COLOR);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(BORDER_COLOR);
            for (int x = 0; x < NUM_CELLS_X; x++) {
                for (int y = 0; y < NUM_CELLS_Y; y++) {
                    int cellX = x * CELL_SIZE;
                    int cellY = y * CELL_SIZE;
                    g.drawRect(cellX, cellY, CELL_SIZE, CELL_SIZE);
                }
            }

            setupWalls(g);
        }

        private void setupWalls(Graphics g){

            List<Wall> walls = Pacman.getWallService().getAll();

            walls.forEach(wall -> {

                int x = wall.point().x() * CELL_SIZE + 1;
                int y = wall.point().y() * CELL_SIZE + 1;
                int size = wall.size();

                g.setColor(WALL_COLOR);

                switch (wall.orientation()){
                    case VERTICAL -> g.fillRect(x, y, CELL_SIZE - 1, size * CELL_SIZE - 1);
                    case HORIZONTAL -> g.fillRect(x, y, size * CELL_SIZE - 1, CELL_SIZE - 1);
                }

            });

        }
    }

}
