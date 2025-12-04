public interface Collidable {
    /**
     * Provjerava da li se ovaj objekat sudara sa drugim objektom
     * @param other drugi objekat sa kojim provjeravamo koliziju
     * @return true ako postoji kolizija, false ako ne postoji
     */
    boolean intersects(Collidable other);
}
