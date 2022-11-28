package Entities;

public class CornerTiles extends Cell{
    private String type;

    /**
     * The constructor for CornerTiles.
     * @param type The type of the Player/
     */
    public CornerTiles(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return "Corner Tile";
    }

    @Override
    public String getType() {
        return null;
    }
}
