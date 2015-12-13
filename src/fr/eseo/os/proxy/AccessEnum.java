package fr.eseo.os.proxy;

/**
 * Created by axel on 13/12/15.
 */
public enum AccessEnum {

    READ_ONLY ("r"),
    READ_WRITE ("rw"),
    FORBIDDEN ("Vous n'avez pas les droits d'accès !");

    private String access;

    private AccessEnum(String access) {
        this.access = access;
    }

    public String getAccess() {
        return access;
    }
}
