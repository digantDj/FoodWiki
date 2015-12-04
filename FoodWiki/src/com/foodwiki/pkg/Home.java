package com.foodwiki.pkg;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*Model _twitter = null;
	
	Model _food = null;	
	*/
	    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
    	
        super();
        /*_food = ModelFactory.createOntologyModel();
		_twitter = ModelFactory.createOntologyModel();*/
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String element_1_value = request.getParameter("search") ;
		ServletContext context = request.getSession().getServletContext();
		//PrintWriter out = response.getWriter();

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
		//out.print("<b>"+aboutString+"</b>");
		System.out.println(aboutString);
        Business business = new Business();

       // business.populateFood();

       Map result = null;
		try {
			result = business.getResult(aboutString);
			request.setAttribute("result",result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/data.jsp");
		dispatcher.forward(request,response);     
		/*String output = element_1_value;
		out.print(output);
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
