package domain;

import java.util.HashMap;

public interface Lukuvinkki {
	String getID();
	Type getType();
	String view();
	String toString();
	boolean getLuettu();
}