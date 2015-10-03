package pw.sponges.httputils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class POST {

    private boolean ssl;
    private String url;
    private Map<String, String> properties;
    private String body;

    private POSTResult result = null;

    public POST() {
        this.ssl = false;
        this.url = null;
        this.properties = new HashMap<>();
        this.body = null;
    }

    public POST(boolean ssl, String url, String body) {
        this.ssl = ssl;
        this.url = url;
        this.body = body;
        this.properties = new HashMap<>();
    }

    public POST(boolean ssl, String url, Map<String, String> properties, String body) {
        this.ssl = ssl;
        this.url = url;
        this.properties = properties;
        this.body = body;
    }

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

        result = new POSTResult(code, buffer.toString());

        return this;
    }

    public POSTResult getResult() {
        return result;
    }

    public boolean isSsl() {
        return ssl;
    }

    public POST setSsl(boolean ssl) {
        this.ssl = ssl;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public POST setUrl(String url) {
        this.url = url;
        return this;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public POST setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public POST addProperty(String key, String value) {
        properties.put(key, value);
        return this;
    }

    public String getBody() {
        return body;
    }

    public POST setBody(String body) {
        this.body = body;
        return this;
    }

}

class POSTResult {

    private final int code;
    private final String content;

    public POSTResult(int code) {
        this.code = code;
        this.content = null;
    }

    public POSTResult(int code, String content) {
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