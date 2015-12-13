package fr.eseo.os;

import fr.eseo.os.command.CommandEnum;
import fr.eseo.os.proxy.AccessEnum;
import fr.eseo.os.visitor.VisitorCAT;
import fr.eseo.os.visitor.VisitorLS;
import fr.eseo.os.visitor.VisitorNode;
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

    private String executeCommand(VisitorNode vn, String... args) {
        if (args.length > 1) {
            Node node = this.getHomeDir().findNode(args[1]);
            if (node != null) {
                return node.accept(vn);
            } else {
                return args[1] + "not found.";
            }
        } else {
            return this.getHomeDir().accept(vn);
        }
    }

    public String executeCommandCAT(String... args) {
        VisitorNode vcat = new VisitorCAT();
        return this.executeCommand(vcat, args);
    }

    public String executeCommandLS(String... args) {
        VisitorNode vls = new VisitorLS();
        return this.executeCommand(vls, args);
    }

    public String executeCommandRM(String... args) {
        //TODO
        return null;
    }

    public String executeCommandMKDIR(String... args) {
        //TODO
        return null;
    }

    public String executeCommandTOUCH(String... args) {
        //TODO
        return null;
    }

    public String executeCommandLN(String... args) {
        //TODO
        return null;
    }

}
