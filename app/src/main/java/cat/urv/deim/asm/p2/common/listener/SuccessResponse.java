package cat.urv.deim.asm.p2.common.listener;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Response;

import cat.urv.deim.asm.p2.common.MainActivity;


public class SuccessResponse implements Response.Listener<String> {
    private static final String TAG = SuccessResponse.class.getSimpleName();

    private Context context;

    public SuccessResponse(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(String response) {
        Log.e(TAG, "Response:" + response);
        sendBroacastMessage(response);

    }
    private void sendBroacastMessage(String message){
        Intent intent = new Intent();
        intent.setAction("cat.urv.deim.asm.p2.FINISH_CALL_VOLLEY");
        intent.putExtra(MainActivity.UpdateUIBroadcastReceiver.UPDATED_DATA_KEY,message);
        context.sendBroadcast(intent);
    }
}
