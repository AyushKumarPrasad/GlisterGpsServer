package limited.pvt.global.stachi.glisteradmin.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import limited.pvt.global.stachi.glisteradmin.app.Model.Common;
import limited.pvt.global.stachi.glisteradmin.app.Model.ImageAdapter;
import limited.pvt.global.stachi.glisteradmin.app.Model.ImageAdapterMeetingDetails;
import limited.pvt.global.stachi.glisteradmin.app.Model.MeetingDatabase;
import limited.pvt.global.stachi.glisteradmin.app.Model.Upload;

public class MeetingDetails extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private ImageAdapterMeetingDetails mAdapter;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef ;
    private List<MeetingDatabase> mUploads ;

    private String phone , date , name;

    private TextView childrenCount ;

    private TextView UserName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);

        mRecyclerView = findViewById(R.id.recycler_view_meetingDetails);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        childrenCount = (TextView) findViewById(R.id.text_view_name_meetingChildrenCountDatabase);
        mProgressCircle = findViewById(R.id.progress_circle_meetingDetails);

        UserName = (TextView) findViewById(R.id.datesName);

        mUploads = new ArrayList<>();

        if (getIntent() != null)
        {
            phone = getIntent().getStringExtra("nameDate");
            date = getIntent().getStringExtra("nameDate1");
            name = getIntent().getStringExtra("userName");
        }

        UserName.setText(name);

        String ayush = Common.currentUser.getPhone();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("MeetingDetails").child(phone);
        mDatabaseRef.orderByChild("meetingDate").equalTo(date).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    double count =  dataSnapshot.getChildrenCount();
                    String counts = String.valueOf(count);

                    MeetingDatabase upload = postSnapshot.getValue(MeetingDatabase.class);
                    mUploads.add(upload);

         //           Toast.makeText(MeetingDetails.this , "Count :" + counts , Toast.LENGTH_SHORT).show();

                    childrenCount.setText("Meetings Done Today = " + counts);
                }

                mAdapter = new ImageAdapterMeetingDetails(MeetingDetails.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                mDatabaseRef.keepSynced(true);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(MeetingDetails.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}