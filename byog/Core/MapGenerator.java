package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.Core.Room;

import java.util.ArrayList;
import java.util.Random;

import static byog.Core.Room.randomPosition;
import static byog.Core.Room.randomRooms;

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

    public static void connectHouse(Room h1, Room h2, TETile[][] world) {
        h1.connect(h2, world);
    }


    public static void main(String[] args) {
        MapGenerator map = new MapGenerator();
        Position p = new Position(20,30);
        Position p2 = new Position (25,35);
//        Floor.drawFloorX(p, p2, map.world);
//        Floor.drawFloorY(p, p2, map.world);
//        Room r1 = new Room(p, 5, 10);
//        Room r2 = new Room(new Position(45, 30), 3, 6);
//        r1.drawRoom(map.world);
//        r2.drawRoom(map.world);
//        connectHouse(r1, r2, map.world);
//        Position x = randomPosition(r1);
//        Position y = randomPosition(r2);
//        map.world[x.x][x.y] = Tileset.PLAYER;
//        map.world[y.x][y.y] = Tileset.PLAYER;
//        Floor.drawFloorX(x, y, map.world);
//        Floor.drawFloorY(x, y, map.world);
//        r1.connect(r2, map.world);
//        connectHouse(r1, r2, map.world);
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
        ArrayList<Room> roomList = randomRooms(rand, 20);
//        Room.drawRandomRooms(map.world, rand, WIDTH, HEIGHT, 50);
        for (Room r:roomList) {
            r.drawRoom(map.world);
        }
        for (int i = 0; i < 13; i ++) {
            connectHouse(roomList.get(i), roomList.get(i + 1), map.world);
        }
//        for (int i = 0; i < 2; i++) {
//            randomRooms(rand, 10).get(i).drawRoom(map.world);
//        }
//        Hallway h = new Hallway(new Position(22, 21), 2, 10);
//        Hallway h2 = new Hallway(new Position(26, 35), 10, 2);
//        h2.drawHorizontal(map.world);
//        h.drawVertical(map.world);
        map.ter.renderFrame(map.world);
    }
}
