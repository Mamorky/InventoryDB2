package com.example.jaime.inventorydb.data.db.repository;

import com.example.jaime.inventorydb.data.db.dao.DependencyDao;
import com.example.jaime.inventorydb.data.db.model.Dependency;
import com.example.jaime.inventorydb.ui.utils.InteractorCallback;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Repositorio con los datos de Dependency.
 */
public class DependencyRepository {
    private ArrayList<Dependency> mDependencies;
    private static DependencyRepository mInstance;
    private DependencyDao dependencyDao;

    private String error;

    private DependencyRepository() {
        this.mDependencies = new ArrayList<>();
        dependencyDao = new DependencyDao();
        //initialize();
    }


    public static DependencyRepository getInstance() {
        if (mInstance == null)
            mInstance = new DependencyRepository();

        return mInstance;
    }

    /*
    private void initialize() {
        addDependency(new Dependency(1, "1º Ciclo Formativo Grado Superior",
                "ZZZ", "1CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(2, "2º Ciclo Formativo Grado Superior",
                "GGG", "2CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(3, "1º Ciclo Formativo Grado Superior",
                "BBB", "1CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(4, "2º Ciclo Formativo Grado Superior",
                "BBB", "2CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(5, "1º Ciclo Formativo Grado Superior",
                "HHH", "1CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(6, "2º Ciclo Formativo Grado Superior",
                "HHH", "2CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(7, "1º Ciclo Formativo Grado Superior",
                "EEE", "1CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(8, "2º Ciclo Formativo Grado Superior",
                "EEE", "2CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(9, "1º Ciclo Formativo Grado Superior",
                "KKK", "1CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(10, "2º Ciclo Formativo Grado Superior",
                "KKK", "2CFGS Desarrollo de Aplicaciones Multiplataformma"));
    }
    */
    public ArrayList<Dependency> getDependencies() {
        return dependencyDao.loadAll();
    }


    public ArrayList<Dependency> getDependenciesOrderByName() {
        Collections.sort(mDependencies, Dependency.COMPARATOR_NAME);
        return mDependencies;
    }


    public ArrayList<Dependency> getDependenciesOrderByID() {
        Collections.sort(mDependencies, Dependency.COMPARATOR_ID);
        return mDependencies;
    }

    public int getLastId() {
        return mDependencies.get(mDependencies.size() - 1).get_ID();
    }


    public boolean validateDependency(String name, String sortname) {
        boolean result = true;
        int index = 0;

        while (index < mDependencies.size()) {
            if (name.equals(mDependencies.get(index).getName()) || sortname.equals(mDependencies.get(index).getShortname())) {
                result = false;
                index = mDependencies.size();
            } else
                index++;
        }

        return result;
    }


    public void editDependency(Dependency dependency) {
        int index = 0;

        while (index < mDependencies.size()) {
            if (dependency.get_ID() == mDependencies.get(index).get_ID()) {
                mDependencies.get(index).setDescription(dependency.getDescription());
                index = mDependencies.size();
            } else
                index++;
        }
    }


    /*public void deleteDependency(Dependency dependency) {
        Iterator<Dependency> iterator = mDependencies.iterator();
        Dependency tmpDependency;

        while (iterator.hasNext()) {
            tmpDependency = iterator.next();

            if (dependency.get_ID() == tmpDependency.get_ID()) {
                iterator.remove();
                break;
            }
        }
    }*/


    public Dependency getDependencyAtPosition(int position){
        return mDependencies.get(position);
    }

    /**
     * Método que añade una dependencia a la base de datos
     * @param dependency
     * */
    public void addDependency(Dependency dependency,InteractorCallback callback){
        long id = dependencyDao.add(dependency);
        if(id==-1){
            error = "No se añadio la dependencia "+dependency.getName();
            callback.onError(error);
        }
        else{
            callback.onSuccess();
        }
    }

    public void updateDependency(Dependency dependency,InteractorCallback callback){
        long rows = dependencyDao.update(dependency);
        if(rows==0){
            error = "No hay filas a actualizar o se produjo un error";
            callback.onError(error);
        }
        else {
            callback.onSuccess();
        }
    }

    public void deleteDependency(Dependency dependency,InteractorCallback callback){
        try {
        long rows = dependencyDao.delete(dependency);
            if(rows==0)
                callback.onError(error);
            else
                callback.onSuccess();
        }
        catch (Exception e){
            callback.onError("Error: "+e.getMessage());
        }
    }
}
