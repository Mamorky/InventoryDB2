package com.example.jaime.inventorydb.ui.dependency.interactors;

import com.example.jaime.inventorydb.data.db.model.Dependency;
import com.example.jaime.inventorydb.data.db.repository.DependencyRepository;
import com.example.jaime.inventorydb.ui.utils.InteractorCallback;
import com.example.jaime.inventorydb.ui.dependency.contracts.ListDependencyContract;

import java.util.ArrayList;

/**
 * Created by usuario on 27/11/17.
 */

public class ListDependencyInteractor implements ListDependencyContract.Interactor,InteractorCallback{

    public OnFinishedLoadDependency listener;

    public ListDependencyInteractor(OnFinishedLoadDependency listener){
        this.listener = listener;
    }

    @Override
    public void loadDependencies(OnFinishedLoadDependency onFinishedLoadDependency) {
        onFinishedLoadDependency.onSuccess(DependencyRepository.getInstance().getDependencies());
    }


    @Override
    public void loadDependenciesOrderByName(OnFinishedLoadDependency onFinishedLoadDependency) {
        onFinishedLoadDependency.onSuccess(DependencyRepository.getInstance().getDependenciesOrderByName());
    }


    @Override
    public void loadDependenciesOrderByID(OnFinishedLoadDependency onFinishedLoadDependency) {
        onFinishedLoadDependency.onSuccess(DependencyRepository.getInstance().getDependenciesOrderByID());
    }


    @Override
    public void deleteDependency(Dependency dependency, OnFinishedLoadDependency onFinishedLoadDependency) {
        DependencyRepository.getInstance().deleteDependency(dependency,this);
        onFinishedLoadDependency.onSuccess(DependencyRepository.getInstance().getDependencies());
    }


    @Override
    public void deleteDependencies(ArrayList<Dependency> dependencies, OnFinishedLoadDependency listener) {
        for (int i = 0; i < dependencies.size(); i++)
            DependencyRepository.getInstance().deleteDependency(dependencies.get(i),this);

        listener.onSuccess(DependencyRepository.getInstance().getDependencies());
    }


    @Override
    public Dependency getDependency(int position) {
        return DependencyRepository.getInstance().getDependencyAtPosition(position);
    }

    @Override
    public void onError(String error) {
        listener.onError(error);
    }

    @Override
    public void onSuccess() {
    }
}
