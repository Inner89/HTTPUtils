package pw.sponges.httputils;

public class Result {

    private final int code;
    private final String content;

    /**
     * Constructor with only the response code.
     * Would be used if no content was returned from the request.
     * @param code - the request response code
     */
    public Result(int code) {
        this.code = code;
        this.content = null;
    }

    /**
     * Constructor with the response code and the content from the request.
     * @param code - the request response code
     * @param content - the returned content from the request
     */
    public Result(int code, String content) {
        this.code = code;
        this.content = content;
    }

    /**
     * Returns the response code for the request.
     * @return code - the response code
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the content for the request.
     * @return content - the String content
     */
    public String getContent() {
        return content;
    }

    /**
     * Boolean to check if there is content in the results.
     * Would be used to see if any content was returned from the request,
     * e.g. if there was an error in the request.
     * @return boolean - if there is content in the results
     */
    public boolean hasContent() {
        return content != null;
    }

}