public class CircleCollider implements Collidable {
    private int centerX;
    private int centerY;
    private int radius;

    public CircleCollider(int centerX, int centerY, int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Poluprečnik kruga mora biti strogo pozitivan");
        }
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Poluprečnik kruga mora biti strogo pozitivan");
        }
        this.radius = radius;
    }

    /**
     * Pomoćna metoda za ograničavanje vrijednosti u određeni opseg
     */
    private int clamp(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    @Override
    public boolean intersects(Collidable other) {
        if (other == null) {
            return false;
        }

        // Ako je drugi objekat također krug
        if (other instanceof CircleCollider) {
            CircleCollider circle = (CircleCollider) other;
            // Računamo udaljenost između centara
            int dx = this.centerX - circle.centerX;
            int dy = this.centerY - circle.centerY;
            int distanceSquared = dx * dx + dy * dy;
            int radiusSum = this.radius + circle.radius;
            return distanceSquared <= radiusSum * radiusSum;
        }

        // Ako je drugi objekat pravougaonik
        if (other instanceof RectangleCollider) {
            RectangleCollider rect = (RectangleCollider) other;

            // Pronalazimo najbližu tačku pravougaonika centru kruga
            int closestX = clamp(this.centerX, rect.getX(), rect.getX() + rect.getWidth());
            int closestY = clamp(this.centerY, rect.getY(), rect.getY() + rect.getHeight());

            // Računamo udaljenost između centra kruga i najbliže tačke
            int dx = this.centerX - closestX;
            int dy = this.centerY - closestY;
            int distanceSquared = dx * dx + dy * dy;

            // Postoji kolizija ako je udaljenost manja ili jednaka poluprečniku
            return distanceSquared <= this.radius * this.radius;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("Circle[center=(%d, %d), r=%d]", centerX, centerY, radius);
    }
}
