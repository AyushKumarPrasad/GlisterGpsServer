package limited.pvt.global.stachi.glisteradmin.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import limited.pvt.global.stachi.glisteradmin.app.Model.Common;
import limited.pvt.global.stachi.glisteradmin.app.Model.ImageAdapterMeetingDetails;
import limited.pvt.global.stachi.glisteradmin.app.Model.MeetingDatabase;
import limited.pvt.global.stachi.glisteradmin.app.Model.ProfileImageAdapter;
import limited.pvt.global.stachi.glisteradmin.app.Model.User;

public class ProfileDetails extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private ProfileImageAdapter mAdapter;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef ;
    private List<User> mUploads ;

    private String phone , name;

    private TextView childrenCount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        mRecyclerView = findViewById(R.id.recycler_view_meetingDetails);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        childrenCount = (TextView) findViewById(R.id.text_view_name_meetingChildrenCountDatabase);
        mProgressCircle = findViewById(R.id.progress_circle_meetingDetails);

        mUploads = new ArrayList<>();

        if (getIntent() != null)
        {
            phone = getIntent().getStringExtra("phone");
            name = getIntent().getStringExtra("name");
        }

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");
        mDatabaseRef.orderByChild("name").equalTo(name).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    double count =  dataSnapshot.getChildrenCount();
                    String counts = String.valueOf(count);

                    User upload = postSnapshot.getValue(User.class);
                    mUploads.add(upload);
                }

                mAdapter = new ProfileImageAdapter(ProfileDetails.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                mDatabaseRef.keepSynced(true);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(ProfileDetails.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}