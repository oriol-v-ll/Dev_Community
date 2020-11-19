package cat.urv.deim.asm.p2.common.listener;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Response;


public class SuccessResponse implements Response.Listener<String> {
    private static final String TAG = SuccessResponse.class.getSimpleName();

    private Context context;
    public SuccessResponse(Context context){
        this.context = context;
    }

    @Override
    public void onResponse(String response) {
        // Display the first 500 characters of the response string.
        //textView.setText("Response is: "+ response.substring(0,500));
        Log.e(TAG,"Response:"+ response);
        Log.d("Response", response);
        Intent intent = new Intent();
        intent.setAction("cat.urv.deim.padm.demo.MY_NOTIFICATION");
        intent.putExtra("data",response);
        context.sendBroadcast(intent);

    }
}
