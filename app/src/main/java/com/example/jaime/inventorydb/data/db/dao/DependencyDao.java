package com.example.jaime.inventorydb.data.db.dao;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.provider.BaseColumns;

import com.example.jaime.inventorydb.InventoryApplication;
import com.example.jaime.inventorydb.data.db.InventoryContract;
import com.example.jaime.inventorydb.data.db.InventoryOpenHelper;
import com.example.jaime.inventorydb.data.db.model.Dependency;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by mamorky on 22/01/18.
 */

public class DependencyDao {
    /**
     * Método que devuelve un cursor con todas las dependencias de la base de datos
     * */
    public ArrayList<Dependency> loadAll(){
        ArrayList<Dependency> dependencies = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();

        Cursor cursor = sqLiteDatabase.query(InventoryContract.DependencyEntry.TABLE_NAME,InventoryContract.DependencyEntry.ALL_COLUMN,null,null,null,null,InventoryContract.DependencyEntry.DEFAULT_SORT,null);

        if(cursor.moveToFirst()){
            do{
                Dependency dependency = new Dependency(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                dependencies.add(dependency);
            }
            while (cursor.moveToNext());
        }

        InventoryOpenHelper.getInstance().closeDatabase();

        return dependencies;
    }

    public long add(Dependency dependency){
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.DependencyEntry.COLUMN_NAME,dependency.getName());
        contentValues.put(InventoryContract.DependencyEntry.COLUMN_SORTNAME,dependency.getShortname());
        contentValues.put(InventoryContract.DependencyEntry.COLUMN_DESCRIPTION,dependency.getDescription());
        contentValues.put(InventoryContract.DependencyEntry.COLUMN_IMAGE,dependency.getImageName());

        long id = sqLiteDatabase.insert(InventoryContract.DependencyEntry.TABLE_NAME,null,contentValues);

        return id;
    }

    public long delete(Dependency dependency){
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();
        String whereClause = BaseColumns._ID+"=?";
        String[] whereArgs = new String[]{""+String.valueOf(dependency.get_ID())};

        int rowAfecteds = sqLiteDatabase.delete(InventoryContract.DependencyEntry.TABLE_NAME,whereClause,whereArgs);

        InventoryOpenHelper.getInstance().closeDatabase();

        return rowAfecteds;
    }

    public boolean exists(Dependency dependency){
        return false;
    }

    public int update(Dependency dependency) {
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();
        ContentValues contentValues = createContent(dependency);
        String whereClause = BaseColumns._ID+"=?";
        String[] whereArgs = new String[]{""+String.valueOf(dependency.get_ID())};

        int rowAfecteds = sqLiteDatabase.update(InventoryContract.DependencyEntry.TABLE_NAME,contentValues,whereClause,whereArgs);

        InventoryOpenHelper.getInstance().closeDatabase();

        return rowAfecteds;
    }

    private ContentValues createContent(Dependency dependency){
        ContentValues contentValues = new ContentValues();

        contentValues.put(InventoryContract.DependencyEntry.COLUMN_NAME,dependency.getName());
        contentValues.put(InventoryContract.DependencyEntry.COLUMN_SORTNAME,dependency.getShortname());
        contentValues.put(InventoryContract.DependencyEntry.COLUMN_DESCRIPTION,dependency.getDescription());
        contentValues.put(InventoryContract.DependencyEntry.COLUMN_IMAGE,dependency.getImageName());

        return contentValues;
    }

    public class MyTask extends AsyncTask<SQLiteDatabase, Void, Void>{
        ArrayList<Dependency> dependencies;
        ProgressDialog progressDialog;

        public MyTask(ArrayList<Dependency> dependencies){
            this.dependencies = dependencies;
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
