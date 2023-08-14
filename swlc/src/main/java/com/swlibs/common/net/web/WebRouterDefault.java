package com.swlibs.common.net.web;

import com.swlibs.common.system.Console;
import com.swlibs.common.system.FileExtension;
import com.swlibs.common.logging.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author brzsmg
 */
public class WebRouterDefault implements IWebRouter
{
    private int mBufferSize = 1024 * 64;
    
    private String mFilePath = "M:\\Sources\\Programs\\Server\\build\\libs\\www";
    private String mIndexFile = "index.html";
    
    //private WebRequest mWebRequest;
    //private WebResponse mResponse;
    
    protected static AtomicInteger mUid = new AtomicInteger(0);
    
    @Override
    public void route(WebRequest webRequest, int httpStatus) {
        WebResponse response = new WebResponse(webRequest);
        if(httpStatus != 200){
            String error = "Unknown Error";
            if(httpStatus == 400)
            {
                error = "Bad Request";
            }
            if(httpStatus == 500)
            {
                error = "Internal Server Error";
            }
            sendError(response, httpStatus, error);
        }
        
        Integer uid = mUid.incrementAndGet();
        try {
            FileInputStream stream = null;
            try {
                String requestSource = webRequest.getQuery();
                if(requestSource.endsWith("/"))
                {
                    requestSource += mIndexFile;
                }

                stream = new FileInputStream(mFilePath + requestSource);

                String extension = requestSource.substring(requestSource.lastIndexOf("."));
                String contentType = FileExtension.getMimeType(extension);
                
                httpStatus = 200;
                response.addHeader("ETag", uid.toString());
                response.setContentType(contentType);
                response.setContentLength(stream.getChannel().size());
                response.sendHeaders(httpStatus);

                byte[] buffer = new byte[mBufferSize];
                int bufferLenght = 0;

                while ((bufferLenght = stream.read(buffer, 0, buffer.length)) > 0)
                {
                    response.send(buffer);
                }
            } catch (FileNotFoundException ex) {
                httpStatus = 404;
                sendError(response, httpStatus, "File not found");
                stream = null;
            } catch (IOException ex) {
                httpStatus = 500;
                sendError(response, httpStatus, "Internal Server Error");
            } finally {
                try {
                    if(stream != null){
                        stream.close();
                    }
                } catch (IOException ex) {
                    httpStatus = 500;
                    sendError(response, httpStatus, "Internal Server Error");
                }
            }
        } catch (RuntimeException ex) {
            Console.writeln("RuntimeException: " + ex.getMessage());
        }
        Log.i("[("+uid.toString() + ")" + webRequest.getAddress() + "] (" + httpStatus + ") Request: " + webRequest.getQuery());
    }
    
    private void sendError(WebResponse response, int httpStatus, String httpError) {
        response.setContentType("text/html; charset=UTF-8");
        
        String html = "<html>\r\n";
        html += "<head><title>Error</title></head>\r\n";
        html += "<body><h3>" + httpStatus + "</h3>" + httpError + "</body>\r\n";
        html += "</html>";
        
        response.send(html);
        response.sendHeaders(httpStatus);
    }


}
