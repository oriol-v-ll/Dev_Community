package cat.urv.deim.asm.p2.common.listener;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;


import cat.urv.deim.asm.p2.common.MainActivity;

public class ErrorListener implements Response.ErrorListener {

    private static final String TAG = ErrorListener.class.getSimpleName();

    Context context;

    public ErrorListener(Context context){
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG,"Error Response: "+error.getMessage());
        if (error == null){
            Log.e(TAG,"Error Response: "+error.getMessage());
            return;
        }
        if (error.networkResponse.statusCode == 404){
            Log.e(TAG,"Not found error response");
        }else if (error.networkResponse.statusCode == 500){
            Log.e(TAG,"Access denied");
        }
        Intent intent = new Intent();
        intent.setAction("cat.urv.deim.padm.FINISH_CALL_VOLLEY");
        context.sendBroadcast(intent);

    }
}
