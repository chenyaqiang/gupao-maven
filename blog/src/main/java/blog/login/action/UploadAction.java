package blog.login.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *  文件上传
 *	单、多文件上传
 * @author wangye
 *
 */
@Controller
@RequestMapping("/upload") 
public class UploadAction {
	@Autowired
	HttpServletRequest request;
	
	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	@RequestMapping("/uploadFile")
	public String uploadFile(
			@RequestParam(value = "file", required = false) MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String path=request.getSession().getServletContext().getRealPath("upload");
		saveFile(file, fileName, path);
		return "upload";
	}

	private void saveFile(MultipartFile file, String fileName, String path) {
		File file2 = new File(path,fileName);
		if (!file2.exists()) {
			file2.mkdirs();
		}
		try {
			file.transferTo(file2);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 传上多文件
	 * @param files
	 * @return
	 */
	@RequestMapping("/uploadFiles")
	public String uploadFiles(
			@RequestParam(value = "files") MultipartFile[] files) {
		String path=request.getSession().getServletContext().getRealPath("upload");
		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			saveFile(file, fileName, path);
		}
		return "upload";
	}
	
	
	@RequestMapping("/toUpload")
	public String toUpload() {
		return "upload";
	}

}
