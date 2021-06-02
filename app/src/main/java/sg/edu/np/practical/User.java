package sg.edu.np.practical;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String description;
    private int id;
    private boolean followed;
    private static int unique_id = 0;

    public User(String name, String description, boolean followed) {
        this.name = name;
        this.description = description;
        this.followed = followed;
        this.id = unique_id++;
    }

    // For initializing from Users database
    public User(String name, String description, int id, int followed) {
        this.name = name;
        this.description = description;
        // SQLite stores boolean values as an integer, thus this hack
        this.followed = followed == 1 ? true : false;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

}
