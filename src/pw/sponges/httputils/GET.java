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

    private GETResult result = null;

    public GET() {
        this.ssl = false;
        this.url = null;
        this.properties = new HashMap<>();
    }

    public GET(boolean ssl, String url, String body) {
        this.ssl = ssl;
        this.url = url;
        this.properties = new HashMap<>();
    }

    public GET(boolean ssl, String url, Map<String, String> properties, String body) {
        this.ssl = ssl;
        this.url = url;
        this.properties = properties;
    }

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

        result = new GETResult(code, buffer.toString());

        return this;
    }

    public GETResult getResult() {
        return result;
    }

    public boolean isSsl() {
        return ssl;
    }

    public GET setSsl(boolean ssl) {
        this.ssl = ssl;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public GET setUrl(String url) {
        this.url = url;
        return this;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public GET setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public GET addProperty(String key, String value) {
        properties.put(key, value);
        return this;
    }

}

class GETResult {

    private final int code;
    private final String content;

    public GETResult(int code) {
        this.code = code;
        this.content = null;
    }

    public GETResult(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    public boolean hasContent() {
        return content != null;
    }

}