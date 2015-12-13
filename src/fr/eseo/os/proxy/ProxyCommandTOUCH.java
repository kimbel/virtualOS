package fr.eseo.os.proxy;

import fr.eseo.os.User;
import fr.eseo.os.command.Command;
import fr.eseo.os.command.CommandTOUCH;

/**
 * Created by axel on 13/12/15.
 */
public class ProxyCommandTOUCH implements Command {

    private User user;

    public ProxyCommandTOUCH(User user) {
        this.user = user;
    }

    @Override
    public String execute(String... args) {
        if (this.user.hasReadWriteAccess()) {
            Command commandTOUCH = new CommandTOUCH(this.user);
            return commandTOUCH.execute(args);
        } else {
            return AccessEnum.FORBIDDEN.getAccess();
        }
    }
}
