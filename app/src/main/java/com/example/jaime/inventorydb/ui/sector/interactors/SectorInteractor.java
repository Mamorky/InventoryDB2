package com.example.jaime.inventorydb.ui.sector.interactors;

import com.example.jaime.inventorydb.data.db.model.Sector;
import com.example.jaime.inventorydb.data.db.repository.SectorRepository;
import com.example.jaime.inventorydb.ui.sector.contracts.SectorContract;
import com.example.jaime.inventorydb.ui.utils.InteractorCallback;

/**
 * Created by mamorky on 26/01/18.
 */

public class SectorInteractor implements SectorContract.Interactor,InteractorCallback{

    private OnAddSuccess addSuccess;

    public SectorInteractor(OnAddSuccess addSuccess){
        this.addSuccess = (OnAddSuccess) addSuccess;
    }

    @Override
    public void loadSector(OnLoadSuccess onLoadSuccess) {
        onLoadSuccess.onSuccess(SectorRepository.getInstance().getSectors());
    }

    @Override
    public void addSector(Sector sector) {
        SectorRepository.getInstance().addSector(sector,this);
    }

    @Override
    public void onError(String error) {
        addSuccess.onError(error);
    }

    @Override
    public void onSuccess() {
        addSuccess.onAddSuccess();
    }
}
