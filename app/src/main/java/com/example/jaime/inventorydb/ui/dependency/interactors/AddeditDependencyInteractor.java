package com.example.jaime.inventorydb.ui.dependency.interactors;

import com.example.jaime.inventorydb.data.db.model.Dependency;
import com.example.jaime.inventorydb.data.db.repository.DependencyRepository;
import com.example.jaime.inventorydb.ui.utils.InteractorCallback;
import com.example.jaime.inventorydb.ui.dependency.contracts.AddeditDependencyContract;

/**
 * Created by usuario on 24/11/17.
 */

public class AddeditDependencyInteractor implements AddeditDependencyContract.Interactor,InteractorCallback{

    OnAddeditFinishedListener listener;

    public AddeditDependencyInteractor(OnAddeditFinishedListener listener){
        this.listener = listener;
    }

    @Override
    public void validateDependecy(String name, String sortname, String description, OnAddeditFinishedListener listener) {
        if (name.isEmpty())
            listener.onNameEmptyError();
        else if (sortname.isEmpty())
            listener.onSortNameEmptyError();
        else if (sortname.length() < 2 || sortname.length() > 5)
            listener.onSortNameLengthError();
        else if (description.isEmpty())
            listener.onDescriptionEmptyError();
        else if (DependencyRepository.getInstance().validateDependency(name, sortname)) {
            DependencyRepository.getInstance().addDependency(new Dependency(11, name, sortname, description,"dsadsad"),this);
            listener.onSuccess();
        } else
            listener.onDependencyExists();
    }


    @Override
    public void addDependency(String name, String sortname, String description) {
        Dependency dependency = new Dependency(11, name, sortname, description,"dsadasd");
        DependencyRepository.getInstance().addDependency(dependency,this);
    }


    @Override
    public void updateDependency(Dependency dependency, OnAddeditFinishedListener listener) {
        DependencyRepository.getInstance().updateDependency(dependency,this);
        listener.onSuccess();
    }

    @Override
    public void onError(String error) {
        listener.onDBError(error);
    }

    @Override
    public void onSuccess() {
        listener.onSuccess();
    }
}
