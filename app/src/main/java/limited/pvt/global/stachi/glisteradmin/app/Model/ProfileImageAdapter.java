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

public class ProfileImageAdapter extends RecyclerView.Adapter<ProfileImageAdapter.ProfileViewHolder>
{
    private Context mContext ;
    private List<User> mUploads ;

    public ProfileImageAdapter (Context context, List<User> uploads)
    {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(mContext).inflate(R.layout.profile_userdetails,parent,false);
        return new ProfileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position)
    {
        User uploadCurrent = mUploads.get(position);
        holder.name.setText("Name : " + uploadCurrent.getName());
        holder.phone.setText("Phone : " + uploadCurrent.getPhone());
        holder.address.setText("Address : " + uploadCurrent.getAddress());
        holder.email.setText("Email : " + uploadCurrent.getEmail());
        holder.aadharCard.setText("Aadhar Card : " + uploadCurrent.getAadharcard());
        holder.panCard.setText("Pan : " + uploadCurrent.getPancard());
        holder.refName.setText("Reference Name : " + uploadCurrent.getRefname());
        holder.refNumber.setText("Reference Number : " + uploadCurrent.getRefnumber());
        holder.emergency.setText("Emergency Number: " + uploadCurrent.getEmergencynumber());
        holder.password.setText("Password : " + uploadCurrent.getPassword());

        Picasso.with(mContext).load(uploadCurrent.getUserimage()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().
                into(holder. meetingImageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name , phone , address , email , aadharCard , panCard , refName , refNumber , emergency , password;
        public ImageView meetingImageView;

        public ProfileViewHolder (View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.profile_name);
            phone = itemView.findViewById(R.id.profile_phone);
            address = itemView.findViewById(R.id.profile_address);
            email = itemView.findViewById(R.id.profile_email);
            aadharCard = itemView.findViewById(R.id.profile_aadhar);
            panCard = itemView.findViewById(R.id.profile_pan);
            refName = itemView.findViewById(R.id.profile_refname);
            refNumber = itemView.findViewById(R.id.profile_refnumber);
            emergency = itemView.findViewById(R.id.profile_emergency);
            password = itemView.findViewById(R.id.profile_password);

            meetingImageView = itemView.findViewById(R.id.profile_image);

        }
    }
}
