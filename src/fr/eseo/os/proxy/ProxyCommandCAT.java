package fr.eseo.os.proxy;

import fr.eseo.os.User;
import fr.eseo.os.command.Command;
import fr.eseo.os.command.CommandCAT;

/**
 * Created by axel on 13/12/15.
 */
public class ProxyCommandCAT implements Command {

    private User user;

    public ProxyCommandCAT(User user) {
        this.user = user;
    }

    @Override
    public String execute(String... args) {
        Command commandCAT = new CommandCAT(this.user);
        return commandCAT.execute(args);
    }
}
