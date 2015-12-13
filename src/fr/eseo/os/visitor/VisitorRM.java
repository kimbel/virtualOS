package fr.eseo.os.visitor;

import fr.eseo.os.File;
import fr.eseo.os.Folder;
import fr.eseo.os.Link;

public class VisitorRM implements VisitorNode {

    @Override
    public String visit(File file) {
        return file.getName() + " file was deleted.";
    }

    @Override
    public String visit(Folder folder) {
        return " folder was deleted.";
    }

    @Override
    public String visit(Link link) {
        return link.getName() + " link was deleted.";
    }
}
