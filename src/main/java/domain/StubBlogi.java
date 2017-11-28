package domain;

import tools.TextTools;

public class StubBlogi implements Lukuvinkki {
    @Override
    public String getID() {
        return null;
    }

    @Override
    public String toString() {
        int maxLength = 20;
        String columnRepresentation = TextTools.fit("jotain", 20) + " | ";
        columnRepresentation += TextTools.fit("jotain muuta", 20) + " | ";
        columnRepresentation += TextTools.fit("vielä jotain muuta", 20) + " | ";
        return columnRepresentation;
    }
}
