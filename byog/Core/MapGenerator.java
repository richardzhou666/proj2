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

    /** Constructor, generate empty world */
    public MapGenerator(long seed) {
        this.seed = seed;
        rand = new Random(seed);
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

    public static ArrayList<Position> wall(TETile[][] world) {
        ArrayList<Position> wall = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j] == Tileset.MYWALL) wall.add(new Position(i, j));
            }
        }
        return wall;
    }

    public static ArrayList<Position> perimeter(TETile[][] world) {
        ArrayList<Position> perimeter = new ArrayList<>();
        for (int x = 1; x < WIDTH - 1; x++) {
            for (int y = 1; y < HEIGHT - 1; y++) {
                if ((world[x - 1][y] == Tileset.NOTHING && world[x + 1][y] == Tileset.FLOOR)
                        || (world[x - 1][y] == Tileset.FLOOR && world[x + 1][y] == Tileset.NOTHING)
                        || (world[x][y + 1] == Tileset.FLOOR && world[x][y - 1] == Tileset.NOTHING)
                        || (world[x][y + 1] == Tileset.NOTHING && world[x][y - 1] == Tileset.FLOOR)) {
                    perimeter.add(new Position(x, y));
                }
            }
        }
        return perimeter;
    }

    /** Generate pseudo-random world*/
    public void startMap(int count) {
        ArrayList<Room> roomList = randomRooms(rand, count);
        sortRooms(roomList);
        for (Room r:roomList) {
            r.drawRoom(world);
        }
        for (int i = 0; i < count; i ++) {
            connectHouse(rand, roomList.get(i), roomList.get(i + 1), world);
        }
        Room.removeXWall(world);
        Room.removeYWall(world);
        Room.removeXWall(world);
        Room.removeYWall(world);
        getPlayer();
        getLockedDoor();
        getFlower();
        getEnemy();
    }

    public void getPlayer() {
        Player p = new Player(rand, world);
        p.drawPlayer(world);
    }

    public void getLockedDoor() {
        Door d = new Door(rand, world);
        d.drawLockedDoor(world);
    }

    public void getFlower() {
        for (int i = 0; i < 3; i++) {
            Flower p = new Flower(rand, world);
            p.drawFlower(world);
        }
    }

    public void getEnemy() {
        for (int i = 0; i < 3; i++) {
            Enemy d = new Enemy(rand, world);
            d.drawEnemy(world);
        }
    }

    public static void main(String[] args) {
        MapGenerator map = new MapGenerator(131313);
        map.startMap(30);
        map.ter.renderFrame(map.world);
    }
}
