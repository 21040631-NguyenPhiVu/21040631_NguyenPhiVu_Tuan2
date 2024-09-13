package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.ContactDao;
import dbConnection.DBConnection;
import entities.Contact;

/**
 * Servlet implementation class ServletFileUpload
 */
@MultipartConfig
public class ServletFileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 4096; // 4KB

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletFileUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		InputStream inputStream = null;
		Part filePart = request.getPart("photo");
		String fileUploadName = "";
		if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {

			fileUploadName = filePart.getSubmittedFileName();
			inputStream = filePart.getInputStream();
		}
		Connection conn = null;
		String message = null;
		String filePath = "D:/" + fileUploadName;
		try {
			conn = DBConnection.getConnection();
			String sqlInsert = "INSERT INTO contacts (first_name, last_name, photo) values (?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sqlInsert);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			if (inputStream != null) {
				statement.setBlob(3, inputStream);
			}
			int row = statement.executeUpdate();
			if (row > 0) {
				message = "File uploaded and saved into database";
			}
			String sqlSelect = "SELECT photo FROM contacts WHERE first_name=? AND last_name=?";
			statement = conn.prepareStatement(sqlSelect);
			statement.setString(1, firstName);

			statement.setString(2, lastName);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Blob blob = result.getBlob("photo");
				inputStream = blob.getBinaryStream();
				OutputStream outputStream = new FileOutputStream(filePath);
				int bytesRead = -1;
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
			}
		} catch (SQLException ex) {
			message = "ERROR: " + ex.getMessage();
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			request.setAttribute("Message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}
}
