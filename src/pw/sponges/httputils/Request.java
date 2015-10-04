package pw.sponges.httputils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2015 Joe Burnard ("SpongyBacon").
 * By viewing this code, you agree to the terms
 * in the enclosed license.txt file.
 */
public abstract class Request {

    public boolean ssl = false;
    public String url = null;
    public Map<String, String> properties = null;

    public Result result = null;

    /**
     * No args constructor so it can be used as a builder
     */
    public Request() {
    }

    /**
     * Constructor without header properties map.
     * Header properties should be added with #addProperty
     * @param ssl - use https?
     * @param url - the url to make the request to
     */
    public Request(boolean ssl, String url) {
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
    public Request(boolean ssl, String url, Map<String, String> properties) {
        this.ssl = ssl;
        this.url = url;
        this.properties = properties;
    }

    public abstract Request connect() throws IOException;

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
    public boolean isSSL() {
        return ssl;
    }

    /**
     * Should https be used instead of http?
     * @param ssl - boolean for using https or http
     * @return this - builder pattern
     */
    public Request setSSL(boolean ssl) {
        this.ssl = ssl;
        return this;
    }

    /**
     * The String url that is being sent the request.
     * @return url
     */
    public String getURL() {
        return url;
    }

    /**
     * Sets the url that the request is being sent to.
     * @param url
     * @return this - builder pattern
     */
    public Request setURL(String url) {
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
    public Request setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Adds a header for the request to the properties Map.
     * @param key - the header name
     * @param value - the header value
     * @return this - builder pattern
     */
    public Request addProperty(String key, String value) {
        properties.put(key, value);
        return this;
    }

}
