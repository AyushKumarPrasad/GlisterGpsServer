package limited.pvt.global.stachi.glisteradmin.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LeaveDetailsViewHolder extends RecyclerView.ViewHolder
{
    public TextView leaveDate , leaveTime , leaveMessage ;

    public Button btnEdit , btnRemove ;

    public LeaveDetailsViewHolder (View itemView) {
        super(itemView);

        leaveDate = (TextView) itemView.findViewById(R.id.retrieve_leavedate);
        leaveTime = (TextView) itemView.findViewById(R.id.retrieve_leavetime);
        leaveMessage = (TextView) itemView.findViewById(R.id.retrieve_leavemessage);
    }

}
