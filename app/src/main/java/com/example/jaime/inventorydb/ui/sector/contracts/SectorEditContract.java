package com.example.jaime.inventorydb.ui.sector.contracts;

import com.example.jaime.inventorydb.data.db.model.Sector;
import com.example.jaime.inventorydb.ui.base.BasePresenter;
import com.example.jaime.inventorydb.ui.base.BaseView;

/**
 * Created by mamorky on 30/01/18.
 */

public interface SectorEditContract {
    interface view extends BaseView {
        void showEditError(String error);
        void editSuccess();
    }

    interface presenter extends BasePresenter {
        void editSector(Sector sector);
    }

    interface interactor{
        void editSector(Sector sector);

        interface OnEditSuccess{
            void onEditSuccess();
            void onError(String error);
        }
    }
}
