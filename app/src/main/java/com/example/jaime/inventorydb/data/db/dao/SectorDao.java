package com.example.jaime.inventorydb.data.db.dao;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.provider.BaseColumns;

import com.example.jaime.inventorydb.data.db.InventoryContract;
import com.example.jaime.inventorydb.data.db.InventoryOpenHelper;
import com.example.jaime.inventorydb.data.db.model.Sector;

import java.util.ArrayList;

/**
 * Created by mamorky on 30/01/18.
 */

public class SectorDao {
    public ArrayList<Sector> loadAll(){
        ArrayList<Sector> sectors = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();

        Cursor cursor = sqLiteDatabase.query(InventoryContract.SectorEntry.TABLE_NAME,InventoryContract.SectorEntry.ALL_COLUMN,null,null,null,null,InventoryContract.SectorEntry.DEFAULT_SORT,null);

        if(cursor.moveToFirst()){
            do{
                Sector dependency = new Sector(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),true,true);
                sectors.add(dependency);
            }
            while (cursor.moveToNext());
        }

        InventoryOpenHelper.getInstance().closeDatabase();

        return sectors;
    }

    public long add(Sector sector){
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.SectorEntry.COLUMN_NAME,sector.getName());
        contentValues.put(InventoryContract.SectorEntry.COLUMN_SORTNAME,sector.getSortname());
        contentValues.put(InventoryContract.SectorEntry.COLUMN_DESCRIPTION,sector.getDescription());
        contentValues.put(InventoryContract.SectorEntry.COLUMN_DEPENDENCY_ID,sector.getDependencyId());
        contentValues.put(InventoryContract.SectorEntry.COLUMN_IMAGE,"dsadad");

        long id = sqLiteDatabase.insert(InventoryContract.SectorEntry.TABLE_NAME,null,contentValues);

        return id;
    }

    public long delete(Sector sector){
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();
        String whereClause = BaseColumns._ID+"=?";
        String[] whereArgs = new String[]{""+String.valueOf(sector.get_ID())};

        int rowAfecteds = sqLiteDatabase.delete(InventoryContract.SectorEntry.TABLE_NAME,whereClause,whereArgs);

        InventoryOpenHelper.getInstance().closeDatabase();

        return rowAfecteds;
    }

    public boolean exists(Sector sector){
        return false;
    }

    public int update(Sector sector) {
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();
        ContentValues contentValues = createContent(sector);
        String whereClause = BaseColumns._ID+"=?";
        String[] whereArgs = new String[]{""+String.valueOf(sector.get_ID())};

        int rowAfecteds = sqLiteDatabase.update(InventoryContract.SectorEntry.TABLE_NAME,contentValues,whereClause,whereArgs);

        InventoryOpenHelper.getInstance().closeDatabase();

        return rowAfecteds;
    }

    private ContentValues createContent(Sector sector){
        ContentValues contentValues = new ContentValues();

        contentValues.put(InventoryContract.SectorEntry.COLUMN_NAME,sector.getName());
        contentValues.put(InventoryContract.SectorEntry.COLUMN_SORTNAME,sector.getSortname());
        contentValues.put(InventoryContract.SectorEntry.COLUMN_DESCRIPTION,sector.getDescription());
        contentValues.put(InventoryContract.SectorEntry.COLUMN_DEPENDENCY_ID,sector.getDependencyId());


        return contentValues;
    }

    public class MyTask extends AsyncTask<SQLiteDatabase, Void, Void> {
        ArrayList<Sector> sectors;
        ProgressDialog progressDialog;

        public MyTask(ArrayList<Sector> sectors){
            this.sectors = sectors;
        }

        @Override
        protected void onPreExecute() {
            //progressDialog = new ProgressDialog(InventoryApplication.getContext());
            //progressDialog.setMessage("Cargando dependencias ...");
            //progressDialog.show();
        }

        @Override
        protected Void doInBackground(SQLiteDatabase... sqLiteDatabase) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //progressDialog.dismiss();
        }
    }
}
