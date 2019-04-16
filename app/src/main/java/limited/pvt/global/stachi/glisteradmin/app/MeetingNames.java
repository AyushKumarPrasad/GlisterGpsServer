package limited.pvt.global.stachi.glisteradmin.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import limited.pvt.global.stachi.glisteradmin.app.Model.Common;
import limited.pvt.global.stachi.glisteradmin.app.Model.User;

public class MeetingNames extends AppCompatActivity
{
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<User, MeetingDatesTryNameViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference requests   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_names);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("User");

        recyclerView = (RecyclerView) findViewById(R.id.retrieve_profile_getNames);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders();
    }

    private void loadOrders()
    {
        adapter = new FirebaseRecyclerAdapter<User, MeetingDatesTryNameViewHolder>
                (
                        User.class,
                        R.layout.meetingnames,
                        MeetingDatesTryNameViewHolder.class,
                        requests
                )
        {
            @Override
            protected void populateViewHolder( MeetingDatesTryNameViewHolder viewHolder, final User model, int position)
            {
                viewHolder.txtNames.setText(model.getName());

                viewHolder.itemClickListener = new ItemClickListener()
                {
                    @Override
                    public void onClick(View view, int position)
                    {
                        if (! model.getName().equals(Common.currentUser.getName()))
                        {
                            Intent map = new Intent(MeetingNames.this, MeetingDates.class);
                            map.putExtra("name", model.getName());
                            startActivity(map);
                        }
                        else
                        {
                            Toast.makeText(MeetingNames.this,"You are admin my friend " , Toast.LENGTH_SHORT).show();
                        }
                    }
                };
            }
        };
        recyclerView.setAdapter(adapter);
        requests.keepSynced(true);
    }
}