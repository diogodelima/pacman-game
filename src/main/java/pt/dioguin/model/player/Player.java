package pt.dioguin.model.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import pt.dioguin.model.direction.Direction;
import pt.dioguin.model.point.Point;

@AllArgsConstructor
@Data
public class Player {

    private Point point;
    private Direction direction;
    private boolean full;

}
