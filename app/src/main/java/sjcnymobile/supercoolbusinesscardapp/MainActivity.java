package sjcnymobile.supercoolbusinesscardapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import sjcnymobile.supercoolbusinesscardapp.CarouselAdapter.OnItemClickListener;
import sjcnymobile.supercoolbusinesscardapp.CarouselAdapter.OnItemSelectedListener;
import sjcnymobile.supercoolbusinesscardapp.DataObjects.Bizcard;
import sjcnymobile.supercoolbusinesscardapp.managers.DatabaseManager;

public class MainActivity extends Activity {
    ArrayList<Bizcard> bizcards;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Carousel carousel = (Carousel)findViewById(R.id.carousel);
//        TypedArray images = getResources().obtainTypedArray(R.array.entries);
        bizcards = DatabaseManager.getInstance().getAllBizCards();
        CarouselImageAdapter imageAdapter=new CarouselImageAdapter(this, bizcards);
        carousel.setCarouselAdapter(imageAdapter);

//        carousel.setOnItemClickListener(new OnItemClickListener() {
//
//            public void onItemClick(CarouselAdapter<?> parent, View view,
//                                    int position, long id) {
//
//                Toast.makeText(MainActivity.this,
//                        String.format("%s has been clicked",
//                                ((CarouselItem) parent.getChildAt(position)).getName()),
//                        Toast.LENGTH_SHORT).show();
//            }
//
//        });

        Button button = (Button)findViewById(R.id.button);

        final MainActivity thisInstance = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisInstance, CameraActivity.class);
                startActivity(intent);
            }
        });
        carousel.setOnItemSelectedListener(new OnItemSelectedListener(){

                                               public void onItemSelected(CarouselAdapter<?> parent, View view,
                                                                          int position, long id) {
                                                   Intent intent = new Intent(MainActivity.this, Contacts.class);
                                                   String bid = bizcards.get(position).getId();
                                                   intent.putExtra("id",bid);
                                                   startActivity(intent);
                                               }

                                               public void onNothingSelected(CarouselAdapter<?> parent) {
                                               }

                                           }
        );

    }

}

