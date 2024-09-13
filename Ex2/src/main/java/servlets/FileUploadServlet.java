package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Servlet implementation class FileUploadServlet
 */
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, 
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024 * 10)

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String uploadPath = null;
	
	
    public FileUploadServlet() {
        super();
    }

    @Override
	public void init() throws ServletException {
		super.init();
		uploadPath = getServletContext().getInitParameter("upload.path");
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			boolean hasFile = false;
			for (Part filePart : request.getParts()) {
			    if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
			        hasFile = true;
			        String fileName = filePart.getSubmittedFileName();
			        InputStream inputStream = filePart.getInputStream();
			        Files.copy(inputStream, Paths.get(uploadPath + File.separator + fileName),
			                StandardCopyOption.REPLACE_EXISTING);
			        System.out.println(uploadPath + File.separator + fileName);
			    }
			}
			if (hasFile) {
			    response.getWriter().println("Upload thành công!");
			} else {
			    response.getWriter().println("Không có file được chọn để tải lên.");
			}

		} catch (IOException | ServletException e) {
			response.getWriter().println("Tải tệp lên không thành công do có lỗi: " + e.getMessage());
		}
	}
}
