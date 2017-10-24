package practice.hbk.com.sqlite;

/**
 * Created by H.B.K on 24-Oct-17.
 */

public class Music {
    int _music_id;
    String _genre;
    String _downloads;

    //empty constructor
    public Music(){

    }

    //constructor
    public Music(int MusicId, String genre, String _downloads) {

        this._music_id = MusicId;
        this._genre = genre;
        this._downloads = _downloads;
    }

    public Music(String _genre, String _downloads){
        this._genre = _genre;
        this._downloads = _downloads;
    }

    //getting Music Id
    public int getMusicId() {
        return this._music_id;
    }

    //setting Music Id
    public void setMusicId(int MusicId){
        this._music_id = MusicId;
    }

    //getting genre


    public String get_genre() {
        return _genre;
    }

    //setting genre
    public void setGenre(String genre){
        this._genre = genre;
    }

    //getting downloads


    public String get_downloads() {
        return _downloads;
    }

    //setting downloads
    public void setDownloads(String downloads){
        this._downloads = downloads;
    }


}