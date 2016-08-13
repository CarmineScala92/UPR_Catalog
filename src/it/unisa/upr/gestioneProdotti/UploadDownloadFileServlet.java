/*
 * ©2013-2014 UPR-Catalog - Gruppo 3 - Ingegneria del software
 */

package it.unisa.upr.gestioneProdotti;

import it.unisa.upr.data.gestioneAutenticazione.Account;
import it.unisa.upr.data.gestioneAutenticazione.Amministratore;
import it.unisa.upr.data.gestioneAutenticazione.Ricercatore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet si occupa di gestire il download dei file associati ai prodotti
 * 
 */
@WebServlet(name = "UploadFileServlet",
		urlPatterns = { "/UploadDownloadFileServlet" })
@MultipartConfig
public class UploadDownloadFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!request.getParameterNames().hasMoreElements()) {
			RequestDispatcher rd = request.getRequestDispatcher("./Upload.jsp");
			rd.forward(request, response);
		}
		else {
			Ricercatore ric = (Ricercatore) request.getSession().getAttribute("Ricercatore");
			Amministratore amm=(Amministratore) request.getSession().getAttribute("Amministratore");
			if (ric != null || amm!=null) {
				String fileName = request.getParameter("fileName");
				if (fileName == null || fileName.equals("")) {
					throw new ServletException("File Name can't be null or empty");
				}
				File file = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + fileName);
				if (!file.exists()) {
					throw new ServletException("File doesn't exists on server.");
				}
				// System.out.println("File location on server::" +
				// file.getAbsolutePath());
				ServletContext ctx = getServletContext();
				InputStream fis = new FileInputStream(file);
				String mimeType = ctx.getMimeType(file.getAbsolutePath());
				response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + fileName + "\"");

				ServletOutputStream os = response.getOutputStream();
				byte[] bufferData = new byte[1024];
				int read = 0;
				while ((read = fis.read(bufferData)) != -1) {
					os.write(bufferData, 0, read);
				}
				os.flush();
				os.close();
				fis.close();
				System.out.println("File downloaded at client successfully");
			}
		}
	}
}
