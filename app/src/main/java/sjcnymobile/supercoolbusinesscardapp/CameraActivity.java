package sjcnymobile.supercoolbusinesscardapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by ryan on 12/9/2015.
 */
public class CameraActivity extends Activity {
    private static final int CAMERA_REQUEST = 1111;
    private Button acceptPictureButton;
    private Button rejectPictureButton;
    private ImageView pictureImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);

        pictureImage = (ImageView) findViewById(R.id.imageView);
        acceptPictureButton = (Button) findViewById(R.id.accept);
        final CameraActivity thisInstance = this;
        acceptPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String root = Environment.getExternalStorageDirectory().toString();
                String path = root + "/supercoolbusinesscardapp/photos";
                Intent intent = new Intent(thisInstance, Contacts.class);
                intent.putExtra("path", path);
                startActivity(intent);
            }
        });

        rejectPictureButton = (Button) findViewById(R.id.cancel);
        rejectPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST) {
            Bitmap b = (Bitmap) data.getExtras().get("data");
            saveFile("savedimage.png", b);
            pictureImage.setImageBitmap(loadFile("savedImage.png"));
        }
    }

    public void saveFile(String fileName, Bitmap b) {
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

    public Bitmap loadFile(String fileName) {
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
