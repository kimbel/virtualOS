package fr.eseo.os.proxy;

import fr.eseo.os.User;
import fr.eseo.os.command.Command;
import fr.eseo.os.command.CommandMKDIR;

/**
 * Created by axel on 13/12/15.
 */
public class ProxyCommandMKDIR implements Command {

    private User user;

    public ProxyCommandMKDIR(User user) {
        this.user = user;
    }

    @Override
    public String execute(String... args) {
        if (this.user.hasReadWriteAccess()) {
            Command commandMKDIR = new CommandMKDIR(this.user);
            return commandMKDIR.execute(args);
        } else {
            return AccessEnum.FORBIDDEN.getAccess();
        }
    }
}
