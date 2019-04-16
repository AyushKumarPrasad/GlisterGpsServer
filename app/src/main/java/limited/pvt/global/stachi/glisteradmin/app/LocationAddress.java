package limited.pvt.global.stachi.glisteradmin.app;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Locale;

public class LocationAddress extends AppCompatActivity
{
    private TextView mTextLocation , mTextAddress , mName;
    String lat , lng , name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_address);

        if (getIntent() != null)
        {
            lat = getIntent().getStringExtra("lat" );
            lng = getIntent().getStringExtra("lng" );
            name = getIntent().getStringExtra("name");

            Toast.makeText(LocationAddress.this,"Location" + lat + lng , Toast.LENGTH_SHORT).show();
        }

        final Double lati = Double.parseDouble(lat);
        final Double longi = Double.parseDouble(lng);

        mTextLocation = (TextView) findViewById(R.id.text_location);
        mTextLocation.setText(lati + " KUMAR " + longi);

        mTextAddress = (TextView) findViewById(R.id.text_address);

        mName = (TextView) findViewById(R.id.locationUserName);

        mName.setText(name);

        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try
        {
            addresses = gcd.getFromLocation(lati,longi, 1);
            if (addresses != null && addresses.size() > 0)
            {
                // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                mTextAddress.setText(address + " " + city + " " + state + " " + country + " " + postalCode + " " + knownName);
            }
            else
                Toast.makeText(getApplicationContext(), "Address not found", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Address not found", Toast.LENGTH_LONG).show();
        }
    }
}
