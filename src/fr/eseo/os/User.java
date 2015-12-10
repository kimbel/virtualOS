package fr.eseo.os;

import java.util.HashMap;
import java.util.Observable;
import java.util.Map;

public class User extends Observable {

    private String login;
    private String password;
    private Folder homeDir;
    private Historic historic;
    private static Map<String, User> instances = new HashMap<>();

    /**
     * Creates a new User with a default home directory
     * @param login of the user
     * @param password of the user
     */
    private User(String login, String password){
        this.login = login;
        this.password = password;
        this.homeDir = new Folder(this.login);
        this.historic = new Historic(this);
        this.initHomeDir();
    }

    public synchronized  static User getInstance(String login, String password) {
        User user = instances.get(login);
        if (user == null) {
            user = new User(login, password);
            instances.put(login, user);
        }
        return user;
    }

    public void addCommand(String command) {
        this.getHistoric().getCommands().add(command);
        this.setChanged();
        this.notifyObservers();
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

    public Historic getHistoric() {
        return historic;
    }

    public void setHistoric(Historic historic) {
        this.historic = historic;
    }
}
