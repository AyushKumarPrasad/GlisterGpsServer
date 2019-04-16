package limited.pvt.global.stachi.glisteradmin.app;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import limited.pvt.global.stachi.glisteradmin.app.Model.Common;
import limited.pvt.global.stachi.glisteradmin.app.Model.ProfileData;
import limited.pvt.global.stachi.glisteradmin.app.Notification.APIService;
import limited.pvt.global.stachi.glisteradmin.app.Notification.MyResponse;
import limited.pvt.global.stachi.glisteradmin.app.Notification.Notification;
import limited.pvt.global.stachi.glisteradmin.app.Notification.Sender;
import limited.pvt.global.stachi.glisteradmin.app.Notification.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAttendance extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<ProfileData,ProfileViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference requests;
    private String phone ;

    APIService mService ;
    MaterialSpinner spinner ;
    FirebaseDatabase db ;

    TextView userName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_attendance);

        userName = (TextView) findViewById(R.id.attendanceName);

        mService = Common.getFCMClient() ;

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Attendance");

        recyclerView = (RecyclerView) findViewById(R.id.retrieve_profile_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (getIntent() != null)
        {
            phone = getIntent().getStringExtra("name");
        }

        userName.setText(phone);

        loadOrders(phone);
    }

    private void loadOrders(String name)
    {
        adapter = new FirebaseRecyclerAdapter<ProfileData, ProfileViewHolder>
                (
                        ProfileData.class,
                        R.layout.retrieve_profile_layout,
                        ProfileViewHolder.class,
                        requests.orderByChild("attendanceName").equalTo(name)
                )
        {
            @Override
            protected void populateViewHolder(final ProfileViewHolder viewHolder, final ProfileData model, final int position)
            {
                final String status = Common.convertCodeToStatus(model.getAttendanceStatus());

         //     viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
                viewHolder.txtPhone.setText(model.getAttendanceDate());
                viewHolder.txtTime.setText(model.getAttendanceTime());
                viewHolder.txtStatus.setText(Common.convertCodeToStatus(model.getAttendanceStatus()) );
                viewHolder.txtLocation.setText(model.getAttendanceAddress());
                viewHolder.txtArea.setText(model.getAttendanceTargetArea());

                viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        String status = adapter.getItem(position).getAttendanceDate();

                        showUpdateDialog(adapter.getRef(position).getKey(), adapter.getItem(position) , status);
                    }
                });

                viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteOrder(adapter.getRef(position).getKey());
                    }
                });

                if (viewHolder.txtStatus.getText().toString() == "Present")
                {
                    viewHolder.txtStatus.setBackgroundColor(Color.RED);
                }

                if (adapter != null)
                {
                    recyclerView.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
                        }
                    }, 500);
                }

            }
        };

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        requests.keepSynced(true);
    }

    private void deleteOrder(String key)
    {
        requests.child(key).removeValue();
        adapter.notifyDataSetChanged();
    }

    private void showUpdateDialog(String key, final ProfileData item , final String status)
    {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(GetAttendance.this);
        alertDialog.setTitle("Update Order");
        alertDialog.setMessage("Please Choose Status");

        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.update_order_layout, null);

        spinner = (MaterialSpinner) view.findViewById(R.id.statusSpinner);
        spinner.setItems("Placed" , "Present" ,"Half-Day", "Absent");

        alertDialog.setView(view);

        final String localKey = key ;
        final String statuskey = status ;
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                item.setAttendanceStatus(String.valueOf(spinner.getSelectedIndex()));

                requests.child(localKey).setValue(item);
                adapter.notifyDataSetChanged();

                sendOrderStatusToUser(localKey, item , statuskey);
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void sendOrderStatusToUser(final String key , final ProfileData item , final String status)
    {
        DatabaseReference tokens = database.getReference("Tokens");
        tokens.orderByKey().equalTo(item.getAttendancePhone())
                .addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                        {
                            Token token = postSnapshot.getValue(Token.class);

                            Notification notification = new Notification(phone, "" + "Attendance" /* + key */ +
                                    " updated for " + status);
                            Sender content = new Sender(token.getToken(),notification);

                            mService.sendNotification(content)
                                    .enqueue(new Callback<MyResponse>()
                                    {
                                        @Override
                                        public void onResponse(Call<MyResponse> call, Response<MyResponse> response)
                                        {
                                            if (response.body().success == 1)
                                            {
                                                Toast.makeText(GetAttendance.this,"Attendance was updated" ,
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                Toast.makeText(GetAttendance.this,"Attendance was updated but " +
                                                                "notification failed" ,
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<MyResponse> call, Throwable t)
                                        {
                                            Log.e("ERROR" , t.getMessage());
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {

                    }
                });
    }
}
