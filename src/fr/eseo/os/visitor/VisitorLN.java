package fr.eseo.os.visitor;

import fr.eseo.os.File;
import fr.eseo.os.Folder;
import fr.eseo.os.Link;

public class VisitorLN implements VisitorNode {

    @Override
    public String visit(File file) {
        return file.getName() + " file was created.";
    }

    @Override
    public String visit(Folder folder) {
        return folder.getName() + " folder was created.";
    }

    @Override
    public String visit(Link link) {
        return link.getName() + " link was created.";
    }
}
