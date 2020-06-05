package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

import static byog.Core.Room.*;

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

    public static ArrayList<Position> floor(TETile[][] world) {
        ArrayList<Position> floor = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j] == Tileset.FLOOR) floor.add(new Position(i, j));
            }
        }
        return floor;
    }

    public void startMap(int count) {
        ArrayList<Room> roomList = randomRooms(rand, count);
        sortRooms(roomList);
        for (Room r:roomList) {
            r.drawRoom(world);
            System.out.println(r.distance);
        }
        for (int i = 0; i < count; i ++) {
            connectHouse(roomList.get(i), roomList.get(i + 1), world);
        }
        Room.removeXWall(world);
        Room.removeYWall(world);
        Room.removeXWall(world);
        Room.removeYWall(world);
    }

    public void getPlayer(TETile[][] world) {
        Player p = new Player(world);
        p.drawPlayer(world);
    }

    public static void main(String[] args) {
        MapGenerator map = new MapGenerator();
        map.startMap(18);
        map.getPlayer(map.world);
        map.ter.renderFrame(map.world);
    }
}
