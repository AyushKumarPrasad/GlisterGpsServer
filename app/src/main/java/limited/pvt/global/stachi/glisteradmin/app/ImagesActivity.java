package limited.pvt.global.stachi.glisteradmin.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import limited.pvt.global.stachi.glisteradmin.app.Model.Common;
import limited.pvt.global.stachi.glisteradmin.app.Model.ImageAdapter;
import limited.pvt.global.stachi.glisteradmin.app.Model.ProfileData;
import limited.pvt.global.stachi.glisteradmin.app.Model.Upload;
import limited.pvt.global.stachi.glisteradmin.app.Model.User;

public class ImagesActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef ;
    private List<Upload> mUploads ;

    private String phone ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

        if (getIntent() != null)
        {
            phone = getIntent().getStringExtra("name");
        }

        String ayush = Common.currentUser.getPhone();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Images").child(phone);
        mDatabaseRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }

                mAdapter = new ImageAdapter(ImagesActivity.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                mDatabaseRef.keepSynced(true);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
