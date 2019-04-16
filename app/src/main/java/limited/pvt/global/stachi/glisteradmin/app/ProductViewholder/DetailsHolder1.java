package limited.pvt.global.stachi.glisteradmin.app.ProductViewholder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import limited.pvt.global.stachi.glisteradmin.app.Products.ProductDatabase1;
import limited.pvt.global.stachi.glisteradmin.app.R;

public class DetailsHolder1 extends RecyclerView.Adapter<DetailsHolder1.ViewHolder1>
{

    private Context mContext ;
    private List<ProductDatabase1> mUploads ;

    public DetailsHolder1 (Context context, List<ProductDatabase1> uploads)
    {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(mContext).inflate(R.layout.holder_details1,parent,false);
        return new  ViewHolder1 (v);
    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, int position)
    {
        ProductDatabase1 uploadCurrent = mUploads.get(position);

        holder.txtName.setText("Name : " + uploadCurrent.getUserName1());
        holder.txtDate.setText("Date : " + uploadCurrent.getUserDate1());
        holder.txtTime.setText("Time : " + uploadCurrent.getUserTime1());
        holder.txtPhone.setText("Phone : " + uploadCurrent.getUserPhone1());
        holder.txtAddress.setText("Address : " + uploadCurrent.getUserAddress1());
        holder.txtItems.setText(uploadCurrent.getUserItem1()
                        + uploadCurrent.getUserItem2()
                        + uploadCurrent.getUserItem3()
                        + uploadCurrent.getUserItem4()
                        + uploadCurrent.getUserItem5()
                        + uploadCurrent.getUserItem6()
                        + uploadCurrent.getUserItem7()
                        + uploadCurrent.getUserItem8()
                        + uploadCurrent.getUserItem9()
                        + uploadCurrent.getUserItem10()
                        + uploadCurrent.getUserItem11()
                        + uploadCurrent.getUserItem12()
                        + uploadCurrent.getUserItem13()
                        + uploadCurrent.getUserItem14());

        holder.txtTotal.setText("Total :" + uploadCurrent.getUserTotal1());
        holder.txtPaymentReceived.setText("Payment Received :" + uploadCurrent.getUserPayment1());
        holder.txtOutstanding.setText("Outstanding :" + uploadCurrent.getUserOutstanding1());
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }


    public class ViewHolder1 extends RecyclerView.ViewHolder
    {
        public TextView txtPhone , txtTime , txtDate , txtAddress , txtName  , txtItems , txtTotal ,  txtPaymentReceived
                , txtOutstanding;

        public ViewHolder1 (View itemView)
        {
            super(itemView);

            txtPhone = (TextView) itemView.findViewById(R.id.holder_mobile_details1);
            txtTime = (TextView) itemView.findViewById(R.id.holder_time_details1);
            txtDate = (TextView) itemView.findViewById(R.id.holder_date_details1);
            txtAddress = (TextView) itemView.findViewById(R.id.holder_address_details1);
            txtName = (TextView) itemView.findViewById(R.id.holder_name_details1);
            txtItems = (TextView) itemView.findViewById(R.id.holder_itemsList_details1);
            txtTotal = (TextView) itemView.findViewById(R.id.holder_total_details1);
            txtPaymentReceived = (TextView) itemView.findViewById(R.id.holder_payment_details1);
            txtOutstanding = (TextView) itemView.findViewById(R.id.holder_outstanding_details1);
        }
    }
}
