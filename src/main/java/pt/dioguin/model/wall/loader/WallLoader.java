package pt.dioguin.model.wall.loader;

import lombok.SneakyThrows;
import pt.dioguin.model.wall.Wall;
import pt.dioguin.model.wall.adapter.WallAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WallLoader {

    private final File file;
    private final WallAdapter adapter;

    public WallLoader(File file){
        this.file = file;
        this.adapter = new WallAdapter();
    }

    @SneakyThrows
    public List<Wall> setup(){

        List<Wall> walls = new ArrayList<>();
        Scanner scanner = new Scanner(this.file);

        while (scanner.hasNext()){

            String line = scanner.nextLine();

            if (line.matches("\\d+:"))
                walls.add(this.adapter.adapt(scanner));
        }

        return walls;
    }

}
