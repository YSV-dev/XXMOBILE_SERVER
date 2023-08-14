package com.swlibs.common.net.web;

//import java.io.ByteArrayInputStream;
import com.swlibs.common.logging.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.HashMap;

/**
 *
 * @author brzsmg
 */
public class WebResponse {
    private boolean mHeaderSended = false;
    private boolean mBodySended = false;
    private WebRequest mWebRequest;
    private HashMap<String, String> mHeaders;
    private ByteArrayOutputStream  mStream;
    private int mContentLength = 0;
    
    //private final int mHttpStatus;
    
    public WebResponse(WebRequest webRequest)
    {
        mWebRequest = webRequest;
        mHeaders = new HashMap<>();
        mStream = new ByteArrayOutputStream();
        //mHttpStatus = httpStatus;
    }
    
    public void addHeader(String key, String value)
    {
        if(mHeaderSended){
            throw new RuntimeException("Заголовки уже отправлены.");
        }
        mHeaders.put(key, value);
    }
    
    public void sendHeaders(int httpStatus)
    {
        try {
            addHeader("Host", mWebRequest.getServerAddress() + ":" + mWebRequest.getPort());
            if(!mHeaders.containsKey("Content-Length"))
            {
                addHeader("Content-Length", ((Integer)mContentLength).toString());
                mBodySended = true;
            }
            if(!mHeaders.containsKey("Content-type"))
            {
                addHeader("Content-type", "text/html; charset=UTF-8");
            }
            if(!mHeaders.containsKey("Expires"))
            {
                addHeader("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
            }
            if(!mHeaders.containsKey("Cache-Control"))
            {
                addHeader("Cache-Control", "no-cache, must-revalidate");
            }
            if(!mHeaders.containsKey("Pragma"))
            {
                addHeader("Pragma", "no-cache");
            }

            String header = "HTTP/1.1 " + httpStatus + " OK\r\n";
            for(HashMap.Entry<String, String> entry : mHeaders.entrySet()) {
                header += entry.getKey() + ": " + entry.getValue() + "\r\n";
            }
            header += "\r\n";
            mWebRequest.getStream().write(header.getBytes("UTF-8"));
            mHeaderSended = true;
            flush();
            
        /*} catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Неудалось отправить заголовки.",ex);*/
        } catch (SocketException ex) {
            throw new ClientCanceledConnection(ex.getClass().getSimpleName() + ((ex.getMessage() != null) ? (": " + ex.getMessage()) : ""));
        } catch (IOException ex) {
            Log.e(ex.getClass().getSimpleName() + ((ex.getMessage() != null) ? (": " + ex.getMessage()) : ""));
            ex.printStackTrace();
            throw new RuntimeException("Неудалось отправить заголовки.", ex);
        }
    }
    
    public void setContentType(String value)
    {
        addHeader("Content-type", value);
    }
    
    /*private String getContentLength()
    {
        if(!mHeaders.containsKey("Content-Length"))
        {
            return mHeaders.get("Content-Length");
        }
        else
        {
            return null;
        }
    }*/
    
    public void setContentLength(long value)
    {
        addHeader("Content-Length", ((Long)value).toString());
    }
    
    private void flush() throws IOException {
        //try {
            mWebRequest.getStream().write(mStream.toByteArray());
            mStream.reset();
        /*} catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }*/ /*catch (IOException ex) {
            throw new RuntimeException(ex);
        }   */   
    }
    
    public void send(byte[] data) {
        send(data, data.length);
    }
    
    public void send(byte[] data, int length) {
        if(mBodySended){
            throw new RuntimeException("Ответ уже отправлен.");
        }
        try {
            if(mHeaderSended)
            {
                mWebRequest.getStream().write(data, 0, length);
            }
            else
            {
                mStream.write(data);
            }
            mContentLength += data.length;
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void send(String data)
    {
        try {
            send(data.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Неудалось отправить данные.",ex);
        }
    }
    
}
