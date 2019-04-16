package limited.pvt.global.stachi.glisteradmin.app;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ProfileViewHolder extends RecyclerView.ViewHolder //  implements View.OnClickListener
{
    public TextView /* txtOrderId , */  txtPhone , txtTime , txtStatus , txtLocation , txtArea ;

    public Button btnEdit , btnRemove ;

    public CardView cardView ;

 //   public ItemClickListener itemClickListener;

    public ProfileViewHolder(View itemView) {
        super(itemView);

    //    txtOrderId = (TextView) itemView.findViewById(R.id.order_id);
        txtPhone = (TextView) itemView.findViewById(R.id.retrieve_date);
        txtTime = (TextView) itemView.findViewById(R.id.retrieve_time);
        txtStatus = (TextView) itemView.findViewById(R.id.retrieve_status);
        txtLocation = (TextView) itemView.findViewById(R.id.retrieve_location);

        txtArea = (TextView) itemView.findViewById(R.id.retrieve_targetArea);

        btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
        btnRemove = (Button) itemView.findViewById(R.id.btnRemove);

        cardView = (CardView) itemView.findViewById(R.id.cardViewColor);

   //     itemView.setOnClickListener(this);
    }

/*    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view)
    {
        itemClickListener.onClick(view,getAdapterPosition() );
    }    */
}
