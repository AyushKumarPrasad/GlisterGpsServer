package limited.pvt.global.stachi.glisteradmin.app.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import limited.pvt.global.stachi.glisteradmin.app.R;

public class ImageAdapterMeetingDetails extends RecyclerView.Adapter<ImageAdapterMeetingDetails.ImageViewHolderMeetingDetails>
{
    private Context mContext ;
    private List<MeetingDatabase> mUploads ;

    public ImageAdapterMeetingDetails (Context context, List<MeetingDatabase> uploads)
    {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolderMeetingDetails onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item_meeting_details,parent,false);
        return new ImageViewHolderMeetingDetails(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolderMeetingDetails holder, int position)
    {
        MeetingDatabase uploadCurrent = mUploads.get(position);
        holder.meetingName.setText("Retailer Name : " + uploadCurrent.getMeetingName());
        holder.meetingDate.setText("Date : " + uploadCurrent.getMeetingDate());
        holder.meetingStartTime.setText("Start Time : " + uploadCurrent.getMeetingStartTime());
        holder.meetingEndTime.setText("End Time : " + uploadCurrent.getMeetingEndTime());
        holder.meetingMobile.setText("Mobile : " + uploadCurrent.getMeetingMobile());
        holder.meetingAddress.setText("Location : " + uploadCurrent.getMeetingAddress());
        holder.meetingOrder.setText("Order : " + uploadCurrent.getMeetingOrder());

        Picasso.with(mContext).load(uploadCurrent.getMeetingImage()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().
                into(holder. meetingImageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolderMeetingDetails extends RecyclerView.ViewHolder
    {
        public TextView meetingName , meetingDate , meetingStartTime , meetingEndTime , meetingMobile , meetingAddress , meetingOrder ;
        public ImageView meetingImageView;

        public ImageViewHolderMeetingDetails(View itemView)
        {
            super(itemView);

            meetingName = itemView.findViewById(R.id.text_view_name_meetingDetails);
            meetingDate = itemView.findViewById(R.id.text_view_date_meetingDetails);
            meetingStartTime = itemView.findViewById(R.id.text_view_starttime_meetingDetails);
            meetingEndTime = itemView.findViewById(R.id.text_view_endtime_meetingDetails);
            meetingMobile = itemView.findViewById(R.id.text_view_mobile_meetingDetails);
            meetingAddress = itemView.findViewById(R.id.text_view_address_meetingDetails);
            meetingImageView = itemView.findViewById(R.id.image_view_upload_meetingDetails);
            meetingOrder = itemView.findViewById(R.id.text_view_order_meetingDetails);
        }
    }
}
