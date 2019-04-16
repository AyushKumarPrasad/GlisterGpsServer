package limited.pvt.global.stachi.glisteradmin.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

import limited.pvt.global.stachi.glisteradmin.app.Model.Common;
import limited.pvt.global.stachi.glisteradmin.app.Model.LeaveDatabase;
import limited.pvt.global.stachi.glisteradmin.app.Model.MeetingDatabase;
import limited.pvt.global.stachi.glisteradmin.app.Model.ProfileData;
import limited.pvt.global.stachi.glisteradmin.app.Notification.APIService;

public class LeaveDetails extends AppCompatActivity
{
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<LeaveDatabase , LeaveDetailsViewHolder > adapter;
    FirebaseDatabase database;
    DatabaseReference requests;
    private String phone ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_details);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Leave");

        recyclerView = (RecyclerView) findViewById(R.id.retrieve_profile_recyclerView_leaveDetails);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (getIntent() != null)
        {
            phone = getIntent().getStringExtra("name");
        }

        loadOrders(phone);
        //   loadOrders(Common.currentUser.getPhone());
    }

    private void loadOrders(String name)
    {
        adapter = new FirebaseRecyclerAdapter<LeaveDatabase , LeaveDetailsViewHolder >
                (
                        LeaveDatabase.class,
                        R.layout.retrieve_profile_layout_leavedetails,
                        LeaveDetailsViewHolder.class,
                        requests.orderByChild("leaveName").equalTo(name)
                )
        {
            @Override
            protected void populateViewHolder(LeaveDetailsViewHolder viewHolder, LeaveDatabase model, final int position)
            {
                //   final String status = Common.convertCodeToStatus(model.getAttendanceStatus());

                viewHolder.leaveDate.setText(model.getLeaveDate());
                viewHolder.leaveTime.setText(model.getLeaveTime());
                viewHolder.leaveMessage.setText(model.getLeaveMessage());
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        requests.keepSynced(true);
    }
}
