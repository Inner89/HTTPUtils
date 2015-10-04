package pw.sponges.httputils.request;

import pw.sponges.httputils.Request;
import pw.sponges.httputils.Result;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class POST extends Request {

    private String body = null;

    /**
     * No args constructor so it can be used as a builder
     */
    public POST() {
    }

    /**
     * Constructor without header properties map.
     * Header properties should be added with #addProperty
     * @param ssl - use https?
     * @param url - the url to make the request to
     * @param body - the body to send in the request
     */
    public POST(boolean ssl, String url, String body) {
        this.ssl = ssl;
        this.url = url;
        this.body = body;
        this.properties = new HashMap<>();
    }

    /**
     * Constructor with all params set.
     * @param ssl - use https?
     * @param url - the url to make the request to
     * @param properties - the header properties map
     * @param body - the body to send in the request
     */
    public POST(boolean ssl, String url, Map<String, String> properties, String body) {
        this.ssl = ssl;
        this.url = url;
        this.properties = properties;
        this.body = body;
    }

    /**
     * Creates the connection & gets the results.
     * @return this - builder pattern
     * @throws IOException - thrown by URL connections
     */
    @Override
    public POST connect() throws IOException {
        URL url = new URL(this.url);

        HttpURLConnection con;

        if (ssl) con = (HttpsURLConnection) url.openConnection();
        else con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        for (String key : properties.keySet()) {
            con.setRequestProperty(key, properties.get(key));
        }

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(body);
        out.close();

        int code = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuffer buffer = new StringBuffer();

        String input;
        while ((input = in.readLine()) != null) {
            buffer.append(input);
        }
        in.close();
        con.disconnect();

        result = new Result(code, buffer.toString());

        return this;
    }

    /**
     * Gets the body of the request.
     * @return body - the request body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body of the request.
     * @param body - the request body
     * @return this - builder pattern
     */
    public POST setBody(String body) {
        this.body = body;
        return this;
    }

}

