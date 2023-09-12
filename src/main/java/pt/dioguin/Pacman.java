package pt.dioguin;

import lombok.Getter;
import lombok.SneakyThrows;
import pt.dioguin.model.gameboard.GameBoard;
import pt.dioguin.model.wall.loader.WallLoader;
import pt.dioguin.model.wall.service.WallFoundationService;
import pt.dioguin.model.wall.service.WallService;

import java.io.File;
import java.util.Objects;

public class Pacman {

    @Getter
    private static WallFoundationService wallService;

    @SneakyThrows
    public static void main(String[] args) {

        File wallConfig = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("wall.txt")).getFile());
        wallService = new WallService();
        new WallLoader(wallConfig).setup().forEach(wallService::put);

        new GameBoard();
    }

}