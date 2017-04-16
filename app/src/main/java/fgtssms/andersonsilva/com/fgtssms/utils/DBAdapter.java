package fgtssms.andersonsilva.com.fgtssms.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOME = "nome";
    public static final String KEY_NUMERO_CONTA = "numeroconta";
    public static final String KEY_MENSAGEM_INICIAL = "mensageminicial";
    private static final String TAG = "DBAdapter";
    
    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE_CONTAS = "contas";
    private static final String DATABASE_TABLE_GERAL = "geral";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE1 =
        "create table contas (_id integer primary key autoincrement, "
        + "nome text not null, numeroconta text not null );";
    private static final String DATABASE_CREATE2 =  " create table geral (_id integer primary key autoincrement, "
        +" mensageminicial text not null);";
        
    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
        	try {
        		db.execSQL(DATABASE_CREATE1);
                db.execSQL(DATABASE_CREATE2);
            } catch (SQLException e) {
        		e.printStackTrace();
        	}
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contas");
            onCreate(db);
        }
    }    

    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a contact into the database---
    public long insertConta(String nome, String numeroConta, String senha){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NOME, nome);
        initialValues.put(KEY_NUMERO_CONTA, numeroConta);
         return db.insert(DATABASE_TABLE_CONTAS, null, initialValues);
    }




    //---deletes a particular contact---
    public boolean deleteConta(long rowId)
    {
        return db.delete(DATABASE_TABLE_CONTAS, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllContas()
    {
        return db.query(DATABASE_TABLE_CONTAS, new String[] {KEY_ROWID, KEY_NOME,
                KEY_NUMERO_CONTA,KEY_NOME   }, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getConta(long numeroconta) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE_CONTAS, new String[] {KEY_ROWID,
                KEY_NOME, KEY_NUMERO_CONTA}, KEY_NUMERO_CONTA + "='" + numeroconta+"'", null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }




    /**
     *
     * @param rowId

     * @return
     */
    public boolean updateConta(long rowId, String nome, String numeroconta){
        ContentValues args = new ContentValues();
        args.put(KEY_NOME, nome);
        args.put(KEY_NUMERO_CONTA, numeroconta);
        return db.update(DATABASE_TABLE_CONTAS, args, KEY_ROWID + "=" + rowId, null) > 0;
    }


}
