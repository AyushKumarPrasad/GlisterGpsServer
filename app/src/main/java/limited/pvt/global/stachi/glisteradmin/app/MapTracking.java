package limited.pvt.global.stachi.glisteradmin.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import limited.pvt.global.stachi.glisteradmin.app.Model.Gps1Data;

public class MapTracking extends AppCompatActivity
{
    private String email ;
    DatabaseReference locations ;
    Double lat , lng ;

    private TextView mTextLocation;

    Gps1Data tracking ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_tracking);

        mTextLocation = (TextView) findViewById(R.id.Location);
        mTextLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Double latitude  = Double.parseDouble(tracking.getLat());
                Double longitude  = Double.parseDouble(tracking.getLng());

                String lati = String.valueOf(latitude);
                String longi = String.valueOf(longitude);

                mTextLocation.setText(lati + "AYUSH" + longi);

                Intent mapi = new Intent(MapTracking.this , LocationAddress.class);
                mapi.putExtra("lati" ,latitude);
                mapi.putExtra("longi" , longitude);
                startActivity(mapi);
            }
        });

        if (getIntent() != null)
        {
     //       email = getIntent().getStringExtra("email");
            lat = getIntent().getDoubleExtra("lat" , 0);
            lng = getIntent().getDoubleExtra("lng" , 0);
        }
    }
}
