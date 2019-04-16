package limited.pvt.global.stachi.glisteradmin.app.ProductViewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import limited.pvt.global.stachi.glisteradmin.app.ItemClickListener;
import limited.pvt.global.stachi.glisteradmin.app.R;

public class DateHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtDates   ;

    public ItemClickListener itemClickListener;

    public DateHolder1 (View itemView)
    {
        super(itemView);

        txtDates = (TextView) itemView.findViewById(R.id.retrieve_date_holder1);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view)
    {
        itemClickListener.onClick(view,getAdapterPosition());
    }
}
