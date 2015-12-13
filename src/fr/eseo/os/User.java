package fr.eseo.os;

import fr.eseo.os.command.CommandEnum;
import fr.eseo.os.visitor.*;

import java.util.*;

import static fr.eseo.os.proxy.AccessEnum.READ_ONLY;
import static fr.eseo.os.proxy.AccessEnum.READ_WRITE;

public class User extends Observable {

    private String login;
    private String password;
    private Folder homeDir;
    private String access;

    private static Map<String, User> instances = new HashMap<>();
    private Deque<String> histories;


    /**
     * Creates a new User with a default home directory
     * @param login of the user
     * @param password of the user
     */
    private User(String login, String password, String access){
        this.login = login;
        this.password = password;
        this.homeDir = new Folder(this.login);
        this.access = access;
        this.histories = new ArrayDeque<>();
        this.initHomeDir();
    }

    public synchronized  static User getInstance(String login, String password, String access) {
        User user = instances.get(login);
        if (user == null) {
            user = new User(login, password, access);
            instances.put(login, user);
        }
        return user;
    }

    /**
     * @return the login of the user
     */
    public String getLogin(){
        return this.login;
    }

    /**
     * @return the password of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @return the home directory of the user
     */
    public Folder getHomeDir() {
        return this.homeDir;
    }

    /**
     * Initializes the home directory of the user with an example file and an example link
     */
    private void initHomeDir() {
        File file = new File("file1","Ceci est le contenu de file1...");
        Link link = new Link("link_file1",file);
        homeDir.addChild(file);
        homeDir.addChild(link);
    }

    /**
     * Stores data in the history of the user
     * @param command to store
     */
    public void addHistory(String command, Object caller){
        this.histories.add(command);
        this.setChanged();
        this.notifyObservers(caller);
    }

    /**
     * @return the history of the user
     */
    public Deque<String> getHistories() {
        return histories;
    }

    public Boolean hasReadOnlyAccess() {
        return this.access.equals(READ_ONLY.getAccess());
    }

    public Boolean hasReadWriteAccess() {
        return this.access.equals(READ_WRITE.getAccess());
    }

    /* ---------- USER'S COMMANDS ---------- */

    private String executeCommands(VisitorNode vn, String... args) {
        String result = "";
        if (args.length > 1) {
            Node node = this.getHomeDir().findNode(args[1]);
            CommandEnum commandEnum = CommandEnum.getEnum(args[0]);
            switch (commandEnum) {
                case LS:
                    if (node == null) {
                        result =  args[1] + " not found.";
                    } else {
                        result = node.accept(vn);
                    }
                    break;
                case CAT:
                    if (node == null) {
                        result =  args[1] + " not found.";
                    } else {
                        result = node.accept(vn);
                    }
                    break;
                case TOUCH:
                    if (node == null) {
                        node = new File(args[1], "");
                        homeDir.addChild(node);
                        result = node.accept(vn);
                    } else {
                        result = args[1] + " already exist.";
                    }
                    break;
                case MKDIR:
                    if (node == null) {
                        node = new Folder(args[1]);
                        homeDir.addChild(node);
                        result = node.accept(vn);
                    } else {
                        result = args[1] + " already exist.";
                    }
                    break;
                case RM:
                    if (node == null) {
                        result = args[1] + " does not exist.";
                    } else {
                        homeDir.getChildren().remove(node);
                        result = node.accept(vn);
                    }
                    break;
                case LN:
                    if (node == null) {
                        result = args[1] + " does not exist.";
                    } else if (args.length > 2){
                        Node link = new Link(args[2], node);
                        homeDir.addChild(link);
                        result = link.accept(vn);
                    } else {
                        result = args[0] + args[1] + " missing operand.";
                    }
                    break;
            }
        } else if (args[0].equals(CommandEnum.LS.getCommand())) {
            result = this.getHomeDir().accept(vn);
        } else {
            result = args[0] + " missing operand.";
        }
        return result;
    }

    public String executeCommandCAT(String... args) {
        VisitorNode vn = new VisitorCAT();
        return this.executeCommands(vn, args);
    }

    public String executeCommandLS(String... args) {
        VisitorNode vn = new VisitorLS();
        return this.executeCommands(vn, args);
    }

    public String executeCommandRM(String... args) {
        VisitorNode vn = new VisitorRM();
        return executeCommands(vn, args);
    }

    public String executeCommandMKDIR(String... args) {
        VisitorNode vn = new VisitorMKDIR();
        return executeCommands(vn, args);
    }

    public String executeCommandTOUCH(String... args) {
        VisitorNode vn = new VisitorTOUCH();
        return executeCommands(vn, args);
    }

    public String executeCommandLN(String... args) {
        VisitorNode vn = new VisitorLN();
        return executeCommands(vn, args);
    }

}
