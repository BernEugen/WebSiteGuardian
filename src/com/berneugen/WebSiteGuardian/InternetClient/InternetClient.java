package com.berneugen.WebSiteGuardian.InternetClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.berneugen.WebSiteGuardian.Model.StatusesModel;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class InternetClient {

    private Context context;

    public InternetClient(Context context) {
        this.context = context;
    }

    public boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }

    public int checkHttpClient(String url) {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response;
        try {
            response = client.execute(request);
        } catch (Exception ex) {
            return StatusesModel.FAILED_STATUS;
        }
        int statusCode = response.getStatusLine().getStatusCode();
        return statusCode;
    }

    public int checkSiteAvailability(String url) {
        if (isInternetConnected()) {
            int responseCode = checkHttpClient(url);
            return responseCode;
        } else {
            return -1;
        }
    }
}
