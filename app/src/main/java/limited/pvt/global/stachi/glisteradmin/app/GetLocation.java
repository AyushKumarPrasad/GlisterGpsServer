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
import limited.pvt.global.stachi.glisteradmin.app.Model.Gps1Data;
import limited.pvt.global.stachi.glisteradmin.app.Model.User;

public class GetLocation extends AppCompatActivity
{
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Gps1Data, GpsViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference requests   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("PermanentGps");

        recyclerView = (RecyclerView) findViewById(R.id.retrieve_profile_getLocation);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders();
    }

    private void loadOrders()
    {
        adapter = new FirebaseRecyclerAdapter<Gps1Data,  GpsViewHolder>
                (
                        Gps1Data.class,
                        R.layout.get_gps_location,
                        GpsViewHolder.class,
                        requests
                )
        {
            @Override
            protected void populateViewHolder( GpsViewHolder viewHolder, final Gps1Data model, int position)
            {
                viewHolder.txtNames.setText(model.getName());
                viewHolder.itemClickListener = new ItemClickListener()
                {
                    @Override
                    public void onClick(View view, int position)
                    {
                        Intent mapi = new Intent(GetLocation.this , LocationAddress.class);
                        mapi.putExtra("name" , model.getName());
                        mapi.putExtra("lat" , model.getLat());
                        mapi.putExtra("lng" , model.getLng());
                        startActivity(mapi);
                    }
                };
            }
        };
        recyclerView.setAdapter(adapter);
        requests.keepSynced(true);
    }
}
