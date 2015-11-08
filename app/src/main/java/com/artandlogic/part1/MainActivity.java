package com.artandlogic.part1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.artandlogic.part1.mainactivityhelpers.DecodeButtonClickListener;
import com.artandlogic.part1.mainactivityhelpers.DecodeTextChangedListener;
import com.artandlogic.part1.mainactivityhelpers.EncodeButtonClickListener;

/**
 * Main UI screen.
 * The Encode and the Decode UI it differs, please take a look!
 * The Decode style should be used in production, but it takes longer to implement then the Encode.
 */
public class MainActivity extends AppCompatActivity {

    // usually this class is huge and many initialisation code at:
    // onCreate() , onResume() and onActivityResult()

    // remove any code from here, which is not related with this UI
    // specially the application lifecycle methods, helper classes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add listeners:
        findViewById(R.id.encodeButton).setOnClickListener(new EncodeButtonClickListener(this));
        findViewById(R.id.decodeButton).setOnClickListener(new DecodeButtonClickListener(this));

        // make it user friendlier the decode:
        EditText decodeInput = (EditText) findViewById(R.id.decodeInput);
        decodeInput.addTextChangedListener(new DecodeTextChangedListener(decodeInput));


    }


}
