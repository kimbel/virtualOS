package fr.eseo.os.command;

import fr.eseo.os.User;

/**
 * Created by axel on 12/12/15.
 */
public class CommandMKDIR implements Command {

    private User user;

    public CommandMKDIR(User user) {
        this.user = user;
    }

    @Override
    public String execute(String... args) {
        return user.executeCommandMKDIR(args);
    }
}
