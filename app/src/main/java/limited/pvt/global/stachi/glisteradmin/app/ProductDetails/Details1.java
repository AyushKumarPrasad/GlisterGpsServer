package limited.pvt.global.stachi.glisteradmin.app.ProductDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import limited.pvt.global.stachi.glisteradmin.app.MeetingDetails;
import limited.pvt.global.stachi.glisteradmin.app.Model.Common;
import limited.pvt.global.stachi.glisteradmin.app.Model.ImageAdapterMeetingDetails;
import limited.pvt.global.stachi.glisteradmin.app.Model.MeetingDatabase;
import limited.pvt.global.stachi.glisteradmin.app.Model.ProfileData;
import limited.pvt.global.stachi.glisteradmin.app.Notification.APIService;
import limited.pvt.global.stachi.glisteradmin.app.ProductViewholder.DetailsHolder1;
import limited.pvt.global.stachi.glisteradmin.app.Products.ProductDatabase1;
import limited.pvt.global.stachi.glisteradmin.app.ProfileViewHolder;
import limited.pvt.global.stachi.glisteradmin.app.R;

public class Details1 extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private DetailsHolder1 mAdapter;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef ;
    private List<ProductDatabase1> mUploads ;

    private String phone , date ;

    private TextView childrenCount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details1);

        mRecyclerView = findViewById(R.id.recycler_view_meetingDetails1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        childrenCount = (TextView) findViewById(R.id.text_view_name_meetingChildrenCountDatabase1);
        mProgressCircle = findViewById(R.id.progress_circle_meetingDetails1);

        mUploads = new ArrayList<>();

        if (getIntent() != null)
        {
            phone = getIntent().getStringExtra("nameDate");
            date = getIntent().getStringExtra("nameDate1");
        }

        String ayush = Common.currentUser.getPhone();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ProductDetails1").child(phone);
        mDatabaseRef.orderByChild("userDate1").equalTo(date).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    double count =  dataSnapshot.getChildrenCount();
                    String counts = String.valueOf(count);

                    ProductDatabase1 upload = postSnapshot.getValue(ProductDatabase1.class);
                    mUploads.add(upload);

                    //           Toast.makeText(MeetingDetails.this , "Count :" + counts , Toast.LENGTH_SHORT).show();

                    childrenCount.setText(counts + " time sold");
                }

                mAdapter = new DetailsHolder1(Details1.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                mDatabaseRef.keepSynced(true);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(Details1.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
