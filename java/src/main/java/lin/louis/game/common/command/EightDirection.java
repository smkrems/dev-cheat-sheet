package lin.louis.game.common.command;

public enum EightDirection {
    N(0, -1),
    NE(1, -1),
    E(1, 0),
    SE(1, 1),
    S(0, 1),
    SW(-1, 1),
    W(-1, 0),
    NW(-1, -1);

    public int x;
    public int y;

    private EightDirection(int x, int y) {
        this.x = x;
        this.y = y;
    }
}