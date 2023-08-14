package com.swlibs.common.net.web;

import com.swlibs.common.logging.Log;
import com.swlibs.common.utils.Properties;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author brzsmg
 */
//ETag
public class WebRequest {
    //
    protected int mReadTimeWait = Properties.getInt("read_time_wait");
    protected int mWriteTimeWait = Properties.getInt("write_time_wait");
    protected int mMaxPostDataSize = Properties.getInt("max_post_data_size");
    protected int mMaxHeadersSize = Properties.getInt("max_headers_size");
    protected int mBufferSize = 1024 * 64;
    protected String mQueryPattern = "^\\w+(\\s+([^\\s\\?]+)[\\?]?([^\\s]*))\\s+HTTP/.*|";
    
    protected String    mRawHeaders = "";
    protected String    mQuery = "";      // Запрос
    protected String    mRawGET = "";
    protected byte[]    mRawPOST = new byte[0];
    
    protected String[]  mPaths = new String[0];
        
    protected String    mHTTPHead = "";         // Заголовок запроса
    protected HashMap<String, String> mHeaders; // Массив заголовков
    protected HashMap<String, String> mGET;     // GET Параметры
    protected HashMap<String, String> mPOST;    // POST Параметры
    
    protected int mHttpStatus = 200;
    protected String httpError  = "";
    
    private final Socket mSocket;
    private final WebServer mWebServer;
    private IWebRouter mWebRouter;
    
    private int mRequestId;
    
    private long mStartTime;
    
    public WebRequest(int requestId, WebServer webServer, Socket socket, long startTime) {
        mWebServer = webServer;
        mSocket = socket;
        mHeaders = new HashMap<>();
        mGET = new HashMap<>();
        mPOST = new HashMap<>();
        mRequestId = requestId;
        mStartTime = startTime;
    }

    public int getRequestId() {
        return mRequestId;
    }
    
    public long getStartTime() {
        return mStartTime;
    }
    
    public void wait(IWebRouter webRouter) {
        //System.out.println("["+getAddress() + "] Connecting.");
        mWebRouter = webRouter;
        
        ReadRequest();
        
        webRouter.route(this, mHttpStatus);

        try {
            mSocket.close();
            //var s = mRawHeaders;
        } catch (IOException ex) {
            Log.e("Socket closing. " + WebRequest.class.getSimpleName());
        }
        //System.out.println("["+getAddress() + "] Request: " + mQuery);
    }
    
    public String getServerAddress() {
        return ((InetSocketAddress)mSocket.getLocalSocketAddress())
                .getAddress().toString().replace("/","");
    }
    
    public int getServerPort() {
        return ((InetSocketAddress)mSocket.getLocalSocketAddress()).getPort();
    }
    
    public String getAddress() {
        return ((InetSocketAddress)mSocket.getRemoteSocketAddress())
                .getAddress().toString().replace("/","");
    }
    
    public int getPort() {
        return ((InetSocketAddress)mSocket.getRemoteSocketAddress()).getPort();
    }
    
    public String getQuery() {
        return mQuery;
    }
    
    public String getPath(int index) {
        if(mPaths.length <= index) {
            return "";
        }
        return mPaths[index];
    }
    
    public OutputStream getStream() throws IOException {
        return mSocket.getOutputStream();
    }
    
    public String getPostAsText() {
        return new String(mRawPOST, StandardCharsets.UTF_8);
    }
    
    public String getHeader(String key) {
        if (mHeaders.containsKey(key)) {
            return mHeaders.get(key);
        }
        return null;
    }
    
    public String getParam(String key) {
        if (mGET.containsKey(key)) {
            return mGET.get(key);
        }
        if (mPOST.containsKey(key)) {
            return mPOST.get(key);
        }
        return null;
    }
    
    public HashMap<String, String> getPostParams(){
        return this.mPOST;
    }
    
    public HashMap<String, String> getGetParams(){
        return this.mGET;
    }
    
    public int getContentLength() {
        if (this.mHeaders.containsKey("Content-Length")) {
            return Integer.valueOf(this.mHeaders.get("Content-Length"));
        } else {
            return 0;
        }
    }
    
    public String getContentType() {
        if (this.mHeaders.containsKey("Content-Type")) {
            return this.mHeaders.get("Content-Type");
        } else {
            return "";
        }
    }
    
    private void setError(int status) {
        mHttpStatus = status;
    }
    
    private void ReadRequest() {
        byte[] buffer = new byte[mBufferSize];
        int bufferLenght;
        boolean readHeader = false;
        boolean reading = true;
        Integer contentLength = null;
        int size = 0;
        try {
            while (reading) {
                if(readHeader) {
                    if(contentLength <= size) {
                        /*if(getContentType().contains("application/x-www-form-urlencoded"))
                        {
                            //TODO: getContentType().contains("charset=UTF-8")
                            String params = new String(mRawPOST, StandardCharsets.UTF_8);
                            params = java.net.URLDecoder.decode(params, StandardCharsets.UTF_8.name());
                            mPOST = parseParams(params);
                        }*/
                        reading = false;
                        break;
                    }
                }

                mSocket.setSoTimeout(mReadTimeWait);
                bufferLenght = mSocket.getInputStream().read(buffer, 0, buffer.length);
                if(bufferLenght < 1) {
                    break;
                }
                if(!readHeader) {
                    String str = new String(buffer, StandardCharsets.ISO_8859_1);
                    int p = str.indexOf("\r\n\r\n");
                    if (p >= 0) {
                        mRawHeaders += str.substring(0, p);
                        ParseRawHeaders();
                        contentLength = getContentLength();
                        size = bufferLenght - (p + 4);
                        mRawPOST = new byte[contentLength];
                        System.arraycopy(buffer, (p+4), mRawPOST, 0, size);
                        readHeader = true;
                    } else {
                        mRawHeaders += str;
                    }
                } else {
                    System.arraycopy(buffer, 0, mRawPOST, size, bufferLenght);
                    size = size + bufferLenght;
                }

            }
            if(getContentType().contains("application/x-www-form-urlencoded"))
            {
                //TODO: getContentType().contains("charset=UTF-8")
                String params = new String(mRawPOST, StandardCharsets.UTF_8);
                params = java.net.URLDecoder.decode(params, StandardCharsets.UTF_8.name());
                mPOST = parseParams(params);
            }
            mSocket.setSoTimeout(mWriteTimeWait);
        } catch (Exception ex) {
            setError(500);
        }
    }
    
    private void ParseRawHeaders() {
        String[] hs = mRawHeaders.split("\n");
        if (hs.length > 0) {
            mHTTPHead = hs[0].trim();
        }
        for (int i = 1; i < hs.length; i++) {
            String[] h = hs[i].split(":");
            if (h.length > 1) {
                if (!this.mHeaders.containsKey(h[0])) {
                    this.mHeaders.put(h[0], h[1].trim());
                }
            }
        }

        Matcher matcher = Pattern.compile(mQueryPattern).matcher(mHTTPHead);
        if(!matcher.find()) {
            setError(400);
            return;
            //throw new RuntimeException("400");
        }
        /*var q0 = matcher.groupCount();
        var q1 = matcher.start();
        var q2 = matcher.end();
                    var dd = matcher.group(1).trim();*/

        //var query = StringEscapeUtils.unescapeJava(matcher.group(1).trim());
        String query = matcher.group(1).trim();
        //System.out.println("matcher" + matcher.group(1).trim());
        //URL url = new URL(matcher.group(1).trim());
        //var query = url.toURI().toString();
        if (query.contains("..")) {
            setError(400);
            return;
            //throw new RuntimeException("400");
        }
        String[] split = query.split("\\?");
        if(split.length < 1) {
            setError(400);
            return;
        }
        if(split.length > 1) {
            mQuery = split[0];
            mRawGET = split[1];
            try {
                mRawGET = java.net.URLDecoder.decode(mRawGET, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException ex) {
                setError(400);
                return;
            }
            mGET = parseParams(mRawGET);
        } else {
            mQuery = query;
        }
        mPaths = mQuery.substring(1).split("/");
        
    }
    
    private HashMap<String,String> parseParams(String raw) {
        HashMap<String,String> result = new HashMap<>();
        String[] params = raw.split("&");
        for (String param : params) {
            java.lang.String[] pairs = param.split("=");
            if (pairs.length > 1) {
                result.put(pairs[0], pairs[1]);
            } else {
                result.put(pairs[0], "");
            }
        }
        return result;
    }
    
    /*private void ParseRawPOST()
    {
        var length = getContentLength();
        if(mMaxPostDataSize > 0)
        {
            if(mMaxPostDataSize < length)
            {
                throw new RuntimeException("Превышение MaxPostData.");
            }
        }
        if(mRawPOST.length != length)
        {
            throw new RuntimeException("Размер POST данных не сооответствует указанному в заголовке.");
        }
        if(getContentType().contains("application/x-www-form-urlencoded"))
        {   
            //TODO: getContentType().contains("charset=UTF-8")
            var params = new String(mRawPOST, StandardCharsets.UTF_8);
            mPOST = parseParams(params);
        }
    }*/
    
}
