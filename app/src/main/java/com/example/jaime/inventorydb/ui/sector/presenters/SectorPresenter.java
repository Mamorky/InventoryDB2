package com.example.jaime.inventorydb.ui.sector.presenters;

import com.example.jaime.inventorydb.data.db.model.Sector;
import com.example.jaime.inventorydb.ui.sector.SectorAddFragment;
import com.example.jaime.inventorydb.ui.sector.SectorFragment;
import com.example.jaime.inventorydb.ui.sector.contracts.SectorContract;
import com.example.jaime.inventorydb.ui.sector.interactors.SectorInteractor;

import java.util.List;

/**
 * Created by mamorky on 26/01/18.
 */

public class SectorPresenter implements SectorContract.Presenter,SectorContract.Interactor.OnAddSuccess {
    private SectorFragment view;
    private SectorAddFragment viewAdd;
    private SectorInteractor interactor;

    public SectorPresenter(SectorFragment view){
        this.view = view;
        this.interactor = new SectorInteractor(this);
    }

    public SectorPresenter(SectorAddFragment view){
        this.viewAdd = view;
        this.interactor = new SectorInteractor(this);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void loadSectors() {
        interactor.loadSector(this);
    }

    @Override
    public void addSector(Sector sector) {
        interactor.addSector(sector);
    }

    @Override
    public void onSuccess(List<Sector> sectors) {
        view.showSectors(sectors);
    }

    @Override
    public void onAddSuccess() {
        viewAdd.addSuccess();
    }

    @Override
    public void onError(String error) {
        viewAdd.showAddError(error);
    }
}
