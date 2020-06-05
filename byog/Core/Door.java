package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Door {
    Position p;
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;

    Door(Random rand, TETile[][] world) {
        int x = RandomUtils.uniform(rand, MapGenerator.perimeter(world).size());
        this.p = MapGenerator.perimeter(world).get(x);
    }

    void drawLockedDoor(TETile[][] world) {
        world[p.x][p.y] = Tileset.LOCKED_DOOR;
    }
}
