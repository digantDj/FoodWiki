package com.foodwiki.pkg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet(description = "The default Home Page for FoodWIki", urlPatterns = { "/Home" })
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//PrintWriter out = response.getWriter().append("Served at: ").append(request.getContextPath());
		String element_1_value = request.getParameter("search") ;
		PrintWriter out = response.getWriter();
		String aboutString ="";
		BufferedReader br = new BufferedReader(new FileReader(GetFoodNames.class.getClassLoader().getResource("foodnames.txt").getPath()));
		try {
		    String line = br.readLine();
		    while (line != null) {	
		    	
		        if(line.split(" rdf:about=")[0].equals(element_1_value)){
		        	aboutString = line.split(" rdf:about=")[1];
		        	break;
		        }
		        line = br.readLine();        
		    }		    
		} finally {
		    br.close();
		}	
		out.print("<b>"+aboutString+"</b>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
