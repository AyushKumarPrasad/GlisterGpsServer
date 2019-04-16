package limited.pvt.global.stachi.glisteradmin.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import limited.pvt.global.stachi.glisteradmin.app.Model.Common;
import limited.pvt.global.stachi.glisteradmin.app.Model.MeetingDatabase;
import limited.pvt.global.stachi.glisteradmin.app.Model.User;

public class MeetingDates extends AppCompatActivity
{
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<MeetingDatabase, MeetingDatesViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference requests   ;

    String phone ;

    TextView name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_dates);

        if (getIntent() != null)
        {
            phone = getIntent().getStringExtra("name");
        }

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("MeetingDetails").child(phone);

        recyclerView = (RecyclerView) findViewById(R.id.retrieve_profile_recyclerView_meetingDates);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        name = (TextView) findViewById(R.id.datesName);

        name.setText(phone);

        loadOrders();
    }

    private void loadOrders()
    {
        adapter = new FirebaseRecyclerAdapter<MeetingDatabase, MeetingDatesViewHolder>
                (
                        MeetingDatabase.class,
                        R.layout.retrieve_profile_layout_meeting_dates,
                        MeetingDatesViewHolder.class,
                        requests
                )
        {
            @Override
            protected void populateViewHolder( MeetingDatesViewHolder viewHolder, final MeetingDatabase model, int position)
            {
                viewHolder.txtDates.setText(model.getMeetingDate());

                viewHolder.itemClickListener = new ItemClickListener()
                {
                    @Override
                    public void onClick(View view, int position)
                    {
                        Intent map = new Intent(MeetingDates.this, MeetingDetails.class);
                        map.putExtra("userName" , model.getMeetingUserName());
                        map.putExtra("nameDate", model.getMeetingUserName());
                        map.putExtra("nameDate1", model.getMeetingDate());
                        startActivity(map);
                    }
                };




            }
        };
        recyclerView.setAdapter(adapter);
        requests.keepSynced(true);
    }
}