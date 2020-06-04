package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Hallway {
    Position p; // Bottom left corner
    int n; // Width
    int m; // Height

    public Hallway(Position p, int n, int m) {
        this.p = p;
        this.n = n;
        this.m = m;
    }

    public void drawVertical(TETile[][] world) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                world[p.x][p.y + j] = Tileset.WALL;
                world[p.x + n + 1][p.y + j] = Tileset.WALL;
                world[p.x + i][p.y + j] = Tileset.FLOOR;
            }
        }
    }

    public void drawHorizontal(TETile[][] world) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                world[p.x + i][p.y] = Tileset.WALL;
                world[p.x + i][p.y + j + 1] = Tileset.WALL;
                world[p.x + i][p.y + j] = Tileset.FLOOR;
            }
        }
    }

//    public void drawL(TETile[][] world)

}
