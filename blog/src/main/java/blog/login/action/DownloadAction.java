package blog.login.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/download") 
public class DownloadAction {
	
	
    @RequestMapping(value = "downloadFile")  
    public String download(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
  
        String realName = "¹þ¹þ.txt";  
        String contentType = "application/octet-stream";  
  
        download(request, response, contentType,  
                realName);  
  
        return null;  
    }  
    
    @RequestMapping(value = "toDownload")  
    public String toDownload(){
    	return "download";
    }
    
	public static void download(HttpServletRequest request,
			HttpServletResponse response, String contentType,
			String realName) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String ctxPath = request.getSession().getServletContext()
				.getRealPath("upload");

		String downLoadPath = ctxPath +"\\"+ realName;

		long fileLength = new File(downLoadPath).length();

		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(realName.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}
}
