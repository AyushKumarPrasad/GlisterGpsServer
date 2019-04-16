package limited.pvt.global.stachi.glisteradmin.app.ProductDates;

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
import limited.pvt.global.stachi.glisteradmin.app.MeetingDatesViewHolder;
import limited.pvt.global.stachi.glisteradmin.app.MeetingDetails;
import limited.pvt.global.stachi.glisteradmin.app.Model.MeetingDatabase;
import limited.pvt.global.stachi.glisteradmin.app.ProductDetails.Details1;
import limited.pvt.global.stachi.glisteradmin.app.ProductViewholder.DateHolder1;
import limited.pvt.global.stachi.glisteradmin.app.Products.ProductDatabase1;
import limited.pvt.global.stachi.glisteradmin.app.R;

public class Dates1 extends AppCompatActivity
{
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<ProductDatabase1, DateHolder1> adapter;

    FirebaseDatabase database;
    DatabaseReference requests   ;

    String phone ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates1);

        if (getIntent() != null)
        {
            phone = getIntent().getStringExtra("name");
        }

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("ProductDetails1").child(phone);

        recyclerView = (RecyclerView) findViewById(R.id.retrieve_dates1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders();
    }

    private void loadOrders()
    {
        adapter = new FirebaseRecyclerAdapter<ProductDatabase1, DateHolder1>
                (
                        ProductDatabase1.class,
                        R.layout.holder_date1,
                        DateHolder1.class,
                        requests
                )
        {
            @Override
            protected void populateViewHolder( DateHolder1 viewHolder, final ProductDatabase1 model, int position)
            {
                viewHolder.txtDates.setText(model.getUserDate1());

                viewHolder.itemClickListener = new ItemClickListener()
                {
                    @Override
                    public void onClick(View view, int position)
                    {
                        Intent map = new Intent(Dates1.this, Details1.class);
                        map.putExtra("nameDate", model.getUserName1());
                        map.putExtra("nameDate1", model.getUserDate1());
                        startActivity(map);
                    }
                };
            }
        };
        recyclerView.setAdapter(adapter);
        requests.keepSynced(true);
    }
}
