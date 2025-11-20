public class CircleCollider implements ICollidable {
    private double centerX;
    private double centerY;
    private double radius;

    public CircleCollider(double centerX, double centerY, double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Poluprečnik mora biti veći od nule");
        }
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Poluprecnik mora biti veći od nule");
        }
        this.radius = radius;
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    @Override
    public boolean intersects(ICollidable other) {
        if (other instanceof CircleCollider circle) {
            double dx = this.centerX - circle.centerX;
            double dy = this.centerY - circle.centerY;
            double distance = Math.sqrt(dx * dx + dy * dy);
            return distance <= this.radius + circle.radius;
        } else if (other instanceof RectangleCollider rect) {
            double closestX = clamp(this.centerX, rect.getX(), rect.getX() + rect.getWidth());
            double closestY = clamp(this.centerY, rect.getY(), rect.getY() + rect.getHeight());
            double dx = this.centerX - closestX;
            double dy = this.centerY - closestY;
            double distanceSquared = dx * dx + dy * dy;
            return distanceSquared <= this.radius * this.radius;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Circle[%.1f,%.1f R=%.1f]", centerX, centerY, radius);
    }
}
