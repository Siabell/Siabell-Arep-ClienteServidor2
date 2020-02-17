package edu.escuelaing.arep;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

public class ConnectionDB {
	/*
	private static Connection getConnection() throws URISyntaxException, SQLException {
	    String dbUrl = System.getenv("JDBC_DATABASE_URL");
	    return (Connection) DriverManager.getConnection(dbUrl);
	}*/
	/*
	private static Connection getConnection() throws URISyntaxException, SQLException {
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}*/
	private static final String URL = "jdbc:postgresql://ec2-3-213-192-58.compute-1.amazonaws.com/d61udmp8b1ccs8";
	private static final String HOST ="ec2-3-213-192-58.compute-1.amazonaws.com";
	private static final String USERNAME = "sugfdzfiiqylrg";
	private static final String PASSWORD ="185ad0fd2f06ae46426e77c8f8c03fbad32114e036b783cf828c63b7df67af79";
	
	static final String DEFAULT_FILE = "index.html";
	static final File WEB_ROOT = new File(System.getProperty("user.dir") + "/src/main/resources");
	static final String FILE_NOT_FOUND = "404.html";
    static final String METHOD_NOT_SUPPORTED = "notSupported.html";
    static final String JPGIMAGE = "image.jpg";
    static final String PNGIMAGE = "image.png";
    static Connection connection = null;

	public ConnectionDB() {}
	
	private static Connection getConnection() throws URISyntaxException, SQLException {
	    try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver error");
		}
	    Connection conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
	    return conn;
	}
	
	
	
	public static void  connet() throws SQLException{
		try {
			connection = getConnection();
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.out.println("Connection failed");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (connection!= null) {
			System.out.println("you made it");
		}
	}

	public static ArrayList<String> getBooks() {
		
		String SQL = "select * from books";
		String[] res = null;
		PreparedStatement pst;
		ArrayList<String> autor = new ArrayList<String>();
		ArrayList<String> book = new ArrayList<String>();
		try {
			pst = connection.prepareStatement("SELECT * FROM books");
			ResultSet rs = pst.executeQuery();
			
		    while (rs.next()) {
		    	autor.add(rs.getString(3));
		    	book.add(rs.getString(2));
		        System.out.print(rs.getInt(1));
		        System.out.print(": ");
		        System.out.println(rs.getString(2));
		        System.out.print(": ");
		        System.out.println(rs.getString(3));
		    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return book;
		
		
	}
}
