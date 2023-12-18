package Factory;

public class GridComponentFactory{
    public GridComponent createGridComponent(GridComponentTypes type) {
        if (type == null)
            return null;
        switch (type) {
            case HUNTER:
                return new Hunter();
            case TARGET:
                return new Target();
            default:
                throw new IllegalArgumentException("Unknown component type "+type);
        }
    }
}
