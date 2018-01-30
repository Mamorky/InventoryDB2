package com.example.jaime.inventorydb.ui.sector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jaime.inventorydb.R;
import com.example.jaime.inventorydb.adapters.SectorAdapter;
import com.example.jaime.inventorydb.data.db.model.Sector;
import com.example.jaime.inventorydb.ui.sector.contracts.SectorContract;
import com.example.jaime.inventorydb.ui.sector.presenters.SectorPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamorky on 26/01/18.
 */

public class SectorFragment extends ListFragment implements SectorContract.View{
    private static final String SECTORS_MODIFIED_KEY = "sectormodify";
    public static final String TAG = "sectorfragmenttag";

    private RecyclerView rvSector;
    private SectorAdapter mAdapter;
    private Toolbar mToolbar;
    private FloatingActionButton fab;

    private SectorAdapter.OnItemClickListener mListener;
    private SectorContract.Presenter mPresenter;

    private SectorFragmentListener mCallback;

    private Bundle savedInstanceState;

    interface SectorFragmentListener{
        void addSector(Bundle bundle);
        void editSector(Bundle bundle);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mCallback = (SectorFragmentListener) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(getActivity().getLocalClassName() + " must implements ListDepedencyListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sector,container,false);

        mToolbar = root.findViewById(R.id.tb_sector);
        //mLayout = (ConstraintLayout) findViewById(R.id.layout_sector);
        rvSector = root.findViewById(R.id.rv_sector);
        rvSector.setHasFixedSize(true);
        rvSector.setLayoutManager(new LinearLayoutManager(getActivity()));
        fab = root.findViewById(R.id.fab_sectorAdd);

        mListener = new SectorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Sector sector) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(SectorEditFragment.SECTORSEDIT,sector);

                mCallback.editSector(bundle);
            }
        };

        mPresenter = new SectorPresenter(this);
        mPresenter.loadSectors();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;

        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        rvSector.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.addSector(null);
            }
        });
    }

    public static SectorFragment newInstance(Bundle bundle) {
        SectorFragment sectorFragment = new SectorFragment();

        if (bundle != null)
            sectorFragment.setArguments(bundle);

        return sectorFragment;
    }

    @Override
    public void showSectors(List<Sector> sectors) {
        if (savedInstanceState != null)
            mAdapter = new SectorAdapter(savedInstanceState.<Sector>getParcelableArrayList(SECTORS_MODIFIED_KEY), mListener, (ArrayList<Sector>) sectors);
        else
            mAdapter = new SectorAdapter(mListener, (ArrayList<Sector>) sectors);
    }
}
