package com.codepath.apps.mysimpletweets.utility;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by eddietseng on 8/14/16.
 */
public class NetworkUtil {
    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void showNoNetworkMessage(Context context) {
        Toast.makeText(context,"Please Check Network Connection.", Toast.LENGTH_LONG).show();
    }
}
