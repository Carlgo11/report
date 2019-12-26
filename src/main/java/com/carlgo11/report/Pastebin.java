package com.carlgo11.report;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Pastebin {

    private String api_user_key;
    private String api_dev_key;

    /**
     *
     * @param api_user_key Pastebin API_USER_KEY to use. Can be left blank
     * @param api_dev_key  Pastebin API_DEV_KEY to use.
     * @since 1.1
     */
    public Pastebin(String api_user_key, String api_dev_key) {
        this.api_user_key = api_user_key;
        this.api_dev_key = api_dev_key;
    }

    private boolean checkResponse(String response) {
        return response.substring(0, 15).equals("Bad API request");
    }

    /**
     * Posts a new Pastebin paste.
     *
     * @param name Name of the paste
     * @param body Content of the paste
     * @return Returns a string containing the URL of the paste if successful, otherwise NULL.
     * @throws UnsupportedEncodingException Throws unsupported encoding exception if the body or name can't be URL encoded.
     * @since 1.0
     */
    public String makePaste(String name, String body) throws UnsupportedEncodingException {
        String content = URLEncoder.encode(body, "UTF-8");
        String title = URLEncoder.encode(name + " report", "UTF-8");
        String data = String.format("api_option=paste&api_user_key=%s&api_paste_private=0&api_paste_name=%s&api_paste_expire_date=1M&api_paste_format=text&api_dev_key=%s&api_paste_code=%s", this.api_user_key, title, this.api_dev_key, content);
        String pasteURL = "http://www.pastebin.com/api/api_post.php";
        String response = this.page(pasteURL, data);
        if (response == null) {
            Plugin.sendError("No response from Pasebin");
            return null;
        }
        boolean check = this.checkResponse(response);
        if (check) {
            Plugin.sendError("Bad API Request.");
            return null;
        }
        return response;
    }

    private String page(String uri, String urlParameters) {
        URL url;
        HttpURLConnection connection = null;
        try {
            // Create connection
            url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + urlParameters.getBytes().length);
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
