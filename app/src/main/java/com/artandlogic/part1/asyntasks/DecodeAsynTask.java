package com.artandlogic.part1.asyntasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.artandlogic.part1.engine.Decoder;

/**
 * An AsyncTask implementation for production environment ( simulated).
 * It has a 2 sec sleeping now to simulate the decoding process.
 * <p/>
 * Created by matheszabi on Nov/8/2015 0008.
 */
public class DecodeAsynTask extends AsyncTask<String, Void, String> {


    public interface CompletionListener {
        void onComplete(String result);
    }

    private Context mContext;
    private TextView mResultUI;
    private CompletionListener onmCompletionListener;

    private ProgressDialog mDialog;

    public DecodeAsynTask(Context context, CompletionListener listener) {
        mContext = context;
        onmCompletionListener = listener;

    }

    protected void onPreExecute() {
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage("Please wait while deconding!");
        mDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        if (params == null || params.length == 0) {
            return null;
        }
        int intValue = Integer.decode(params[0]);


        // Simulate a long processing task:
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }


        int decodedInt = Decoder.decode2bytes(intValue);

        // if you are interested: Integer.toBinaryString(decodedInt);

        return Integer.toString(decodedInt);
    }

    protected void onPostExecute(String result) {

        mDialog.dismiss();

        if (onmCompletionListener != null) {
            onmCompletionListener.onComplete(result);
        }
    }
}
