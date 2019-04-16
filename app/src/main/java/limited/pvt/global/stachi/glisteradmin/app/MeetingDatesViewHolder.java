package limited.pvt.global.stachi.glisteradmin.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MeetingDatesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtDates   ;

    ItemClickListener itemClickListener;

    public MeetingDatesViewHolder (View itemView)
    {
        super(itemView);

        txtDates = (TextView) itemView.findViewById(R.id.retrieve_meetingDates);

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