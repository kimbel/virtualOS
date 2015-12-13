package fr.eseo.os.command;

/**
 * Created by axel on 12/12/15.
 */
public enum CommandEnum {

    LS ("ls"),
    CAT ("cat"),
    MKDIR ("mkdir"),
    RM ("rm"),
    TOUCH ("touch"),
    LN ("ln"),
    NOT_FOUND ("commande introuvable");

    private String command;

    private CommandEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static CommandEnum getEnum(String command) {
        if (command != null) {
            for (CommandEnum value : values()) {
                if (value.getCommand().equals(command)) {
                    return value;
                }
            }
        }
        return  NOT_FOUND;
    }
}
