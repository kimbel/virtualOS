package fr.eseo.os.proxy;

import fr.eseo.os.User;
import fr.eseo.os.command.Command;
import fr.eseo.os.command.CommandLN;

/**
 * Created by axel on 13/12/15.
 */
public class ProxyCommandLN implements Command {

    private User user;

    public ProxyCommandLN(User user) {
        this.user = user;
    }

    @Override
    public String execute(String... args) {
        if (this.user.hasReadWriteAccess()) {
            Command commandLN = new CommandLN(this.user);
            return commandLN.execute(args);
        } else {
            return AccessEnum.FORBIDDEN.getAccess();
        }
    }
}
