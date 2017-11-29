/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

import domain.Blogi;
import domain.Lukuvinkki;
import domain.StubBlogi;
import java.util.ArrayList;


public class StubBlogiDAO implements BlogiDAO{

    @Override
    public ArrayList<Lukuvinkki> getAll() {
        ArrayList<Lukuvinkki> list = new ArrayList<>();
        list.add(new StubBlogi());
        list.add(new StubBlogi());
        return list;
    }

    @Override
    public void save(Blogi blogi) {
        //not yet done
    }
    
}
