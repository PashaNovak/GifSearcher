package com.novakpavel.gifsearcher.utils.exceptions;

import android.support.annotation.StringRes;

import static com.novakpavel.gifsearcher.constants.IAppConstants.NO_STRING_RES;

public enum ErrorStatus {

    REG_USER_ALREADY_EXISTS(-2, 0),
    REG_USER_NOT_FOUND(-3, 0)
    ;

    final int SERVER_ERROR_CODE;
    final @StringRes int stringResId;

    ErrorStatus(int server_error_code, int stringResId) {
        this.SERVER_ERROR_CODE = server_error_code;
        this.stringResId = stringResId;
    }

    public boolean hasStringMessageInRes(){
        return this.stringResId != NO_STRING_RES;
    }
}
