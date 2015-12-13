package fr.eseo.os.command;

import fr.eseo.os.User;

/**
 * Created by axel on 12/12/15.
 */
public class CommandLS implements Command {

    private User user;

    public CommandLS(User user) {
        this.user = user;
    }

    @Override
    public String execute(String... args) {
        return user.executeCommandLS(args);
    }
}
