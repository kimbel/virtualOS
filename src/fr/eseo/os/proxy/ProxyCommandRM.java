package fr.eseo.os.proxy;

import fr.eseo.os.User;
import fr.eseo.os.command.Command;
import fr.eseo.os.command.CommandRM;

/**
 * Created by axel on 13/12/15.
 */
public class ProxyCommandRM implements Command {

    private User user;

    public ProxyCommandRM(User user) {
        this.user = user;
    }

    @Override
    public String execute(String... args) {
        if (this.user.hasReadWriteAccess()) {
            Command commandRM = new CommandRM(this.user);
            return commandRM.execute(args);
        } else {
            return AccessEnum.FORBIDDEN.getAccess();
        }
    }
}
