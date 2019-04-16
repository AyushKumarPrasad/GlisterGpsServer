package limited.pvt.global.stachi.glisteradmin.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtNames , txtChating , txtImage , txtMobile ;

    ItemClickListener itemClickListener;

    public NameViewHolder(View itemView)
    {
        super(itemView);

        txtNames = (TextView) itemView.findViewById(R.id.retrieve_name);
        txtMobile = (TextView) itemView.findViewById(R.id.retrieve_mobileNo);
        txtChating = (TextView) itemView.findViewById(R.id.retrieve_chat_messages);
        txtImage = (TextView) itemView.findViewById(R.id.retrieve_userImages);
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
