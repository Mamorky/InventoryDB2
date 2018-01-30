package com.example.jaime.inventorydb.ui.sector.contracts;

import com.example.jaime.inventorydb.data.db.model.Sector;
import com.example.jaime.inventorydb.ui.base.BasePresenter;
import com.example.jaime.inventorydb.ui.base.BaseView;

import java.util.List;

/**
 * Created by mamorky on 26/01/18.
 */

public interface SectorContract {
    interface View extends BaseView{
        void showSectors(List<Sector> sectors);
    }

    interface ViewAdd extends BaseView{
        void showAddError(String error);
        void addSuccess();
    }

    interface Presenter extends BasePresenter,Interactor.OnLoadSuccess{
        void loadSectors();
        void addSector(Sector sector);
    }

    interface Interactor{
        void loadSector(OnLoadSuccess onLoadSuccess);
        void addSector(Sector sector);

        interface OnLoadSuccess{
            void onSuccess(List<Sector> sectors);
            void onError(String error);
        }

        interface OnAddSuccess{
            void onAddSuccess();
            void onError(String error);
        }
    }
}
