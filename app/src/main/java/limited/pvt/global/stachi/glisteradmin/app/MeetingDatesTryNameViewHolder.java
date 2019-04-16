package limited.pvt.global.stachi.glisteradmin.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MeetingDatesTryNameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtNames ;

    ItemClickListener itemClickListener;

    public MeetingDatesTryNameViewHolder (View itemView)
    {
        super(itemView);

        txtNames = (TextView) itemView.findViewById(R.id.retrieve_name);
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
