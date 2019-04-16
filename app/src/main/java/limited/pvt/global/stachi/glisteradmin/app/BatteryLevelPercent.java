package limited.pvt.global.stachi.glisteradmin.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BatteryLevelPercent extends AppCompatActivity
{
    TextView batteryName1 , batteryLevel1 , batteryInfo1 ;
    FirebaseDatabase database;
    DatabaseReference requests   ;
    String phone ;
    private ProgressBar mProgressBar;

    private int mProgressStatus = 0;
    float percentage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_level_percent);

        if (getIntent() != null)
        {
            phone = getIntent().getStringExtra("userName");
        }

   //     Toast.makeText(BatteryLevelPercent.this , "hello" + phone , Toast.LENGTH_SHORT ).show();

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Battery").child(phone);

        batteryLevel1 = (TextView) findViewById(R.id.tv_percentage);
        batteryName1 = (TextView) findViewById(R.id.batteryName);
        batteryInfo1 = (TextView) findViewById(R.id.tv_info);
        mProgressBar = (ProgressBar) findViewById(R.id.pb);

        requests.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String name = dataSnapshot.child("batteryName").getValue().toString();
                String batteryLevel = dataSnapshot.child("batteryLevel").getValue().toString();
                String batteryPercent = dataSnapshot.child("batteryPercentage").getValue().toString();
                String batteryScale = dataSnapshot.child("batteryScale").getValue().toString();

                batteryName1.setText(name);

                batteryInfo1.setText("Battery Scale : " + batteryScale);
                batteryInfo1.setText(batteryInfo1.getText() + "\nBattery Level : " + batteryLevel);

                int batscale = Integer.parseInt(batteryScale);
                int batlevel = Integer.parseInt(batteryLevel);

                percentage = batlevel / (float) batscale ;
                mProgressStatus = (int)((percentage)*100);

                batteryLevel1.setText("" + mProgressStatus + "%");
                batteryInfo1.setText(batteryInfo1.getText() + "\nPercentage : " + mProgressStatus + "%");
                mProgressBar.setProgress(mProgressStatus);

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }
}
