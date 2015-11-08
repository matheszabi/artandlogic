package com.artandlogic.part1.mainactivityhelpers;

import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.artandlogic.part1.MainActivity;
import com.artandlogic.part1.R;
import com.artandlogic.part1.asyntasks.DecodeAsynTask;

/**
 * Handle the "Decode" button click.
 * <p/>
 * The validation is done while the user type in the EditText.
 * The Decoder.decode() it will be called on an AsyncTask simulating a production environment case.
 * <p/>
 * Created by matheszabi on Nov/8/2015 0008.
 */
public class DecodeButtonClickListener implements View.OnClickListener, DecodeAsynTask.CompletionListener {

    private MainActivity mMainActivity;

    // cached value from layout: - can be asked as params in Constructor
    private EditText mDecodeInput;
    private TextView mDecodeResult;


    public DecodeButtonClickListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        if (mMainActivity != null) {
            mDecodeInput = (EditText) mMainActivity.findViewById(R.id.decodeInput);
            mDecodeResult = (TextView) mMainActivity.findViewById(R.id.decodeResult);

            // if mainActivity is not null, then the xml must have the required values and must be loaded:
            if (mDecodeInput == null || mDecodeResult == null) {
                throw new IllegalArgumentException("Missing required fields.Please check the MainActivity xml layout.");
            }

            // a different implementation can be:
            // - while the user type use the validator
            // in this case use an Async task, as how it would be a long process the decoding task

        }
    }

    @Override
    public void onClick(View v) {
        // it is allowed to initialize with null value
        if (mDecodeInput == null || mDecodeResult == null) {
            return;
        }

        mDecodeInput.setError(null);
        mDecodeResult.setText("");

        // press the "Done button"
        mDecodeInput.onEditorAction(EditorInfo.IME_ACTION_DONE);

        // append a prefix for Integer.decode. Use the same modified input for validator and engine
        String input = "0x"+ mDecodeInput.getText().toString();
        String errMessage = DecodeStringValidator.validate(input);
        if (errMessage != null) {
            mDecodeInput.setError(errMessage);
            return;
        }

        new DecodeAsynTask(mMainActivity, this).execute(input);
    }

    @Override
    public void onComplete(String result) {
        // set the result to UI
        mDecodeResult.setText(result);
    }
}
