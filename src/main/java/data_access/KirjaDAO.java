package data_access;

import domain.Lukuvinkki;

import java.util.ArrayList;

public interface KirjaDAO {
	ArrayList<Lukuvinkki> getAll();
}