package com.grupopulpo.lubriacadora.duty.utils;

import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.TextView;

import com.grupopulpo.lubriacadora.duty.R;

public class ValidateForms {
    private Utils objUtils;

    public ValidateForms() {
      this.objUtils=new Utils();
    }

    public boolean validateFormSignin(TextView _etUser, TextView _etPass, Resources objResource) {
        boolean valid = true;
         if (TextUtils.isEmpty(_etUser.getText().toString())) {
            _etUser.setError(objResource.getString(R.string.lbl_required));
            valid = false;
            _etUser.requestFocus();
        } else {
            if (!this.objUtils.isUserValidLen(_etUser.getText().toString())) {
                _etUser.setError(objResource.getString(R.string.error_invalid_user));
                valid = false;
                _etUser.requestFocus();
            } else {
                _etUser.setError(null);
            }
        }
        if (TextUtils.isEmpty(_etPass.getText().toString())) {
            _etPass.setError(objResource.getString(R.string.lbl_required));
            valid = false;
            _etPass.requestFocus();
        } else {
            if (!this.objUtils.isPasswordValid(_etPass.getText().toString())) {
                _etPass.setError(objResource.getString(R.string.error_invalid_password));
                valid = false;
                _etPass.requestFocus();
            } else {
                _etPass.setError(null);
            }
        }

        return valid;
    }
}
