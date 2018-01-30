package com.example.jaime.inventorydb.data.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Debug;
import android.util.Log;

import com.example.jaime.inventorydb.InventoryApplication;
import com.example.jaime.inventorydb.data.db.InventoryContract;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by usuario on 19/01/18.
 */

public class InventoryOpenHelper extends SQLiteOpenHelper {
    //No copies los atributos que hay para cada hilo
    private volatile static InventoryOpenHelper mInstance;

    private SQLiteDatabase sqLiteDatabase;
    private AtomicInteger openCounter = new AtomicInteger();
    private SQLiteDatabase mDatabase;


    private InventoryOpenHelper() {
        super(InventoryApplication.getContext(), InventoryContract.DATABASE_NAME, null,
                InventoryContract.DATABASE_VERSION);
    }


    public synchronized static InventoryOpenHelper getInstance() {
        if (mInstance == null)
            mInstance = new InventoryOpenHelper();

        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
                db.beginTransaction();
                db.execSQL(InventoryContract.DependencyEntry.SQL_CREATE_ENTRIES);
                db.execSQL(InventoryContract.SectorEntry.SQL_CREATE_ENTRIES);

                db.execSQL(InventoryContract.DependencyEntry.SQL_INSERT_ENTRIES);
                db.execSQL(InventoryContract.SectorEntry.SQL_INSERT_ENTRIES);
                db.setTransactionSuccessful();
        }catch (SQLiteException e){
            Log.d(e.toString()+"CreateInventory",e.getMessage());
        }finally {
            db.endTransaction();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(InventoryContract.DependencyEntry.SQL_DELETE_ENTRIES);
        db.execSQL(InventoryContract.SectorEntry.SQL_DELETE_ENTRIES);

        onCreate(db);
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                db.setForeignKeyConstraintsEnabled(true);
            else
                db.execSQL("PRAGMA foreign_keys=1");
        }
    }


    public synchronized SQLiteDatabase openDatabase() {
        if(openCounter.incrementAndGet() == 1){
            sqLiteDatabase = getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public synchronized void closeDatabase(){
        if(openCounter.decrementAndGet()==0){
            //Cierro la base de datos
            sqLiteDatabase.close();
        }
    }
}
