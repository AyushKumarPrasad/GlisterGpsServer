package limited.pvt.global.stachi.glisteradmin.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import limited.pvt.global.stachi.glisteradmin.app.Model.Common;
import limited.pvt.global.stachi.glisteradmin.app.Notification.Token;

public class Home extends AppCompatActivity
{
    Button enter , gps , meetingDetails , leaveDetails , batteryLife , productsList , profile ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gps = (Button) findViewById(R.id.gpsUser);
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , GetLocation.class);
                startActivity(intent);
            }
        });

        enter = (Button) findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , GetNames.class);
                startActivity(intent);
            }
        });

        meetingDetails = (Button) findViewById(R.id.gpsMeetingDetails);
        meetingDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , MeetingNames.class);
                startActivity(intent);
            }
        });

        leaveDetails = (Button) findViewById(R.id.leaveDetails);
        leaveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , LeaveNames.class);
                startActivity(intent);
            }
        });

        batteryLife = (Button) findViewById(R.id.batteryLife);
        batteryLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , BatteryName.class);
                startActivity(intent);
            }
        });

        productsList = (Button) findViewById(R.id.productsList);
        productsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , ProductItems.class);
                startActivity(intent);
            }
        });

        profile = (Button) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this , ProfileNames.class);
                startActivity(intent);
            }
        });

        updateToken(FirebaseInstanceId.getInstance().getToken());
    }

    private void updateToken(String token)
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference tokens = db.getReference("Tokens");

        Token data = new Token (token, true  , "admin");
        tokens.child(Common.currentUser.getPhone()).setValue(data);
    }

    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setTitle("Close Glister")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        Home.super.onBackPressed();
                    }
                }).create().show();
    }
}
