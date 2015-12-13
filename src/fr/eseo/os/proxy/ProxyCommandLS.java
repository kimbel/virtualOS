package fr.eseo.os.proxy;

import fr.eseo.os.User;
import fr.eseo.os.command.Command;
import fr.eseo.os.command.CommandLS;

/**
 * Created by axel on 13/12/15.
 */
public class ProxyCommandLS implements Command {

    private User user;

    public ProxyCommandLS(User user) {
        this.user = user;
    }

    @Override
    public String execute(String... args) {
        Command commandLS = new CommandLS(this.user);
        return commandLS.execute(args);
    }
}
