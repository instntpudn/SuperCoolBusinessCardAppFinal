package sjcnymobile.supercoolbusinesscardapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

import sjcnymobile.supercoolbusinesscardapp.DataObjects.Bizcard;
import sjcnymobile.supercoolbusinesscardapp.managers.DatabaseManager;

public class Contacts extends AppCompatActivity {
    private String id = null;
    private String path = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Intent intent = getIntent();

        //if it was started witha  path to an image
        if(intent.hasExtra("path"))
        {
            path = intent.getStringExtra("path");//extract path
            populateFromImage(path);
        }
        else if(intent.hasExtra("id"))//if it was started with an id
        {
            id = intent.getStringExtra("id");//extract id
            populateFromDb(id);
        }

        Button button = (Button)findViewById(R.id.submit);
        final Contacts thisInstance = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent intent = new Intent(thisInstance, MainActivity.class);
                startActivity(intent);
            }
        });
//        output();//For testing
    }

    /***
     * For testing, remove when done, outputs all bizcards
     */
    private void output()
    {
        ArrayList<Bizcard> bizcards = DatabaseManager.getInstance().getAllBizCards();
        for(Bizcard bizcard: bizcards)
        {
            Log.v("Bizcard", bizcard.toString());
        }
    }

    /***
     * Given the id of the bizcard it populates the view from the database
     * @param id of the bizcard
     */
    private void populateFromDb(String id)
    {
        Bizcard bizcard = DatabaseManager.getInstance().getBizCard(id);
        populateFromBizcard(bizcard);
    }

    /***
     * Given the path to the image it sets the image, and if ocr is on it performs ocr
     * @param path is the path to the image
     */
    private void populateFromImage(String path)
    {
        ImageView imageView = (ImageView)findViewById(R.id.bcimage);
        imageView.setImageBitmap(FileUtil.loadFile(path));
        //set image
        if(true)//ocr on
        {
            //perform ocr
//            Bizcard bizcard = getBizcardFromOcr();//method does not exist, placeholder
//            populateFromBizcard(bizcard);
        }
    }

    /***
     * Gets the views to populate, checks if the bizcards field is null, if not it sets the field
     * @param bizcard is the bizcard to populate from
     */
    private void populateFromBizcard(Bizcard bizcard)
    {
        ImageView imageView = (ImageView)findViewById(R.id.bcimage);
        EditText busName = (EditText)findViewById(R.id.businessname);
        EditText name = (EditText)findViewById(R.id.Name);
        EditText address = (EditText)findViewById(R.id.address);
        EditText phone = (EditText)findViewById(R.id.phone);
        EditText description = (EditText)findViewById(R.id.description);
        EditText email = (EditText)findViewById(R.id.email);
        EditText website = (EditText)findViewById(R.id.website);

        if(bizcard.getImageName()!=null)
        {
            imageView.setImageBitmap(FileUtil.loadFile(bizcard.getImageName()));
            path = bizcard.getImageName();
        }
        if(bizcard.getBusinessName()!=null)
            busName.setText(bizcard.getBusinessName());
        if(bizcard.getName()!=null)
            name.setText(bizcard.getName());
        if(bizcard.getAddress()!=null)
            address.setText(bizcard.getAddress());
        if(bizcard.getPhone()!=null)
            phone.setText(bizcard.getPhone());
        if(bizcard.getDescription()!=null)
            description.setText(bizcard.getDescription());
        if(bizcard.getEmail()!=null)
            email.setText(bizcard.getEmail());
        if(bizcard.getWebsite()!=null)
            website.setText(bizcard.getWebsite());
    }

    /***
     * gets the views, gets there text and updates or inserts a record in the db
     * it updates if this intent was started with id, otherwise insert
     */
    private void save()
    {
        EditText busName = (EditText)findViewById(R.id.businessname);
        EditText name = (EditText)findViewById(R.id.Name);
        EditText address = (EditText)findViewById(R.id.address);
        EditText phone = (EditText)findViewById(R.id.phone);
        EditText description = (EditText)findViewById(R.id.description);
        EditText email = (EditText)findViewById(R.id.email);
        EditText website = (EditText)findViewById(R.id.website);

        if(id!=null)
        {
            DatabaseManager.getInstance().updateBizcard(id, name.getText().toString(), busName.getText().toString(), address.getText().toString(),phone.getText().toString(), description.getText().toString(), email.getText().toString(), website.getText().toString(), path);
        }
        else
        {
            DatabaseManager.getInstance().insertBizcard(name.getText().toString(), busName.getText().toString(), address.getText().toString(),phone.getText().toString(), description.getText().toString(), email.getText().toString(), website.getText().toString(), path);
        }
    }
}
