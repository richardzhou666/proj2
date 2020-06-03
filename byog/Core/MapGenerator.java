package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.Core.Room;

import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {
    public long seed;
    private static Random rand;
    private final TETile[][] world;
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;
    private final TERenderer ter;

    /** Constructor, generate empty world  */
    public MapGenerator() {
        rand = new Random();
        ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }



    public static void main(String[] args) {
        MapGenerator map = new MapGenerator();
        Position p = new Position(20,30);
        Room r1 = new Room(p, 5, 10);
//        Room r2 = new Room(new Position(45, 30), 3, 6);
//        Room l = new Room(new Position(26,32), 18, 2);
//        Room l2 = new Room(new Position(23, 41), 1, 6);
//        int index = RandomUtils.uniform(rand, l.top().size());
//
//        Room l3 = new Room(new Position(21, 29), 3, 6);
//        Room r3 = new Room(l.top().get(index), 1, 6);
//        Room r4 = new Room(l.top().get(index), 2, 8);
//        r1.drawRoom(map.world);
////        r2.drawRoom(map.world);
//        l.drawRoom(map.world);
//        l2.drawRoom(map.world);
////        r3.drawRoom(map.world);
//        r4.drawRoom(map.world);
        Room.drawRandomRooms(map.world, rand, WIDTH, HEIGHT, 50000);
//        r1.drawTest(map.world);
        map.ter.renderFrame(map.world);
    }
}
