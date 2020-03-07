package com.java.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

import com.java.util.JDBCConnection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.*;
/**
 * Servlet implementation class MyContoller
 */
@WebServlet("/MyContoller")
public class MyContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyContoller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JasperPrint jasperPrint=null;
		try {
		String reportFileName="Blank_A4.jrxml";
		String jasperFileName = "Blank_A4.jasper";
		
		//String reportPath="C:\\Users\\Shreya\\M\\M\\WebContent\\"+reportFileName;  used for jrxml
		String reportPath="C:\\Users\\Shreya\\M\\M\\WebContent\\"+jasperFileName;
		System.out.println(reportPath);
		//String targetFileName=reportFileName.replace(".jrxml", ".pdf");  used for jrxml
		//String targetFileName=reportFileName.replace(".jasper", ".pdf");
		String targetFileName=jasperFileName.replace(".jasper", ".pdf");// jasperFileName
		JasperReport jasperReport  = null;
		

		   // Export pdf file
		   //JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);
		try
		{
			// jasperReport = JasperCompileManager.compileReport(reportPath); // to compile jrxml
			jasperReport = (JasperReport)JRLoader.loadObjectFromFile(reportPath);
		}
		catch (Exception e) {
			System.out.println(e);
			}
		
		Connection con  = JDBCConnection.getJDBCConnection();
		HashMap<String, Object> m = new HashMap();
		m.put("My", "emp");
	 	// jasperPrint = JasperFillManager.fillReport(jasperReport,m, con); // to compile jrxml use this code */
		jasperPrint  = (JasperPrint) JasperFillManager.fillReport(jasperReport, m, con);// coomecnted not using to fill now
		ServletOutputStream outputstream = response.getOutputStream();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
		response.setContentType("application/pdf");	
		outputstream.write(byteArrayOutputStream.toByteArray());
		response.setHeader("Cache-Control", "max-age=0");
		response.setHeader("Content-Disposition", "attachment; filename=" + targetFileName); 
		outputstream.flush();
		outputstream.close(); 
		con.close();
		
		} catch (Exception e) 
		{
		System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
	}

}
