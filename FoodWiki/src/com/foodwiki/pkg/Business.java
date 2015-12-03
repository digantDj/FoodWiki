package com.foodwiki.pkg;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;

public class Business {

	static String nameSpace = "http://localhost/foodOntology.owl";

	Model _twitter = null;
	
	Model _food = null;

	Model schema = null;
	
	Map result = new HashMap();
	
	public Map getResult(String search) throws Exception{
		populateFood();
		populateTwitter();
		formFoodQuery(_food, search);
		formTwittQuery(_twitter, search);
		return result;
	}

	public void populateFood() throws IOException
	{
		_food = ModelFactory.createOntologyModel();

		InputStream inFoafInstance =

		FileManager.get().open(System.getProperty("user.dir")+"/FoodWiki/src/com/foodwiki/pkg/foodproducts.rdf");

		_food.read(inFoafInstance, nameSpace);

		inFoafInstance.close();

	}

	public void populateTwitter() throws IOException
	{
		_twitter = ModelFactory.createOntologyModel();
		InputStream inFoafInstance =
		FileManager.get().open(System.getProperty("user.dir")+"/FoodWiki/src/com/foodwiki/pkg/twitterrdf.rdf");
		_twitter.read(inFoafInstance, nameSpace);
		inFoafInstance.close();
	}

	public void formFoodQuery(Model model, String search)
	{
		String about = "http://world-en.openfoodfacts.org/product/0059749881043/popping-corn-mais-a-eclater-au-micro-onde-irresistibles";
		String query = "select ?name where {?about a ?type . ?about food:containsIngredient ?ing . ?ing food:food ?name ."+ "	FILTER(str(?about) = \"" + search + "\")}";
		runFoodQuer(query, model);
	}
	
	public void formTwittQuery(Model model, String search)
	{
		String query = "select ?about ?status where {?about a ?type. ?about twitter:mentionedIn ?tweet. ?tweet twitter:status ?status." + "	FILTER(str(?about) = \"" + search + "\")}";
		runTwittQuer(query, model); 
																
	}

	public void runTwittQuer(String queryRequest, Model model)
	{
		StringBuffer queryStr = new StringBuffer();

		// Establish Prefixes

		queryStr.append("PREFIX twitter" + ": <" + nameSpace + "> ");

		queryStr.append("PREFIX rdfs" + ": <" +

		"http://www.w3.org/2000/01/rdf-schema#" + "> ");

		queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");

		// Now add query

		queryStr.append(queryRequest);

		Query query = QueryFactory.create(queryStr.toString());

		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		try

		{
			List aboutList = new ArrayList();
			List statusList = new ArrayList();
			ResultSet response = qexec.execSelect();

			while (response.hasNext())

			{
				QuerySolution soln = response.nextSolution();
				RDFNode about = soln.get("?about");
				RDFNode status = soln.get("?status");
				aboutList.add(about.toString());
				statusList.add(status.toString());

			}
			result.put("about", aboutList);
			result.put("status", statusList);

		}
		finally {
			qexec.close();
		}
		System.out.println("Finished");
	}

	public void runFoodQuer(String queryRequest, Model model)
	{
		StringBuffer queryStr = new StringBuffer();
		// Establish Prefixes
		queryStr.append("PREFIX food" + ": <" + nameSpace + "> ");
		queryStr.append("PREFIX rdfs" + ": <" +
		"http://www.w3.org/2000/01/rdf-schema#" + "> ");
		queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");
		// Now add query
		queryStr.append(queryRequest);
		Query query = QueryFactory.create(queryStr.toString());
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try
		{
			List ingList = new ArrayList();
			ResultSet response = qexec.execSelect();
			while (response.hasNext())
			{
				QuerySolution soln = response.nextSolution();
				RDFNode ingName = soln.get("?name");
				ingList.add(ingName.toString());
			}
			result.put("ing", ingList);
		}
		finally {
			qexec.close();
		}
		System.out.println("Finished");
	}
}
