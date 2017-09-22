package com.bertelli.pocketls.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ExternalDbOpenHelper extends SQLiteOpenHelper {


	public static String DB_PATH;

	public static String DB_NAME;
	public SQLiteDatabase database;
	public final Context context;
	public static final int DATABASE_VERSION = 5;
	
	public SQLiteDatabase getDb() {
		return database;
	}

	public ExternalDbOpenHelper(Context context, String databaseName) {
		super(context, databaseName, null, DATABASE_VERSION);
		this.context = context;

		String packageName = context.getPackageName();
		DB_PATH = String.format("//data//data//%s//databases//", packageName);
		DB_NAME = databaseName;
		openDataBase();
	}


	public void createDataBase() {
		boolean dbExist = checkDataBase();
		if (!dbExist) {
			this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				Log.e(this.getClass().toString(), "Erro ao copiar");
				throw new Error("Erro ao copiar database!");
			}
		} else {
			Log.i(this.getClass().toString(), "Database já existe");
		}
	}

	private boolean checkDataBase() {
		SQLiteDatabase checkDb = null;
		try {
			String path = DB_PATH + DB_NAME;
			checkDb = SQLiteDatabase.openDatabase(path, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLException e) {
			Log.e(this.getClass().toString(), "Erro na verificação da base");
		}

		if (checkDb != null) {
			checkDb.close();
		}
		return checkDb != null;
	}

	public void copyDataBase() throws IOException {


		InputStream externalDbStream = context.getAssets().open(DB_NAME);


		String outFileName = DB_PATH + DB_NAME;


		OutputStream localDbStream = new FileOutputStream(outFileName);


		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = externalDbStream.read(buffer)) > 0) {
			localDbStream.write(buffer, 0, bytesRead);
		}

		localDbStream.close();
		externalDbStream.close();

	}

	public SQLiteDatabase openDataBase() throws SQLException {
		String path = DB_PATH + DB_NAME;
		if (database == null) {
			createDataBase();
			database = SQLiteDatabase.openDatabase(path, null,
				SQLiteDatabase.OPEN_READWRITE);
		}
		return database;
	}
	@Override
	public synchronized void close() {
		if (database != null) {
			database.close();
		}
		super.close();
	}
	/**
     * This method is called when the app doesn't have a database and a new one needs to be created
     */
    @Override
    public void onCreate(SQLiteDatabase arg0) {
        try {
            // Copy the db from assests
            copyDataBase();
            Log.e("OnCreate", "database created");
        } catch (IOException mIOException) {
            Log.e("OnCreate", mIOException.toString());
        }
   }

    /**
     * This method is called when the app's database version is < the value passed into the constructor
     */ 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //try {
            // delete existing?
    
            // Copy the db from assests
        	//Log.d("OnUpgrade", "Atualizando a base de dados");
        	//db.execSQL("ALTER TABLE musica RENAME TO temp_musica");
        	//Log.d("OnUpgrade", "Tabela musica foi copiada para backup");
            //copyDataBase();
            //Log.d("OnUpgrade", "Realizou update do DB");
            //Log.d("OnUpgrade", "Database original foi copiado");
            //db.execSQL("UPDATE musica SET fav = ( SELECT temp_musica.fav " +
            //		"FROM temp_musica WHERE musica._id = temp_musica._id AND musica.album_id = temp_musica.album_id) WHERE EXISTS" +
            //		"( SELECT temp_musica.fav FROM temp_musica WHERE musica._id = temp_musica._id  AND musica.album_id = temp_musica.album_id)");
            //Log.d("OnUpgrade", "Tabela musica foi atualizada com os favoritos antigos");
        //} catch (IOException mIOException) {
        //    Log.e("OnUpgrade", mIOException.toString());
       // }
    }

}

