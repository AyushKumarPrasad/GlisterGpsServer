package limited.pvt.global.stachi.glisteradmin.app.ProductNames;

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

import limited.pvt.global.stachi.glisteradmin.app.ItemClickListener;
import limited.pvt.global.stachi.glisteradmin.app.MeetingDates;
import limited.pvt.global.stachi.glisteradmin.app.MeetingDatesTryNameViewHolder;
import limited.pvt.global.stachi.glisteradmin.app.MeetingNames;
import limited.pvt.global.stachi.glisteradmin.app.Model.Common;
import limited.pvt.global.stachi.glisteradmin.app.Model.User;
import limited.pvt.global.stachi.glisteradmin.app.ProductDates.Dates1;
import limited.pvt.global.stachi.glisteradmin.app.ProductDetails.Details1;
import limited.pvt.global.stachi.glisteradmin.app.ProductViewholder.NameHolder1;
import limited.pvt.global.stachi.glisteradmin.app.R;

public class Name1 extends AppCompatActivity
{
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<User, NameHolder1> adapter;

    FirebaseDatabase database;
    DatabaseReference requests   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name1);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("User");

        recyclerView = (RecyclerView) findViewById(R.id.retrieve_name1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders();
    }

    private void loadOrders()
    {
        adapter = new FirebaseRecyclerAdapter<User, NameHolder1>
                (
                        User.class,
                        R.layout.holder_name1,
                        NameHolder1.class,
                        requests
                )
        {
            @Override
            protected void populateViewHolder( NameHolder1 viewHolder, final User model, int position)
            {
                viewHolder.txtNames.setText(model.getName());

                viewHolder.itemClickListener = new ItemClickListener()
                {
                    @Override
                    public void onClick(View view, int position)
                    {
                        if (! model.getName().equals(Common.currentUser.getName()))
                        {
                            Intent map = new Intent(Name1.this, Dates1.class);
                            map.putExtra("name", model.getName());
                            startActivity(map);
                        }
                        else
                        {
                            Toast.makeText(Name1.this,"You are admin my friend " , Toast.LENGTH_SHORT).show();
                        }
                    }
                };
            }
        };
        recyclerView.setAdapter(adapter);
        requests.keepSynced(true);
    }
}
