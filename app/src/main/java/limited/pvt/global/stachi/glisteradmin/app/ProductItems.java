package limited.pvt.global.stachi.glisteradmin.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import limited.pvt.global.stachi.glisteradmin.app.ProductNames.Name1;

public class ProductItems extends AppCompatActivity
{

    LinearLayout card1 , card2 , card3 , card4 , card5 , card6 , card7 , card8 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_items);

        card1 = (LinearLayout) findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductItems.this , Name1.class);
                startActivity(intent);
            }
        });

        card2 = (LinearLayout) findViewById(R.id.card2);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        card3 = (LinearLayout) findViewById(R.id.card3);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        card4 = (LinearLayout) findViewById(R.id.card4);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        card5 = (LinearLayout) findViewById(R.id.card5);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        card6 = (LinearLayout) findViewById(R.id.card6);
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        card7 = (LinearLayout) findViewById(R.id.card7);
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        card8 = (LinearLayout) findViewById(R.id.card8);
        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
