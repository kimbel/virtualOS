package fr.eseo.os;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by axel on 07/12/15.
 */
public class Historic {

    private List<String> commands;
    private User user;

    public Historic(User user) {
        this.commands = new ArrayList<>();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

}
