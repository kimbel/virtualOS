package fr.eseo.os;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by axel on 07/12/15.
 */
public class Historic {

    private Map<String, String> commands;
    private User user;

    public Historic(User user) {
        commands = new HashMap<>();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, String> getCommands() {
        return commands;
    }

    public void setCommands(Map<String, String> commands) {
        this.commands = commands;
    }
}
