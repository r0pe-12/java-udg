public class User {
    private int id;
    private String ime;
    private String prezime;
    private float satnica;
    private Role role;

    public enum Role {KLIJENT, RADNIK}

    public User() {
    }

    public User(int id, String ime, String prezime, float satnica, Role role) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.role = role;
        this.satnica = satnica;
        UserStore.add(this);
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public float getSatnica() {
        return satnica;
    }

    public Role getRole() {
        return role;
    }

    public void setIme(String ime) {
        this.ime = ime;
        UserStore.update(this);
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
        UserStore.update(this);
    }

    public void setSatnica(float satnica) {
        this.satnica = satnica;
        UserStore.update(this);
    }

    public void setRole(Role role) {
        this.role = role;
        UserStore.update(this);
    }

    @Override
    public String toString() {
        return id + " " + ime + " " + prezime + " " + role;
    }
}
