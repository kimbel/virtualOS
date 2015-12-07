package fr.eseo.os;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String login;
    private String password;
    private Folder homeDir;
    private Historic historic;
    private static Set<User> instances = new HashSet<>();

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
        User user = null;

        for (User u : instances) {
            if (u.getLogin().equals(login)) {
                user = u;
            }
        }

        if (user != null) {
            return user;
        } else {
            user = new User(login, password);
            instances.add(user);
            return user;
        }
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
