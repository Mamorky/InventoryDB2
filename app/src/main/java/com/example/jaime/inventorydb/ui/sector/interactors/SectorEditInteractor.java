package com.example.jaime.inventorydb.ui.sector.interactors;

import com.example.jaime.inventorydb.data.db.model.Sector;
import com.example.jaime.inventorydb.data.db.repository.SectorRepository;
import com.example.jaime.inventorydb.ui.sector.contracts.SectorEditContract;
import com.example.jaime.inventorydb.ui.utils.InteractorCallback;

/**
 * Created by mamorky on 30/01/18.
 */

public class SectorEditInteractor implements SectorEditContract.interactor,InteractorCallback {
    OnEditSuccess editSuccess;

    public SectorEditInteractor(OnEditSuccess editSuccess){
        this.editSuccess = editSuccess;
    }

    @Override
    public void editSector(Sector sector) {
        SectorRepository.getInstance().modifySector(sector,this);
    }

    @Override
    public void onError(String error) {
        editSuccess.onError(error);
    }

    @Override
    public void onSuccess() {
        editSuccess.onEditSuccess();
    }
}
