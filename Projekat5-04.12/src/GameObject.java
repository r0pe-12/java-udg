public abstract class GameObject {
    private int x;
    private int y;
    private Collidable collider;

    public GameObject(int x, int y, Collidable collider) {
        if (collider == null) {
            throw new IllegalArgumentException("Collider ne smije biti null");
        }
        this.x = x;
        this.y = y;
        this.collider = collider;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Collidable getCollider() {
        return collider;
    }

    public void setCollider(Collidable collider) {
        if (collider == null) {
            throw new IllegalArgumentException("Collider ne smije biti null");
        }
        this.collider = collider;
    }

    // Apstraktna metoda koju svaka podklasa mora implementirati
    public abstract String getDisplayName();

    // Metoda za provjeru kolizije izmedju dva GameObject-a
    public boolean intersects(GameObject other) {
        if (other == null) {
            return false;
        }
        return this.collider.intersects(other.collider);
    }

    @Override
    public String toString() {
        return String.format("GameObject '%s' at position (%d, %d)",
                           getDisplayName(), x, y);
    }
}
