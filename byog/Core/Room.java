package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Room {
    Position p;
    int n; // Width
    int m; // Height

    Room(Position p, int n, int m) {
        this.p = p;
        this.n = n;
        this.m = m;

    }

    public void drawRoom(TETile[][] world) {
        // Draw room interior
        for (Position interior: interior()) {
            world[interior.x][interior.y] = Tileset.FLOOR;
        }
        // Draw Wall
        for (Position out:wall()) {
            world[out.x][out.y] = Tileset.WALL;
        }
    }

    public void drawTest(TETile[][] world) {
        for (Position p:everything()) {
            world[p.x][p.y] = Tileset.LOCKED_DOOR;
        }
    }

    private ArrayList<Position> everything() {
        ArrayList<Position> everything = new ArrayList<>();
        everything.addAll(interior());
        everything.addAll(wall());
        return everything;
    }

    private ArrayList<Position> interior() {
        ArrayList<Position> interiors = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                interiors.add(new Position(p.x + i, p.y + j));
            }
        }
        return interiors;
    }

    public ArrayList<Position> top() {
        ArrayList<Position> top = new ArrayList<> ();
        for (int i = 1; i <= n; i++) {
            top.add(new Position(p.x + i, p.y + m + 1));
        }
        return top;
    }

    public ArrayList<Position> bottom() {
        ArrayList<Position> bottom = new ArrayList<> ();
        for (int i = 1; i <= n; i++) {
            bottom.add(new Position(p.x + i, p.y));
        }
        return bottom;
    }

    public ArrayList<Position> left() {
        ArrayList<Position> left = new ArrayList<> ();
        for (int i = 1; i <= m; i++) {
            left.add(new Position(p.x, p.y + i));
        }
        return left;
    }

    public ArrayList<Position> right() {
        ArrayList<Position> right = new ArrayList<> ();
        for (int i = 1; i <= m; i++) {
            right.add(new Position(p.x + n + 1, p.y + i));
        }
        return right;
    }

    private ArrayList<Position> corner() {
        ArrayList<Position> corner = new ArrayList<>();
        corner.add(new Position(p.x, p.y));
        corner.add(new Position(p.x + n + 1, p.y));
        corner.add(new Position(p.x, p.y + m + 1));
        corner.add(new Position(p.x + n + 1, p.y + m + 1));
        return corner;
    }

    public ArrayList<Position> availablePath() {
        ArrayList<Position> path = new ArrayList<>();
        path.addAll(top());
        path.addAll(bottom());
        path.addAll(left());
        path.addAll(right());
        return path;
    }

    private  ArrayList<Position> wall() {
        ArrayList<Position> wall = new ArrayList<>();
        wall.addAll(availablePath());
        wall.addAll(corner());
        return wall;
    }

    /** Generate list of random rooms */
    private static ArrayList<Room> randomRooms(Random rand, int WIDTH, int HEIGHT, int count) {
        ArrayList<Room> roomList = new ArrayList<>();
        ArrayList<Position> allRooms = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int x = RandomUtils.uniform(rand,0,  WIDTH-10);
            int y = RandomUtils.uniform(rand, 0,  HEIGHT-10);
            Position p = new Position(x,y);
            int n = RandomUtils.uniform(rand,5, 10);
            int m = RandomUtils.uniform(rand, 3,10);
            Room r = new Room(p, n, m);
            if (i == 0) {
                roomList.add(r);
                allRooms.addAll(r.everything());
            } else if (!overlap(allRooms, r)) {
                roomList.add(r);
                allRooms.addAll(r.everything());
            }
        }
        return roomList;
    }

    /** Checks overlap between new room with existing rooms
     * O(n * m) looking to be optimized */
    private static boolean overlap(ArrayList<Position> allRooms, Room a) {
        ArrayList<Position> ai = a.everything();
        for (Position p:allRooms) {
            for (Position ap:ai) {
                if (p.x == ap.x && p.y == ap.y) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void drawRandomRooms(TETile[][] world, Random rand, int WIDTH, int HEIGHT, int count) {
        ArrayList<Room> roomList = randomRooms(rand, WIDTH, HEIGHT, count);
        for (Room r:roomList) {
            r.drawRoom(world);
        }
    }
}
