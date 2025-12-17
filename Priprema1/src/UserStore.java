import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UserStore {

    private static final Path FILE = Paths.get("users.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type LIST_TYPE = new TypeToken<List<User>>() {}.getType();

    public static List<User> all() {
        try {
            if (Files.notExists(FILE)) return new ArrayList<>();
            String json = Files.readString(FILE);
            if (json.isBlank()) return new ArrayList<>();
            return GSON.fromJson(json, LIST_TYPE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void add(User u) {
        List<User> list = all();
        list.add(u);
        save(list);
    }

    public static void update(User u) {
        List<User> list = all();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == u.getId()) {
                list.set(i, u);
                save(list);
                return;
            }
        }
    }

    private static void save(List<User> list) {
        try {
            Files.writeString(
                    FILE,
                    GSON.toJson(list, LIST_TYPE),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
