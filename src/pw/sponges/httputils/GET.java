package pw.sponges.httputils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GET {

    private boolean ssl;
    private String url;
    private Map<String, String> properties;

    private Result result = null;

    /**
     * No args constructor so it can be used as a builder
     */
    public GET() {
        this.ssl = false;
        this.url = null;
        this.properties = new HashMap<>();
    }

    /**
     * Constructor without header properties map.
     * Header properties should be added with #addProperty
     * @param ssl - use https?
     * @param url - the url to make the request to
     */
    public GET(boolean ssl, String url) {
        this.ssl = ssl;
        this.url = url;
        this.properties = new HashMap<>();
    }

    /**
     * Constructor with all params set.
     * @param ssl - use https?
     * @param url - the url to make the request to
     * @param properties - the header properties map
     */
    public GET(boolean ssl, String url, Map<String, String> properties) {
        this.ssl = ssl;
        this.url = url;
        this.properties = properties;
    }

    /**
     * Creates the connection & gets the results.
     * @return this - builder pattern
     * @throws IOException - thrown by URL connections
     */
    public GET connect() throws IOException {
        URL url = new URL(this.url);

        HttpURLConnection con;

        if (ssl) con = (HttpsURLConnection) url.openConnection();
        else con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        for (String key : properties.keySet()) {
            con.setRequestProperty(key, properties.get(key));
        }

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
     * Returns the results from the GET request in a Result object.
     * @return result - the Result object
     */
    public Result getResult() {
        return result;
    }

    /**
     * Using https?
     * @return ssl
     */
    public boolean isSsl() {
        return ssl;
    }

    /**
     * Should https be used instead of http?
     * @param ssl - boolean for using https or http
     * @return this - builder pattern
     */
    public GET setSsl(boolean ssl) {
        this.ssl = ssl;
        return this;
    }

    /**
     * The String url that is being sent the request.
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url that the request is being sent to.
     * @param url
     * @return this - builder pattern
     */
    public GET setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * The headers for the request in a Map.
     * @return properties - the headers Map
     */
    public Map<String, String> getProperties() {
        return properties;
    }

    /**
     * Sets the headers Map for the request.
     * @param properties - the headers Map
     * @return this - builder pattern
     */
    public GET setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Adds a header for the request to the properties Map.
     * @param key - the header name
     * @param value - the header value
     * @return this - builder pattern
     */
    public GET addProperty(String key, String value) {
        properties.put(key, value);
        return this;
    }

}