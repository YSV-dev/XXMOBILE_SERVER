package com.swlibs.common.system;

/**
 *
 * @author brzsmg
 */
public class FileExtension {
    public static String getMimeType(String extension)
    {
        String contentType = "";
        switch(extension){
            case ".htm":
            case ".html":
                contentType = "text/html";
                break;
            case ".css":
                contentType = "text/stylesheet";
                break;
            case ".js":
                contentType = "text/javascript";
                break;
            case ".jpg":
                contentType = "image/jpeg";
                break;
            case ".jpeg":
            case ".png":
            case ".gif":
                contentType = "image/" + extension.substring(1);
                break;
            default:
                if (extension.length() > 1)
                {
                    contentType = "application/" + extension.substring(1);
                }
                else
                {
                    contentType = "application/unknown";
                }
                break; 
        }
        return contentType;
    }
}
