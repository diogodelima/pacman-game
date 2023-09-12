package pt.dioguin.model.wall.adapter;

import lombok.SneakyThrows;
import pt.dioguin.exception.WallConfigurationException;
import pt.dioguin.model.orientation.Orientation;
import pt.dioguin.model.point.Point;
import pt.dioguin.model.wall.Wall;
import pt.dioguin.util.configuration.ConfigurationAdapter;

import java.io.File;
import java.util.Scanner;

public class WallAdapter implements ConfigurationAdapter<Wall> {

    @SneakyThrows
    @Override
    public Wall adapt(Scanner scanner) {

        int x = -1, y = -1, size = -1;
        Orientation orientation = null;
        String identifier = null;

        while (scanner.hasNext()){
            String line = scanner.nextLine();

            if (line.contains("identifier:"))
                identifier = line.split(":")[1].trim();
            else if (line.contains("x:"))
                x = Integer.parseInt(line.split(":")[1].trim());
            else if (line.contains("y:"))
                y = Integer.parseInt(line.split(":")[1].trim());
            else if (line.contains("size:"))
                size = Integer.parseInt(line.split(":")[1].trim());
            else if (line.contains("orientation:"))
                orientation = Orientation.valueOf(line.split(":")[1].trim().toUpperCase());
            else break;
        }

        if (identifier == null || x == -1 || y == -1 || size == -1 || orientation == null)
            throw new WallConfigurationException();

        Point point = new Point(x, y);
        return new Wall(identifier, point, orientation, size);
    }

}
