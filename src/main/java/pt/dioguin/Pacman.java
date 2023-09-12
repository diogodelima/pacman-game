package pt.dioguin;

import lombok.Getter;
import lombok.SneakyThrows;
import pt.dioguin.gameboard.GameBoard;
import pt.dioguin.model.player.Player;
import pt.dioguin.model.wall.loader.WallLoader;
import pt.dioguin.model.wall.service.WallFoundationService;
import pt.dioguin.model.wall.service.WallService;

import java.io.File;
import java.util.Objects;

public class Pacman {

    @Getter
    private static WallFoundationService wallService;
    @Getter
    private static Player player;

    @SneakyThrows
    public static void main(String[] args) {

        File wallConfig = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("wall.txt")).getFile());
        wallService = new WallService();
        new WallLoader(wallConfig).setup().forEach(wallService::put);

        player = new Player(PacmanConstants.INITIAL_POINT, PacmanConstants.INITIAL_DIRECTION, true);

        new GameBoard();
    }

}