package com.example.jaime.inventorydb.ui.sector;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jaime.inventorydb.R;
import com.example.jaime.inventorydb.data.db.model.Sector;
import com.example.jaime.inventorydb.ui.sector.contracts.SectorContract;
import com.example.jaime.inventorydb.ui.sector.presenters.SectorPresenter;

/**
 * Created by mamorky on 30/01/18.
 */

public class SectorAddFragment extends Fragment implements SectorContract.ViewAdd{
    static final String TAG = "sectoraddfragment";

    EditText edtNombre,edtShortName,edtDescripcion,edtIdDependencia;
    FloatingActionButton fab;
    SectorPresenter presenter;
    SectorAddFragmentListener mCallback;

    interface SectorAddFragmentListener{
        void navigateToSector();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (SectorAddFragmentListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_addedit_sector,container,false);

        edtNombre = root.findViewById(R.id.edtNombreSector);
        edtShortName = root.findViewById(R.id.edtShortNameSector);
        edtDescripcion = root.findViewById(R.id.edtDescSector);
        edtIdDependencia = root.findViewById(R.id.edtIdDepSector);
        fab = root.findViewById(R.id.fabSaveSector);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new SectorPresenter(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sector sector = new Sector(12,
                        edtNombre.getText().toString(),
                        edtShortName.getText().toString(),
                        edtDescripcion.getText().toString(),
                        Integer.valueOf(edtIdDependencia.getText().toString()),
                        false,
                        false);

                presenter.addSector(sector);
            }
        });
    }

    @Override
    public void showAddError(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addSuccess() {
        mCallback.navigateToSector();
    }
}
