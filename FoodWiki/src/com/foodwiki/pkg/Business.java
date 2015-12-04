package com.foodwiki.pkg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	static Model _twitter = ModelFactory.createOntologyModel();
	static Model _yelp = ModelFactory.createOntologyModel();
	static Model _food = ModelFactory.createOntologyModel();
	
	Map result = new HashMap();
	
	public Map getResult(String search) throws Exception{
		populateFood();
		populateTwitter();
		populateYelp();
		formFoodQuery(_food, search);
		formTwittQuery(_twitter, search);
		formYelpQuery(_yelp, search);
		return result;
	}

	public void populateFood() throws IOException
	{
		InputStream inInstance = FileManager.get().open("//food.rdf");
		_food.read(inInstance, nameSpace) ;	
	}

	public void populateTwitter() throws IOException
	{
		InputStream inInstance = FileManager.get().open("//twitterrdf.rdf");
		_twitter.read(inInstance, nameSpace) ;
	}
	
	public void populateYelp() throws IOException
	{
		InputStream inInstance = FileManager.get().open("//yelprdf.rdf");
		_yelp.read(inInstance, nameSpace) ;
	}

	public void formFoodQuery(Model model, String search)
	{
		/*String query = "select ?IngredientListAsText ?carbohydratesPer100g ?vitaminCPer100g ?nutritionScoreUkPer100g ?transFatPer100g ?calciumPer100g ?vitaminAPer100g "
				+ "?saltPer100g ?ironPer100g ?sugarsPer100g ?saturatedFatPer100g ?fiberPer100g ?energyPer100g ?nutritionScoreFrPer100g ?proteinsPer100g ?fatPer100g ?sodiumPer100g ?cholesterolPer100g where {?about a ?type . ?about food:IngredientListAsText ?IngredientListAsText. ?about food:cholesterolPer100g ?cholesterolPer100g.  ?about food:sodiumPer100g ?sodiumPer100g. ?about food:fatPer100g ?fatPer100g. ?about food:proteinsPer100g ?proteinsPer100g. "
				+ "?about food:nutritionScoreFrPer100g ?nutritionScoreFrPer100g. ?about food:energyPer100g ?energyPer100g. ?about food:fiberPer100g ?fiberPer100g. ?about food:saturatedFatPer100g ?saturatedFatPer100g. "
				+ "?about food:saturatedFatPer100g ?saturatedFatPer100g. ?about food:energyFromFatPer100g ?energyFromFatPer100g. ?about food:sugarsPer100g ?sugarsPer100g. ?about food:vitaminAPer100g ?vitaminAPer100g.  ?about food:saltPer100g ?saltPer100g. "
				+ "?about food:ironPer100g ?ironPer100g. ?about food:nutritionScoreUkPer100g ?nutritionScoreUkPer100g.  ?about food:transFatPer100g ?transFatPer100g.  ?about food:calciumPer100g ?calciumPer100g. ?about food:vitaminCPer100g ?vitaminCPer100g. ?about food:carbohydratesPer100g ?carbohydratesPer100g. "+ "	FILTER(str(?about) =" + search +")}";
		*/
	/*	String carbohydrate = "ASK {?about a ?type. ?about food:carbohydratesPer100g ?carbohydratesPer100g. FILTER(str(?about) =" + search + ")}";
		StringBuffer queryStr = new StringBuffer();
		// Establish Prefixes
		queryStr.append("PREFIX food" + ": <" + nameSpace + "> ");
		queryStr.append("PREFIX rdfs" + ": <" + "http://www.w3.org/2000/01/rdf-schema#" + "> ");
		queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");
		// Now add query
		queryStr.append(carbohydrate);
		Query query = QueryFactory.create(queryStr.toString());
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		Boolean response = qexec.execAsk();
		System.out.println(response);
		*/
		String query = "select ?IngredientListAsText ?carbohydratesPer100g"
				+ " where {?about a ?type."
				+ "OPTIONAL {?about food:IngredientListAsText ?IngredientListAsText}"
				+ "OPTIONAL {?about food:carbohydratesPer100g ?carbohydratesPer100g}"
				+ "OPTIONAL {?about food:vitaminCPer100g ?vitaminCPer100g}"
				+ "OPTIONAL {?about food:nutritionScoreUkPer100g ?nutritionScoreUkPer100g}"
				+ "OPTIONAL {?about food:transFatPer100g ?transFatPer100g}"
				+ "OPTIONAL {?about food:calciumPer100g ?calciumPer100g}"
				+ "OPTIONAL {?about food:vitaminAPer100g ?vitaminAPer100g}"
				+ "OPTIONAL {?about food:saltPer100g ?saltPer100g}"
				+ "OPTIONAL {?about food:ironPer100g ?ironPer100g}"
				+ "OPTIONAL {?about food:sugarsPer100g ?sugarsPer100g}"
				+ "OPTIONAL {?about food:saturatedFatPer100g ?saturatedFatPer100g}"
				+ "OPTIONAL {?about food:fiberPer100g ?fiberPer100g}"
				+ "OPTIONAL {?about food:energyPer100g ?energyPer100g}"
				+ "OPTIONAL {?about food:nutritionScoreFrPer100g ?nutritionScoreFrPer100g}"
				+ "OPTIONAL {?about food:proteinsPer100g ?proteinsPer100g}"
				+ "OPTIONAL {?about food:fatPer100g ?fatPer100g}"
				+ "OPTIONAL {?about food:sodiumPer100g ?sodiumPer100g}"
				+ "OPTIONAL {?about food:cholesterolPer100g ?cholesterolPer100g}"
				+ " FILTER(str(?about) =" + search +")}";
		runFoodQuer(query, model);
	}
	
	public void formTwittQuery(Model model, String search)
	{
		String query = "select ?about ?status ?userName ?screenname ?userlocation where {?about a ?type. ?about twitter:mentionedIn ?tweet. ?tweet twitter:status ?status. ?tweet twitter:hasUser ?user. ?user twitter:name ?userName. ?user twitter:screenname ?screenname. ?user twitter:location ?location. " + "	FILTER(str(?about) =" + search + ")}";
		runTwittQuer(query, model); 							
	}
	public void formYelpQuery(Model model, String search)
	{
		String query = "select ?about ?businessname ?rating ?location where {?about a ?type. ?about yelp:mentionedIn ?business. ?business yelp:name ?businessname. ?business yelp:rating ?rating. ?business yelp:location ?location. " + "	FILTER(str(?about) =" + search + ")}";
		runYelpQuer(query, model); 															
	}

	public void runFoodQuer(String queryRequest, Model model)
	{
		StringBuffer queryStr = new StringBuffer();
		// Establish Prefixes
		queryStr.append("PREFIX food" + ": <" + nameSpace + "> ");
		queryStr.append("PREFIX rdfs" + ": <" + "http://www.w3.org/2000/01/rdf-schema#" + "> ");
		queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");
		// Now add query
		queryStr.append(queryRequest);
		Query query = QueryFactory.create(queryStr.toString());
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try
		{
			ResultSet response = qexec.execSelect();
			while (response.hasNext())
			{
				QuerySolution soln = response.nextSolution();
				result.put("ing", soln.get("?IngredientListAsText").toString());
				if(null != soln.get("?carbohydratesPer100g")){
					result.put("carbohydratesPer100g", soln.get("?carbohydratesPer100g").toString());
				}
				if(null != soln.get("?vitaminCPer100g")){
					result.put("vitaminCPer100g", soln.get("?vitaminCPer100g").toString());
				}
				if(null != soln.get("?nutritionScoreUkPer100g")){
					result.put("nutritionScoreUkPer100g", soln.get("?nutritionScoreUkPer100g").toString());
				}
				if(null != soln.get("?transFatPer100g")){
					result.put("transFatPer100g", soln.get("?transFatPer100g").toString());
				}
				if(null != soln.get("?calciumPer100g")){
					result.put("calciumPer100g", soln.get("?calciumPer100g").toString());
				}
				if(null != soln.get("?vitaminAPer100g")){
					result.put("vitaminAPer100g", soln.get("?vitaminAPer100g").toString());
				}
				if(null != soln.get("?saltPer100g")){
					result.put("saltPer100g", soln.get("?saltPer100g").toString());
				}
				if(null != soln.get("?ironPer100g")){
					result.put("ironPer100g", soln.get("?ironPer100g").toString());
				}
				if(null != soln.get("?sugarsPer100g")){
					result.put("sugarsPer100g", soln.get("?sugarsPer100g").toString());
				}
				if(null != soln.get("?saturatedFatPer100g")){
					result.put("saturatedFatPer100g", soln.get("?saturatedFatPer100g").toString());
				}
				if(null != soln.get("?fiberPer100g")){
					result.put("fiberPer100g", soln.get("?fiberPer100g").toString());
				}
				if(null != soln.get("?energyPer100g")){
					result.put("energyPer100g", soln.get("?energyPer100g").toString());
				}
				if(null != soln.get("?nutritionScoreFrPer100g")){
					result.put("nutritionScoreFrPer100g", soln.get("?nutritionScoreFrPer100g").toString());
				}
				if(null != soln.get("?proteinsPer100g")){
					result.put("proteinsPer100g", soln.get("?proteinsPer100g").toString());
				}
				if(null != soln.get("?fatPer100g")){
					result.put("fatPer100g", soln.get("?fatPer100g").toString());
				}
				if(null != soln.get("?sodiumPer100g")){
					result.put("sodiumPer100g", soln.get("?sodiumPer100g").toString());
				}
				if(null != soln.get("?cholesterolPer100g")){
					result.put("cholesterolPer100g", soln.get("?cholesterolPer100g").toString());
				}
			}
			
		}
		finally {
			qexec.close();
		}
		System.out.println("Finished Ingredients");
	}
	
	public void runTwittQuer(String queryRequest, Model model)
		{
			StringBuffer queryStr = new StringBuffer();
			// Establish Prefixes
			queryStr.append("PREFIX twitter" + ": <" + nameSpace + "> ");
			queryStr.append("PREFIX rdfs" + ": <" + "http://www.w3.org/2000/01/rdf-schema#" + "> ");
			queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");
			// Now add query
			queryStr.append(queryRequest);
			Query query = QueryFactory.create(queryStr.toString());
			QueryExecution qexec = QueryExecutionFactory.create(query, model);
			try
			{
				List statusList = new ArrayList();
				List userNameList = new ArrayList();
				List userLocationList = new ArrayList();
				List screenNameList = new ArrayList();
				ResultSet response = qexec.execSelect();
				while (response.hasNext())
				{
					QuerySolution soln = response.nextSolution();
					RDFNode status = soln.get("?status");
					RDFNode userName = soln.get("?userName");
					RDFNode userLocation = soln.get("?userlocation");
					RDFNode screenName = soln.get("?screenname");
					statusList.add(status.toString());
					userNameList.add(userName.toString());
					screenNameList.add(screenName.toString());
					if(null != userLocation){
						userLocationList.add(userLocation.toString());
					}
					//System.out.println(userName.toString()+"--"+status.toString());
				}
				result.put("status", statusList);
				result.put("screenNames", screenNameList);
				result.put("userLocation", userLocationList);
				result.put("userNames", userNameList);
			}
			finally {
				qexec.close();
			}
			System.out.println("Finished Twitter");
		}

		public void runYelpQuer(String queryRequest, Model model)
		{
			StringBuffer queryStr = new StringBuffer();
			// Establish Prefixes
			queryStr.append("PREFIX yelp" + ": <" + nameSpace + "> ");
			queryStr.append("PREFIX rdfs" + ": <" +	"http://www.w3.org/2000/01/rdf-schema#" + "> ");
			queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");
			// Now add query
			queryStr.append(queryRequest);
			Query query = QueryFactory.create(queryStr.toString());
			QueryExecution qexec = QueryExecutionFactory.create(query, model);
			try
			{
				List businessList = new ArrayList();
				List ratingsList = new ArrayList();
				List locationList = new ArrayList();
				ResultSet response = qexec.execSelect();
				while (response.hasNext())
				{
					QuerySolution soln = response.nextSolution();
					RDFNode businessname = soln.get("?businessname");
					RDFNode rating = soln.get("?rating");
					RDFNode location = soln.get("?location");
					businessList.add(businessname.toString());
					ratingsList.add(rating.toString());
					locationList.add(location.toString());
					
				}
				result.put("business", businessList);
				result.put("ratings", ratingsList);
				result.put("locations", locationList);
			}
			finally {
				qexec.close();
			}
			System.out.println("Finished Yelp");
		}

}
