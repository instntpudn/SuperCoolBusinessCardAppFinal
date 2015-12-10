package sjcnymobile.supercoolbusinesscardapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by ryan on 12/9/2015.
 */
public class FileUtil {

    public static void saveFile(String fileName, Bitmap b) {
        String root = Environment.getExternalStorageDirectory().toString();
        File dir = new File(root + "/supercoolbusinesscardapp/photos");
        dir.mkdirs();
        File savedFile = new File(dir, fileName);
        if(savedFile.exists()){savedFile.delete();}
        try {
            FileOutputStream out = new FileOutputStream(savedFile);
            b.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch(Exception e) {
            Log.v("SAVE FAILED", e.getMessage());
        }
    }

    public static Bitmap loadFile(String fileName) {
        String root = Environment.getExternalStorageDirectory().toString();
        File dir = new File(root + "/supercoolbusinesscardapp/photos");
        File savedFile = new File(dir, fileName);
        FileInputStream in = null;
        try {
            in = new FileInputStream(savedFile);
        } catch(Exception e) {
            Log.v("LOAD FAILED", e.getMessage());
        }
        Bitmap b = BitmapFactory.decodeStream(in);
        return b;
    }
}
