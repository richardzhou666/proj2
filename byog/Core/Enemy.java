package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Enemy {
    Position p;
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;

    Enemy(Random rand, TETile[][] world) {
        int x = RandomUtils.uniform(rand, MapGenerator.floor(world).size());
        this.p = MapGenerator.floor(world).get(x);
    }

    void drawEnemy(TETile[][] world) {
        world[p.x][p.y] = Tileset.ENEMY;
    }
}
