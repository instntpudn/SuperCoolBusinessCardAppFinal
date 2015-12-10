package sjcnymobile.supercoolbusinesscardapp.managers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

import sjcnymobile.supercoolbusinesscardapp.DataObjects.Bizcard;

/**
 * Created by Thomas on 11/18/2015.
 */
public class DatabaseManager {
    private SQLiteDatabase database;

    private static DatabaseManager ourInstance = new DatabaseManager();

    public static DatabaseManager getInstance() {
        return ourInstance;
    }

    private DatabaseManager()
    {
        File directory = new File(Environment.getExternalStorageDirectory(),"bizcard/db/");
        if(!directory.exists())
        {
            if(!directory.mkdirs())
            {
                Log.e("CREATE ERROR", "Did not create directories properly");
//                System.exit(1);
            }
        }
        File dbFile = new File(directory, "bizcards.db");

        database = SQLiteDatabase.openOrCreateDatabase(dbFile,null);
        createTableIfNotExists();
    }


    public void createTableIfNotExists()
    {
        String cmd = "CREATE TABLE IF NOT EXISTS BIZCARDS (BIZCARDID VARCHAR(35), BIZCARDNAME VARCHAR(50), BIZCARDBIZNAME VARCHAR(50), BIZCARDADDRESS VARCHAR(50),BIZCARDPHONE VARCHAR(20), BIZCARDDESCRIPTION VARCHAR(100), BIZCARDEMAIL VARCHAR(100),BIZCARDWEBSITE VARCHAR(256), BIZCARDIMAGENAME VARCHAR(25), PRIMARY KEY (BIZCARDID));";
        database.execSQL(cmd);
    }

    public String insertBizcard(String name, String bizName, String address, String phone, String description,
                              String email, String website, String imageFileName)
    {
        String id = java.util.UUID.randomUUID().toString();
        String cmd = "INSERT INTO BIZCARDS " +
                "(BIZCARDID, BIZCARDNAME, BIZCARDBIZNAME, BIZCARDADDRESS,BIZCARDPHONE, BIZCARDDESCRIPTION, BIZCARDEMAIL, BIZCARDWEBSITE, BIZCARDIMAGENAME)" +
                "VALUES" +
                "('"+id+"','"+name+"','"+bizName+"','"+address+"','"+phone+"','"+description+"','"+email+"','"+website+"','"+imageFileName+"');";
        database.execSQL(cmd);
        return id;
    }

    public Bizcard getBizCard(String bizId)
    {
        String cmd  = "SELECT * FROM BIZCARDS WHERE BIZCARDID = '" +bizId+"';";
        Cursor resultCursor = database.rawQuery(cmd,null);
        Bizcard bizcard=null;
        if(resultCursor.moveToFirst())
        {
            String id = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDID"));
            String name = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDNAME"));
            String bizname = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDBIZNAME"));
            String address = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDPHONE"));
            String phone = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDPHONE"));
            String description = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDDESCRIPTION"));
            String email = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDEMAIL"));
            String website = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDWEBSITE"));
            String imageFile = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDIMAGENAME"));
            bizcard = new Bizcard(id,name,bizname,address,phone,description,email,website,imageFile);
        }
        return bizcard;
    }

    public ArrayList<Bizcard> getAllBizCards()
    {
        String cmd  = "SELECT * FROM BIZCARDS;";
        Cursor resultCursor = database.rawQuery(cmd,null);
        ArrayList<Bizcard> bizcards = new ArrayList<>();
        if(resultCursor.moveToFirst())
        {
            do {
                String id = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDID"));
                String name = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDNAME"));
                String bizname = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDBIZNAME"));
                String address = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDPHONE"));
                String phone = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDPHONE"));
                String description = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDDESCRIPTION"));
                String email = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDEMAIL"));
                String website = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDWEBSITE"));
                String imageFile = resultCursor.getString(resultCursor.getColumnIndex("BIZCARDIMAGENAME"));
                bizcards.add(new Bizcard(id,name,bizname,address,phone,description,email,website,imageFile));
            }while(resultCursor.moveToNext());
        }
        return bizcards;
    }

    public void deleteBizcard(String id)
    {
        String cmd = "DELETE FROM BIZCARDS WHERE BIZCARDID='"+id+"';";
        database.execSQL(cmd);
    }

    public void updateBizcard(String id, String name, String bizName, String address, String phone, String description,
                              String email, String website, String imageFileName)
    {
        String cmd = "UPDATE BIZCARDS SET BIZCARDNAME = '"+name+"', BIZCARDBIZNAME='"+bizName+"',BIZCARDADDRESS='"+address+
                "',BIZCARDPHONE='"+phone+"',BIZCARDDESCRIPTION='"+description+"',BIZCARDEMAIL='"+email+
                "', BIZCARDWEBSITE='"+website+"',BIZCARDIMAGENAME='"+imageFileName+"' WHERE BIZCARDID ='"+id+"';";
        database.execSQL(cmd);
    }
}