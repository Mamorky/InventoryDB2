package com.example.jaime.inventorydb.ui.sector.presenters;

import com.example.jaime.inventorydb.data.db.model.Sector;
import com.example.jaime.inventorydb.ui.sector.SectorEditFragment;
import com.example.jaime.inventorydb.ui.sector.contracts.SectorEditContract;
import com.example.jaime.inventorydb.ui.sector.interactors.SectorEditInteractor;

/**
 * Created by mamorky on 30/01/18.
 */

public class SectorEditPresenter implements SectorEditContract.presenter,SectorEditContract.interactor.OnEditSuccess{
    private SectorEditFragment view;
    private SectorEditInteractor interactor;

    public SectorEditPresenter(SectorEditFragment view){
        this.view = view;
        this.interactor = new SectorEditInteractor(this);
    }


    @Override
    public void editSector(Sector sector) {
        interactor.editSector(sector);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onEditSuccess() {
        view.editSuccess();
    }

    @Override
    public void onError(String error) {
        view.showEditError(error);
    }
}
