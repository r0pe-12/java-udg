public abstract class GameObject {
    private double x;
    private double y;
    private ICollidable collider;

    public GameObject(double x, double y, ICollidable collider) {
        if (collider == null) {
            throw new IllegalArgumentException("Collider ne moze biti null");
        }
        this.x = x;
        this.y = y;
        this.collider = collider;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public ICollidable getCollider() {
        return collider;
    }

    public void setCollider(ICollidable collider) {
        if (collider == null) {
            throw new IllegalArgumentException("Collider ne moze biti null");
        }
        this.collider = collider;
    }

    public boolean intersects(GameObject other) {
        if (other == null) {
            return false;
        }
        return this.collider.intersects(other.collider);
    }

    public abstract String getDisplayName();

    @Override
    public String toString() {
        return String.format("GameObject @ (%.1f,%.1f) with %s", x, y, collider);
    }
}
