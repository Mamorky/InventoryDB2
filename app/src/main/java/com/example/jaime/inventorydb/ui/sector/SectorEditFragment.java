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
import com.example.jaime.inventorydb.ui.sector.contracts.SectorEditContract;
import com.example.jaime.inventorydb.ui.sector.presenters.SectorEditPresenter;
import com.example.jaime.inventorydb.ui.sector.presenters.SectorPresenter;

/**
 * Created by mamorky on 30/01/18.
 */

public class SectorEditFragment extends Fragment implements SectorEditContract.view{
    static final String TAG = "sectoreditfragment";
    public static final String SECTORSEDIT = "sectoredit";

    EditText edtNombre,edtShortName,edtDescripcion,edtIdDependencia;
    FloatingActionButton fab;
    SectorEditPresenter presenter;
    SectorEditFragment.SectorEditFragmentListener mCallback;

    Sector sectorModificar;

    interface SectorEditFragmentListener{
        void navigateToSector();
    }

    public static SectorEditFragment newInstance(Bundle bundle){
        SectorEditFragment sectorEditFragment = new SectorEditFragment();

        if (bundle != null)
            sectorEditFragment.setArguments(bundle);

        return sectorEditFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (SectorEditFragment.SectorEditFragmentListener) activity;
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

        edtNombre.setEnabled(false);
        edtShortName.setEnabled(false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sectorModificar = getArguments().getParcelable(SECTORSEDIT);

        presenter = new SectorEditPresenter(this);

        edtNombre.setText(sectorModificar.getName());
        edtShortName.setText(sectorModificar.getSortname());
        edtDescripcion.setText(sectorModificar.getDescription());
        edtIdDependencia.setText(String.valueOf(sectorModificar.getDependencyId()));

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

                presenter.editSector(sector);
            }
        });
    }

    @Override
    public void showEditError(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editSuccess() {
        mCallback.navigateToSector();
    }

}
