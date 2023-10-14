package com.example.getpost;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.util.Objects;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignInActivity extends AsyncTask<String, Void, String> {
    private final TextView statusField;
    private final TextView roleField;
    private final int byGetOrPost;
    private static final String URL = "http://192.168.43.110/getData.php";
    // Constructor
    public SignInActivity(TextView statusField, TextView roleField, int flag) {
        this.statusField = statusField;
        this.roleField = roleField;
        byGetOrPost = flag;
    }

    @Override
    protected String doInBackground(String... arg0) {
        if (byGetOrPost == 0) { // means by GET method
            try {
                String username = arg0[0];
                String password = arg0[1];
                HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(URL)).newBuilder();
                urlBuilder.addQueryParameter("username", username);
                urlBuilder.addQueryParameter("password", password);
                String link = urlBuilder.build().toString();

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(link)
                        .build();

                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return Objects.requireNonNull(response.body()).string();
                }
            } catch (IOException e) {
                return "Exception: " + e.getMessage();
            }
        } else if (byGetOrPost == 1) { // means by POST method
            try {
                String username = arg0[0];
                String password = arg0[1];

                OkHttpClient client = new OkHttpClient();

                // Create a request body with the POST data
                RequestBody formBody = new FormBody.Builder()
                        .add("username", username)
                        .add("password", password)
                        .build();

                Request request = new Request.Builder()
                        .url(URL)
                        .post(formBody)
                        .build();

                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return Objects.requireNonNull(response.body()).string();
                }

            } catch (IOException e) {
                return "Exception: " + e.getMessage();
            }
        }
        return null; // You should handle this case appropriately
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("notfound")) {
            statusField.setText("STATUS: FAILED");
            roleField.setText("ROLE: " + result);
        } else {
            statusField.setText("STATUS: SUCCESS");
            roleField.setText("ROLE: " + result);
        }
    }
}
