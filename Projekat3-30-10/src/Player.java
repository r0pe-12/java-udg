public class Player extends GameObject {
    String name;

    public Player(int x, int y, int w, int h, String name, int hp) {
        super(x, y, w, h, hp);
        setName(name);
        setHp(hp);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ime igraca ne smije biti prazno");
        }

        String[] words = name.trim().split("\\s+");
        StringBuilder formattedName = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.isEmpty()) {
                formattedName.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    formattedName.append(word.substring(1).toLowerCase());
                }
                if (i < words.length - 1) {
                    formattedName.append(" ");
                }
            }
        }

        this.name = formattedName.toString();
    }

    @Override
    public String toString() {
        return "Player[" + name + "] @ (" + getX() + ", " + getY() + ") " + getW() + "x" + getH() + " HP=" + getHp();
    }
}