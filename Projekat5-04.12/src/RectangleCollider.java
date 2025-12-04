public class RectangleCollider implements Collidable {
    private int x;
    private int y;
    private int width;
    private int height;

    public RectangleCollider(int x, int y, int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException("Širina pravougaonika mora biti veća od nule");
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Visina pravougaonika mora biti veća od nule");
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Širina pravougaonika mora biti veća od nule");
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Visina pravougaonika mora biti veća od nule");
        }
        this.height = height;
    }

    @Override
    public boolean intersects(Collidable other) {
        if (other == null) {
            return false;
        }

        // Ako je drugi objekat također pravougaonik
        if (other instanceof RectangleCollider) {
            RectangleCollider rect = (RectangleCollider) other;
            return this.x < rect.x + rect.width &&
                   this.x + this.width > rect.x &&
                   this.y < rect.y + rect.height &&
                   this.y + this.height > rect.y;
        }

        // Ako je drugi objekat krug, prebacujemo logiku na krug
        if (other instanceof CircleCollider) {
            return other.intersects(this);
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("Rectangle[x=%d, y=%d, w=%d, h=%d]", x, y, width, height);
    }
}
