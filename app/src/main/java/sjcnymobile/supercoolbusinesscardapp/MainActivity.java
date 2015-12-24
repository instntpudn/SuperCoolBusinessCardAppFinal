package sjcnymobile.supercoolbusinesscardapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import sjcnymobile.supercoolbusinesscardapp.CarouselAdapter.OnItemClickListener;
import sjcnymobile.supercoolbusinesscardapp.CarouselAdapter.OnItemSelectedListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Carousel carousel = (Carousel)findViewById(R.id.carousel);
        TypedArray images = getResources().obtainTypedArray(R.array.entries);

        CarouselImageAdapter imageAdapter=new CarouselImageAdapter(this, images);
        carousel.setCarouselAdapter(imageAdapter);

        carousel.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(CarouselAdapter<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(MainActivity.this,
                        String.format("%s has been clicked",
                                ((CarouselItem) parent.getChildAt(position)).getName()),
                        Toast.LENGTH_SHORT).show();
            }

        });

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

                                                   // final TextView txt = (TextView)(findViewById(R.id.selected_item));

                                                   switch(position){
                                                       case 0:

                                                           break;
                                                       case 1:

                                                           break;
                                                       case 2:

                                                           break;
                                                       case 3:

                                                           break;
                                                       case 4:

                                                           break;
                                                       case 5:

                                                           break;
                                                   }

                                               }

                                               public void onNothingSelected(CarouselAdapter<?> parent) {
                                               }

                                           }
        );

    }

}

