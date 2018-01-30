package com.example.jaime.inventorydb.data.db.repository;

import com.example.jaime.inventorydb.data.db.dao.DependencyDao;
import com.example.jaime.inventorydb.data.db.dao.SectorDao;
import com.example.jaime.inventorydb.data.db.model.Sector;
import com.example.jaime.inventorydb.ui.utils.InteractorCallback;

import java.util.ArrayList;

/**
 * Clase repositorio que contiene todos los datos de Sector.
 */

public class SectorRepository {
    private static SectorRepository instance;
    private ArrayList<Sector> sectors;
    private SectorDao sectorDao;
    private String error;


    private SectorRepository() {
        sectors = new ArrayList<>();
        sectorDao = new SectorDao();
        //initialize();
    }


    public static SectorRepository getInstance(){
        if (instance == null)
            instance = new SectorRepository();

        return instance;
    }


    /*private void initialize() {
        addSector(new Sector(1, "Armario_1", "ARM1",
                "Un armario muy carismático", 1, true, true));
        addSector(new Sector(2, "Armario_2", "ARM2",
                "Un armario poco carismático", 2, false, false));
        addSector(new Sector(3, "Armario_1", "ARM1",
                "Un armario muy carismático", 1, true, true));
        addSector(new Sector(4, "Armario_2", "ARM2",
                "Un armario poco carismático", 2, false, false));
        addSector(new Sector(5, "Armario_1", "ARM1",
                "Un armario muy carismático", 1, true, true));
        addSector(new Sector(6, "Armario_2", "ARM2",
                "Un armario poco carismático", 2, false, false));
        addSector(new Sector(7, "Armario_1", "ARM1",
                "Un armario muy carismático", 1, true, true));
        addSector(new Sector(8, "Armario_2", "ARM2",
                "Un armario poco carismático", 2, false, false));
        addSector(new Sector(9, "Armario_1", "ARM1",
                "Un armario muy carismático", 1, true, true));
        addSector(new Sector(10, "Armario_2", "ARM2",
                "Un armario poco carismático", 2, false, false));
    }*/


    public void addSector(Sector sector, InteractorCallback mCallback) {
        long id = sectorDao.add(sector);
        if(id == -1){
            error = "Error al añadir el Sector";
            mCallback.onError(error);
            return;
        }
        mCallback.onSuccess();
    }


    public ArrayList<Sector> getSectors() {
        return sectorDao.loadAll();
    }

    public void modifySector(Sector sector,InteractorCallback mCallBack) {
        long id = sectorDao.update(sector);
        if(id == 0){
            error = "Error al editar el Sector";
            mCallBack.onError(error);
            return;
        }
        mCallBack.onSuccess();
    }
}
