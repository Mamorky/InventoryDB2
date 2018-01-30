package com.example.jaime.inventorydb.ui.sector;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.example.jaime.inventorydb.R;
import com.example.jaime.inventorydb.adapters.SectorAdapter;
import com.example.jaime.inventorydb.data.db.model.Sector;

public class SectorActivity extends AppCompatActivity implements SectorFragment.SectorFragmentListener,SectorAddFragment.SectorAddFragmentListener,SectorEditFragment.SectorEditFragmentListener{

    SectorFragment mSectorFragment = new SectorFragment();
    SectorAddFragment mSectorAddFragment = new SectorAddFragment();
    SectorEditFragment mSectorEditFragment = new SectorEditFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sector);

        mSectorFragment = (SectorFragment) getSupportFragmentManager().findFragmentByTag(SectorFragment.TAG);

        if (mSectorFragment == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            mSectorFragment = SectorFragment.newInstance(null);
            transaction.add(android.R.id.content, mSectorFragment, SectorFragment.TAG).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_sector, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void addSector(Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content,mSectorAddFragment,SectorAddFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void editSector(Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mSectorEditFragment = SectorEditFragment.newInstance(bundle);
        fragmentTransaction.replace(android.R.id.content,mSectorEditFragment,SectorEditFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void navigateToSector() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content,mSectorFragment,SectorFragment.TAG);
        fragmentTransaction.commit();
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ArrayList<Sector> sectorsModified = mAdapter.getSectorsModified();

        for (int i = 0; i < sectorsModified.size(); i++)
            SectorRepository.getInstance().modifySector(sectorsModified.get(i).get_ID(), sectorsModified.get(i).isEnabled());

        Snackbar.make(mLayout, "Datos guardados con éxito", Snackbar.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }*/
}
