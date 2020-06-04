package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class Room {
    Position p;
    int n; // Width
    int m; // Height
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;

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
    public static ArrayList<Room> randomRooms(Random rand, int count) {
        ArrayList<Room> roomList = new ArrayList<>();
        ArrayList<Position> allRooms = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int x = RandomUtils.uniform(rand,0,  WIDTH - 8);
            int y = RandomUtils.uniform(rand, 0,  HEIGHT - 8);
            Position p = new Position(x,y);
            int n = RandomUtils.uniform(rand,3, 9);
            int m = RandomUtils.uniform(rand, 3,9);
            if (x + n >= WIDTH - 2) n = WIDTH - x - 2;
            if (y + m >= HEIGHT - 2) m = HEIGHT - y - 2;
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
        ArrayList<Room> roomList = randomRooms(rand, count);
        for (Room r:roomList) {
            r.drawRoom(world);
        }
    }

    public static Position randomPosition(Room r) {
        int x = RandomUtils.uniform(new Random(), r.availablePath().size());
        return r.availablePath().get(x);
    }

    public void connect(Room r, TETile[][] world) {
         Position p1 = randomPosition(this);
         Position p2 = randomPosition(r);
        int choice = new Random().nextInt(2);
        if (choice == 0) {
            Floor.drawFloorX(p1, p2, world);
            Floor.drawFloorY(p1, p2, world);
        } else {
            Floor.drawFloorY(p1, p2, world);
            Floor.drawFloorX(p1, p2, world);
        }
//            Floor.drawFloorX(p1, p2, world);
//            Floor.drawFloorY(p1, p2, world);
    }

    /** Debug */
    public static void main(String[] args) {
        ArrayList<Room> roomList = randomRooms(new Random(), 25);
        for (Room r:roomList) {
            if (r.p.x >= 70 && r.p.y >=70 ) System.out.println(true);
        }  System.out.println(false);
    }
}
