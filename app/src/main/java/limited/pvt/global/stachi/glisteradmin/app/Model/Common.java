package limited.pvt.global.stachi.glisteradmin.app.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import limited.pvt.global.stachi.glisteradmin.app.Notification.APIService;
import limited.pvt.global.stachi.glisteradmin.app.Notification.FCMRetrofitClient;
import limited.pvt.global.stachi.glisteradmin.app.Notification.RetrofitClient;


public class Common
{
    public static User currentUser;

    public static String PHONE_TEXT = "userPhone" ;

    public static final String UPDATE = "Update";

    public static final String DELETE = "Delete";

    public static final int PICK_IMAGE_REQUEST = 71 ;

    public static final String baseUrl = "https://maps.googleapis.com";

    public static final String fcmUrl = "https://fcm.googleapis.com/";

 //   public static final String DELETE = "Delete" ;
    public static final String USER_KEY = "User" ;
    public static final String PWD_KEY = "Password" ;

    public static String convertCodeToStatus(String code)
    {
        if (code.equals("0"))
            return "Placed";
        else if (code.equals("1"))
            return "Present" ;
        else if (code.equals("2"))
            return "Half-Day" ;
        else
            return "Absent";
    }

    public static APIService getFCMClient()
    {
        return FCMRetrofitClient.getClient(fcmUrl).create(APIService.class);
    }

    public static boolean isConnectedToInternet(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null)
        {
            NetworkInfo [] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
            {
                for (int i = 0 ; i < info.length; i++)
                {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false ;
    }


}
