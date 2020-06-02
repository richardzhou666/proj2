package byog.Core;

import java.util.HashMap;

public class Position {
    int x;
    int y;
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public HashMap<Integer, Integer> getVal(Position p) {
        return new HashMap<>(p.x, p.y);
    }
}