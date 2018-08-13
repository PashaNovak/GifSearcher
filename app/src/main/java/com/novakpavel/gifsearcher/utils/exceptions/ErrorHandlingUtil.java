package com.novakpavel.gifsearcher.utils.exceptions;

import android.support.annotation.Nullable;

import com.novakpavel.gifsearcher.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Single;

public abstract class ErrorHandlingUtil {

    public static <T> Single<T> defaultHandle(@Nullable Throwable throwable) {
        if (throwable == null) {
            return Single.error(new GifSearcherException(R.string.unknown_error));
        } else if (throwable instanceof GifSearcherException) {
            return Single.error(throwable);
        } else if (isNoNetworkException(throwable)) {
            return Single.error(new GifSearcherException(R.string.no_network));
        } else {
            return Single.error(new GifSearcherException(R.string.unknown_error));
        }
    }

    private static boolean isNoNetworkException(@Nullable Throwable throwable) {
        return throwable != null
                && (throwable instanceof SocketTimeoutException
                || throwable instanceof ConnectException
                || throwable instanceof UnknownHostException);

    }

}
