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

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>
{
    private Context mContext ;
    private List<Upload> mUploads ;

    public ImageAdapter(Context context, List<Upload> uploads)
    {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position)
    {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getAttendanceName());
        holder.Userdate.setText(uploadCurrent.getAttendanceDate());
        holder.Usertime.setText(uploadCurrent.getAttendanceTime());
        Picasso.with(mContext).load(uploadCurrent.getAttendanceImage()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewName , Userdate , Usertime ;
        public ImageView imageView;

        public ImageViewHolder(View itemView)
        {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            Userdate = itemView.findViewById(R.id.text_view_date);
            Usertime = itemView.findViewById(R.id.text_view_time);
            imageView = itemView.findViewById(R.id.image_view_upload);
        }
    }
}
