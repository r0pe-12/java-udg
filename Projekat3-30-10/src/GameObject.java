class GameObject {
    private int x;
    private int y;
    private int w;
    private int h;
    private int hp;

    public GameObject(int x, int y, int w, int h, int hp) {
        this.x = x;
        this.y = y;
        setW(w);
        setH(h);
        setHp(hp);
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

    public int getW() {
        return w;
    }

    public void setW(int w) {
        if (w < 1) {
            System.out.println("Width cannot be less than 0");
        }
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        if (h < 1) {
            System.out.println("Height cannot be less than 0");
        }
        this.h = h;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;
        } else this.hp = Math.min(hp, 100);
    }

    public boolean intersects(GameObject e) {
        return this.getX() < e.getX() + e.getW() &&
                this.getX() + this.getW() > e.getX() &&
                this.getY() < e.getY() + e.getH() &&
                this.getY() + this.getH() > e.getY();
    }

    @Override
    public String toString() {
        return "GameObject @ (" + x + ", " + y + ") " + w + "x" + h;
    }
}