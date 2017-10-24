package practice.hbk.com.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H.B.K on 19-Oct-17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static Variables
    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    public static final String DATABASE_NAME = "contactsManager";

    //Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Column Names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    // creating a new table
    private static final String TABLE_MUSIC = "Music";
    // Music table columns
    private static final String KEY_MUSIC_ID = "Music_id";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_DOWNLOADS = "downloads";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = " CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT, " + KEY_PH_NO + " TEXT " + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        String CREATE_MUSIC_TABLE = " CREATE TABLE " + TABLE_MUSIC + "(" + KEY_MUSIC_ID + " INTEGER PRIMARY KEY," + KEY_GENRE + " TEXT, " + KEY_DOWNLOADS + " TEXT " + ")";
        db.execSQL(CREATE_MUSIC_TABLE);
    }


    //Upgrading Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSIC);
        //Create Tables Again
        onCreate(db);
    }

    //Adding new contact
    public void addContacts(Contacts contacts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contacts.getName());//Contact Name
        values.put(KEY_PH_NO, contacts.getPhoneNumber());// Contact phone number

        //Inserting Rows
        db.insert(TABLE_CONTACTS, null, values);
        db.close();// Closing database connection
    }

    //Adding new Music
    public void addMusic(Music music) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GENRE, music.get_genre());//Music Genre
        values.put(KEY_DOWNLOADS, music.get_downloads());// Music downloads

        //Inserting Rows
        db.insert(TABLE_MUSIC, null, values);
        db.close();// Closing database connection
    }


    //Getting single contact
    public Contacts getContacts(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contacts contacts = new Contacts(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        //return contact
        return contacts;

    }

    //Getting single Music
    public Music getMusic(int music_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MUSIC, new String[]{KEY_MUSIC_ID, KEY_DOWNLOADS}, KEY_MUSIC_ID + "=?",
                new String[]{String.valueOf(music_id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Music music = new Music(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        //return music
        return music;

    }

    //Getting all contacts
    public List<Contacts> getAllContacts() {
        List<Contacts> contactsList = new ArrayList<Contacts>();
        //Select all query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding list
        if (cursor.moveToFirst()) {
            do {
                Contacts contacts = new Contacts();
                contacts.setId(Integer.parseInt(cursor.getString(0)));
                contacts.setName(cursor.getString(1));
                contacts.setPhoneNumber(cursor.getString(2));
                //Adding contacts to list
                contactsList.add(contacts);
            } while (cursor.moveToNext());
        }
        return contactsList;
    }
//Getting all music
        public List<Music> getAllMusic() {
            List<Music> musicList = new ArrayList<Music>();
            //Select all query
            String selectQuery = "SELECT * FROM " + TABLE_MUSIC;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            //looping through all rows and adding list
            if (cursor.moveToFirst()) {
                do {
                    Music music = new Music();
                    music.setMusicId(Integer.parseInt(cursor.getString(0)));
                    music.setGenre(cursor.getString(1));
                    music.setDownloads(cursor.getString(2));
                    //Adding contacts to list
                    musicList.add(music);
                } while (cursor.moveToNext());

            }
        // return music list
        return musicList;

    }


    //Getting Contacts count
    public  int getContactsCount() {
        String countQuery = "SELECT * FROM" + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }
    //Getting music count
    public  int getMusicCount() {
        String countQuery = "SELECT * FROM" + TABLE_MUSIC;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

    //Updating single contact
    public int updateContacts(Contacts contacts){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contacts.getName());
        values.put(KEY_PH_NO, contacts.getPhoneNumber());

        //updating row
        return  db.update(TABLE_CONTACTS, values, KEY_ID + "+?",
                new String[] { String.valueOf(contacts.getId()) });
    }
    //Updating single music
    public int updateMusic(Music music){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GENRE, music.get_genre());
        values.put(KEY_DOWNLOADS, music.get_downloads());

        //updating row
        return  db.update(TABLE_MUSIC, values, KEY_MUSIC_ID + "+?",
                new String[] { String.valueOf(music.getMusicId()) });
    }


    // Deleting single contact
    public void deleteContacts(Contacts contacts) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "= ?",
                new String[] {String.valueOf(contacts.getId()) });
        db.close();
    }


    // Deleting single music
    public void deleteMusic(Music music) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MUSIC, KEY_MUSIC_ID + "= ?",
                new String[] {String.valueOf(music.getMusicId()) });
        db.close();
    }

}
