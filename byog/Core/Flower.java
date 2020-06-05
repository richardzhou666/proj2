package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Flower {
    Position p;
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;

    Flower(Random rand, TETile[][] world) {
        int x = RandomUtils.uniform(rand, MapGenerator.floor(world).size());
        this.p = MapGenerator.floor(world).get(x);
    }

    void drawFlower(TETile[][] world) {
        world[p.x][p.y] = Tileset.FLOWER;
    }
}