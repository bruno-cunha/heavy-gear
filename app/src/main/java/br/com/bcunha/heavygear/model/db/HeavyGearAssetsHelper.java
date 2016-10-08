package br.com.bcunha.heavygear.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by BRUNO on 12/09/2016.
 */
public class HeavyGearAssetsHelper extends SQLiteAssetHelper {

    public static final String DB_NOME = "heavygear.db";
    public static final int    VERSAO  = 1;

    public static final String TABELA_ACOES  = "acoes";

    // Campos comuns
    public static final String CAMPO_ID      = "_id";
    public static final String CAMPO_CODIGO  = "codigo";

    // Campos tabela ACOES
    public static final String CAMPO_EMPRESA = "empresa";
    public static final String CAMPO_TIPO    = "tipo";

    SQLiteDatabase heavyGearDB;

    public HeavyGearAssetsHelper(Context context) {
        super(context, DB_NOME, null, VERSAO);
        //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);
    }

    public void openDB() {
        heavyGearDB = getWritableDatabase();
    }

    public void closeDB() {
        if (heavyGearDB !=  null && heavyGearDB.isOpen()) {
            heavyGearDB.close();
        }
    }

    public long insertAcoes(int id, String codigo, String empresa, String tipo){
        ContentValues contentValues = new ContentValues();
        if (id != -1) {
            contentValues.put(CAMPO_ID, id);
        }
        contentValues.put(CAMPO_CODIGO, codigo);
        contentValues.put(CAMPO_EMPRESA, empresa);
        contentValues.put(CAMPO_TIPO, tipo);

        return heavyGearDB.insert(TABELA_ACOES, null, contentValues);
    }

    public long updateAcoes(int id, String codigo, String empresa, String tipo){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAMPO_CODIGO, codigo);
        contentValues.put(CAMPO_EMPRESA, empresa);
        contentValues.put(CAMPO_TIPO, tipo);

        String where = CAMPO_ID + " = " + id;

        return heavyGearDB.update(TABELA_ACOES, contentValues, where, null);
    }

    public long deleteAcoes(int id){
        String where = CAMPO_ID + " = " + id;

        return heavyGearDB.delete(TABELA_ACOES, where, null);
    }

    public Cursor getAcoes(){
        String query = "SELECT * FROM " + TABELA_ACOES;

        return heavyGearDB.rawQuery(query, null);
    }

    public Cursor execQuery (String query) {
        return heavyGearDB.rawQuery(query, null);
    }
}
