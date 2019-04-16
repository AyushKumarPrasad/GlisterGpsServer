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

public class BatteryName extends AppCompatActivity
{
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<User, BatteryNameViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference requests   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_name);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("User");

        recyclerView = (RecyclerView) findViewById(R.id.retrieve_profile_getNames_batteryName);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders();
    }

    private void loadOrders()
    {
        adapter = new FirebaseRecyclerAdapter<User, BatteryNameViewHolder>
                (
                        User.class,
                        R.layout.battery_names,
                        BatteryNameViewHolder.class,
                        requests
                )
        {
            @Override
            protected void populateViewHolder( BatteryNameViewHolder viewHolder, final User model, int position)
            {
                viewHolder.txtleaveNames.setText(model.getName());

                viewHolder.itemClickListener = new ItemClickListener()
                {
                    @Override
                    public void onClick(View view, int position)
                    {
                        if (! model.getName().equals(Common.currentUser.getName()))
                        {
                            Intent map = new Intent(BatteryName.this, BatteryLevelPercent.class);
                            map.putExtra("userName", model.getName());
                            startActivity(map);
                        }
                        else
                        {
                            Toast.makeText(BatteryName.this,"You are admin my friend " , Toast.LENGTH_SHORT).show();
                        }
                    }
                };
            }
        };
        recyclerView.setAdapter(adapter);
        requests.keepSynced(true);
    }
}