package fixture.stubs

import static org.apache.commons.lang.StringUtils.EMPTY
import static org.apache.commons.lang.StringUtils.isEmpty

class HttpPoster {

    private String url
    private String queryString = EMPTY
    private Object queryObject

    public HttpPoster to(String url) {
        this.url = url
        return this
    }

    public HttpPoster withParameter(String parameterName, Object parameterValue) {
        if (!isEmpty(queryString)) {
            queryString += "&"
        }
        queryString += parameterName + "=" + parameterValue
        return this
    }

    public HttpPoster withObject(Object specification) {
        queryObject = specification
        return this
    }

    public String postObject() {
        URLConnection connection = openConnection()
        connection.setRequestProperty("Content-Type", "application/x-java-serialized-object");

        def writer = new ObjectOutputStream(connection.outputStream)
        writer.writeObject(queryObject)
        writer.flush()
        writer.close()

        return readResponse(connection)
    }

    public String postParameters() {
        URLConnection connection = openConnection()

        def writer = new OutputStreamWriter(connection.outputStream)
        writer.write(queryString)
        writer.flush()
        writer.close()

        return readResponse(connection)
    }

    public String getQueryString() {
        return queryString
    }

    private HttpURLConnection openConnection() {
        URLConnection connection = new URL(url).openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setRequestMethod("POST")
        httpURLConnection.doInput = true
        httpURLConnection.doOutput = true
        return httpURLConnection
    }

    private String readResponse(HttpURLConnection connection) {

        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (Exception e)
        {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuffer result = new StringBuffer();
        if (connection.getErrorStream()!=null)
        {
            result.append(connection.getResponseCode())
            result.append(": ");
            result.append(connection.getResponseMessage())
            result.append("\n")
        }

        String line
        while ((line = reader.readLine()) != null) {
            result.append(line)
        }
        return result.toString()
    }
}
