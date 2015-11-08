package com.artandlogic.part1.mainactivityhelpers;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Make it uppercase the typed letters the 'abcdef'
 * Call the validator while the user type.
 * <p/>
 * Created by matheszabi on Nov/8/2015 0008.
 */
public class DecodeTextChangedListener implements TextWatcher {

    private EditText mDecodeInput;

    public DecodeTextChangedListener(EditText decodeInput) {
        mDecodeInput = decodeInput;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = mDecodeInput.getText().toString();
        String errString = DecodeStringValidator.validate("0x" + text);
        mDecodeInput.setError(errString);


        //make it uppercase the last characters, just to looks better
        char[] chars = text.toCharArray();

        boolean changedCharacter = false;
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                // any of this cases:
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                    chars[i] = Character.toUpperCase(chars[i]);
                    changedCharacter = true;
                    break;
            }
        }
        if (changedCharacter) {
            changeText(new String(chars));
        }

    }

    private void changeText(String newText) {

        mDecodeInput.removeTextChangedListener(this);
        mDecodeInput.setText(newText);

        //move the cursor to end:
        int position = mDecodeInput.length();
        Editable editable = mDecodeInput.getText();
        Selection.setSelection(editable, position);

        mDecodeInput.addTextChangedListener(this);
    }
}
