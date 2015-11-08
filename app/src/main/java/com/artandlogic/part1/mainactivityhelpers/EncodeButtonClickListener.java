package com.artandlogic.part1.mainactivityhelpers;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.artandlogic.part1.MainActivity;
import com.artandlogic.part1.R;
import com.artandlogic.part1.engine.Encoder;

/**
 * Handle the "Encode" button click.
 * It will call the EncodeStringValidator.validate() and  Encoder.encode() on click.
 *
 * Created by matheszabi on Nov/8/2015 0008.
 */
public class EncodeButtonClickListener implements View.OnClickListener {

    private static final String TAG = EncodeButtonClickListener.class.getSimpleName();

    private MainActivity mMainActivity;

    // cached value from layout: - can be asked as params in Constructor
    private EditText mEncodeInput ;
    private TextView mEncodeResult;

    /**
     * MainActivity must load the layout before this is called.
     * @param mainActivity can be null, but on that case the button click it will do nothing
     */
    public EncodeButtonClickListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        if(mMainActivity != null){
            mEncodeInput = (EditText) mMainActivity.findViewById(R.id.encodeInput);
            mEncodeResult = (TextView) mMainActivity.findViewById(R.id.encodeResult);

            // if mainActivity is not null, then the xml must have the required values and must be loaded:
            if(mEncodeInput == null || mEncodeResult == null){
                throw new IllegalArgumentException("Missing required fields.Please check the MainActivity xml layout.");
            }
        }
    }

    @Override
    public void onClick(View v) {
        // it is allowed to initialize with null value
        if(mEncodeInput == null || mEncodeResult == null){
            return;
        }
        mEncodeInput.setError(null);
        mEncodeResult.setText("");

        // press the "Done button"
        mEncodeInput.onEditorAction(EditorInfo.IME_ACTION_DONE);


        // validate the user input: - set a message in case of error:
        // must be a "signed integer in the 14-bit range [-8192..+8191]"
        String input = mEncodeInput.getText().toString();
        String errMessage = EncodeStringValidator.validate(input);
        if(errMessage != null) {
            mEncodeInput.setError(errMessage);
            return;
        }
        // this is an accepted, validated value:
        int inputInteger = Integer.decode(input);

        // if it would be a long processing the engine operation it should be called on a separated thread
        // since it has just a few fast operation it can be called here
        // otherwise use an AsyncTask here, presented at Decode

        String encodedValue = Encoder.encode(inputInteger);

        // in production do not log the encrypted passwords like this :)
        Log.v(TAG, "encodedValue: "+encodedValue);

        // display the result:
        mEncodeResult.setText(encodedValue);
    }
}
