package limited.pvt.global.stachi.glisteradmin.app.ProductViewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import limited.pvt.global.stachi.glisteradmin.app.ItemClickListener;
import limited.pvt.global.stachi.glisteradmin.app.R;


public class NameHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtNames ;

    public ItemClickListener itemClickListener;

    public NameHolder1 (View itemView)
    {
        super(itemView);

        txtNames = (TextView) itemView.findViewById(R.id.retrieve_name_holder1);
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
