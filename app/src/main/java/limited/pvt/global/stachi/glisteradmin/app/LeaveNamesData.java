package limited.pvt.global.stachi.glisteradmin.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class LeaveNamesData extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtleaveNames ;

    ItemClickListener itemClickListener;

    public LeaveNamesData (View itemView)
    {
        super(itemView);

        txtleaveNames = (TextView) itemView.findViewById(R.id.retrieve_leaveNames);
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
