package practice.hbk.com.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD OPERATIONS
         */
        //inserting contacts
        Log.d("Insert: ", "Inserting...");
        db.addContacts(new Contacts("HBK", "9100000000"));
        db.addContacts(new Contacts("Jay", "9199999999"));
        db.addContacts(new Contacts("Ray", "9522222222"));
        db.addContacts(new Contacts("Lay", "9533333333"));

        //Inserting Music
        Log.d("Insert:", "Inserting...");
        db.addMusic(new Music("Rock", "5961000"));
        db.addMusic(new Music("Hip-Hop", "4767110"));
        db.addMusic(new Music("Pop", "2987155"));
        db.addMusic(new Music("Reggae", "675889"));

        //reading all contacts
        Log.d("Reading: ", "Reading all contacts...");
        List<Contacts> contacts = db.getAllContacts();

        for (Contacts cn : contacts) {
            String log = "Id: "+cn.getId()+" ,Name: "+cn.getName() +
                    " ,Phone: " +cn.getPhoneNumber();
            //writing contacts to log
            Log.d("Name: ", log);
        }

        //reading all music
        Log.d("Reading: ", "Reading all music...");
        List<Music> music = db.getAllMusic();

        for (Music cn : music) {
            String log = "Id: "+cn.getMusicId()+" ,Genre: "+cn.get_genre() +
                    " ,Phone: " +cn.get_downloads();
            //writing contacts to log
            Log.d("Genre: ", log);
        }

    }


    /** @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
    }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
     // Handle action bar item clicks here. The action bar will
     // automatically handle clicks on the Home/Up button, so long
     // as you specify a parent activity in AndroidManifest.xml.
     int id = item.getItemId();

     //noinspection SimplifiableIfStatement
     if (id == R.id.action_settings) {
     return true;
     }

     return super.onOptionsItemSelected(item);
     }

     public class AndroidSQLiteTutorialActivity extends Activity {

     @Override
     public void onCreate (Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);

     }
     } **/
}