package pt.dioguin.gameboard;

import pt.dioguin.Pacman;
import pt.dioguin.PacmanConstants;
import pt.dioguin.model.direction.Direction;
import pt.dioguin.model.orientation.Orientation;
import pt.dioguin.model.player.Player;
import pt.dioguin.model.point.Point;
import pt.dioguin.model.wall.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Arc2D;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;

public class GameBoard {

    private final JFrame frame;

    public GameBoard(){

        frame = new JFrame("Pacman");

        GenerateGamePanel gamePanel = new GenerateGamePanel();
        frame.setSize(PacmanConstants.WINDOW_WIDTH + PacmanConstants.CELL_SIZE, PacmanConstants.WINDOW_HEIGHT + PacmanConstants.CELL_SIZE * 2);
        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Player player = Pacman.getPlayer();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                player.setFull(!player.isFull());
                changePlayerPoint(player);
                gamePanel.repaint();

                if (checkCollision(player)){
                    this.cancel();
                    endGame();
                }
            }
        }, 0, 250L);


    }

    private void endGame(){



    }

    private void changePlayerPoint(Player player){

        switch (player.getDirection()){
            case DOWN -> player.setPoint(new Point(player.getPoint().x(), player.getPoint().y() + 1));
            case UP -> player.setPoint(new Point(player.getPoint().x(), player.getPoint().y() - 1));
            case LEFT -> player.setPoint(new Point(player.getPoint().x() - 1, player.getPoint().y()));
            case RIGHT -> player.setPoint(new Point(player.getPoint().x() + 1, player.getPoint().y()));
        }

    }

    private boolean checkCollision(Player player){
        Point point = player.getPoint();

        return Pacman.getWallService().getAll().stream()
                .anyMatch(wall -> {
                    if (wall.orientation() == Orientation.HORIZONTAL) {
                        return wall.point().y() == point.y() &&
                                IntStream.range(wall.point().x(), wall.point().x() + wall.size())
                                        .anyMatch(x -> x == point.x());
                    } else if (wall.orientation() == Orientation.VERTICAL) {
                        return wall.point().x() == point.x() &&
                                IntStream.range(wall.point().y(), wall.point().y() + wall.size())
                                        .anyMatch(y -> y == point.y());
                    }
                    return false;
                });
    }

    public static class GenerateGamePanel extends JPanel implements KeyListener {

        public GenerateGamePanel(){
            setBackground(PacmanConstants.BACKGROUND_COLOR);
            setFocusable(true);
            addKeyListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(PacmanConstants.BORDER_COLOR);
            for (int x = 0; x < PacmanConstants.NUM_CELLS_X; x++) {
                for (int y = 0; y < PacmanConstants.NUM_CELLS_Y; y++) {
                    int cellX = x * PacmanConstants.CELL_SIZE;
                    int cellY = y * PacmanConstants.CELL_SIZE;
                    g.drawRect(cellX, cellY, PacmanConstants.CELL_SIZE, PacmanConstants.CELL_SIZE);
                }
            }

            setupWalls(g);
            drawPlayer((Graphics2D) g, Pacman.getPlayer());
        }

        private void setupWalls(Graphics g){

            List<Wall> walls = Pacman.getWallService().getAll();

            walls.forEach(wall -> {

                int x = wall.point().x() * PacmanConstants.CELL_SIZE + 1;
                int y = wall.point().y() * PacmanConstants.CELL_SIZE + 1;
                int size = wall.size();

                g.setColor(PacmanConstants.WALL_COLOR);

                switch (wall.orientation()){
                    case VERTICAL -> g.fillRect(x, y, PacmanConstants.CELL_SIZE - 1, size * PacmanConstants.CELL_SIZE - 1);
                    case HORIZONTAL -> g.fillRect(x, y, size * PacmanConstants.CELL_SIZE - 1, PacmanConstants.CELL_SIZE - 1);
                }

            });

        }

        private void drawPlayer(Graphics2D g, Player player){

            int x = player.getPoint().x() * PacmanConstants.CELL_SIZE;
            int y = player.getPoint().y() * PacmanConstants.CELL_SIZE;

            switch (player.getDirection()) {
                case RIGHT -> {
                    if (player.isFull()) {
                        g.setColor(PacmanConstants.PLAYER_COLOR);
                        g.fillOval(x, y, PacmanConstants.CELL_SIZE, PacmanConstants.CELL_SIZE);
                    } else {
                        int startAngle = 45;
                        int arcAngle = 270;
                        drawPlayerAngle(g, player, startAngle, arcAngle);
                    }
                }
                case UP -> {
                    if (player.isFull()) {
                        g.setColor(PacmanConstants.PLAYER_COLOR);
                        g.fillOval(x, y, PacmanConstants.CELL_SIZE, PacmanConstants.CELL_SIZE);
                    } else {
                        int startAngle = 135;
                        int arcAngle = 270;
                        drawPlayerAngle(g, player, startAngle, arcAngle);
                    }
                }
                case DOWN -> {
                    if (player.isFull()) {
                        g.setColor(PacmanConstants.PLAYER_COLOR);
                        g.fillOval(x, y, PacmanConstants.CELL_SIZE, PacmanConstants.CELL_SIZE);
                    } else {
                        int startAngle = 315;
                        int arcAngle = 270;
                        drawPlayerAngle(g, player, startAngle, arcAngle);
                    }
                }
                case LEFT -> {
                    if (player.isFull()) {
                        g.setColor(PacmanConstants.PLAYER_COLOR);
                        g.fillOval(x, y, PacmanConstants.CELL_SIZE, PacmanConstants.CELL_SIZE);
                    } else {
                        int startAngle = 225;
                        int arcAngle = 270;
                        drawPlayerAngle(g, player, startAngle, arcAngle);
                    }
                }
            }
        }

        private void drawPlayerAngle(Graphics2D g, Player player, int startAngle, int arcAngle){

            int x = player.getPoint().x() * PacmanConstants.CELL_SIZE;
            int y = player.getPoint().y() * PacmanConstants.CELL_SIZE;
            int radius = PacmanConstants.CELL_SIZE / 2;

            Arc2D arc = new Arc2D.Double(x, y, 2 * radius, 2 * radius, startAngle, arcAngle, Arc2D.PIE);

            g.setColor(PacmanConstants.PLAYER_COLOR);
            g.fill(arc);

            g.setColor(PacmanConstants.BACKGROUND_COLOR);
            g.draw(arc);

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

            Player player = Pacman.getPlayer();

            switch (e.getKeyCode()) {
                case 39 -> player.setDirection(Direction.RIGHT);
                case 38 -> player.setDirection(Direction.UP);
                case 37 -> player.setDirection(Direction.LEFT);
                case 40 -> player.setDirection(Direction.DOWN);
            }

        }
    }

}
