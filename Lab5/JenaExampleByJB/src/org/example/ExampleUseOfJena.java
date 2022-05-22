/**
 * Author Jaros³aw B¹k
 */

package org.example;

import java.util.Iterator;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.reasoner.ValidityReport.Report;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;

public class ExampleUseOfJena {
	
	/**
	 * Constructor
	 */
	public ExampleUseOfJena() {}
	
	
	/**
	 * Prints triples in the accordance of a given model
	 * @param m
	 * @param s
	 * @param p
	 * @param o
	 */
	public void printStatements(Model m, Resource s, Property p, Resource o) {
	    for (StmtIterator i = m.listStatements(s,p,o); i.hasNext(); ) {
	        Statement stmt = i.nextStatement();
	        System.out.println(" - " + PrintUtil.print(stmt));
	    }
	}
	
	
	/**
	 * Creates inference model from a model
	 * @param model
	 * @return
	 */
	public Model createOWLInferenceModel(Model model)
	{
		Reasoner reasoner = ReasonerRegistry.getOWLMiniReasoner();
		reasoner = reasoner.bindSchema(model);
		InfModel infmodel = ModelFactory.createInfModel(reasoner, model);
		return infmodel;
	}
	
	
	/**
	 * Adds a resource to a given model
	 * @param model
	 * @param subject
	 * @param predicate
	 * @param object
	 */
	public void addTripleToModel(Model model, String subject, String predicate, String object)
	{
		String namespace = "http://a.com/ontology#";
		Resource _subject = ResourceFactory.createResource(namespace + subject);
		Property _predicate = ResourceFactory.createProperty(namespace + predicate);
		Resource _object = ResourceFactory.createResource(namespace + object);
		Statement s = ResourceFactory.createStatement(_subject, _predicate, _object);
		model.add(s);
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println("Adding triple to model. \n");
	}
	
	
	/**
	 * Removes triple from a given Model
	 * @param model
	 * @param subject
	 * @param predicate
	 * @param object
	 */
	public void removeTripleFromModel(Model model, String subject, String predicate, String object)
	{
		String namespace = "http://a.com/ontology#";
		Resource _subject = ResourceFactory.createResource(namespace + subject);
		Property _predicate = ResourceFactory.createProperty(namespace + predicate);
		Resource _object = ResourceFactory.createResource(namespace + object);
		Statement s = ResourceFactory.createStatement(_subject, _predicate, _object);
		model.remove(s);
	}
	
	
	/**
	 * Reads an ontology file
	 * @param filename
	 * @return
	 */
	public Model readOntology(String filename)
	{
		Model model = null;
		model = RDFDataMgr.loadModel(filename);
		return model;
	}
	
	
	/**
	 * Presents inference results based on a Person class
	 * @param model
	 * @param infModel
	 */
	public void checkModelsCorrectness(Model model, Model infModel)
	{
		String namespace = "http://a.com/ontology#";
		
		Resource person = model.getResource(namespace + "Person");
		Resource infPerson = infModel.getResource(namespace + "Person");
		
		String pause = "=================================================================================================";
		
		System.out.println();
		
		System.out.println("Normal Results 1:");
		printStatements(model, person, null, null);
		System.out.println("Normal Results 2:");
		printStatements(model, null, null, person);
		
		System.out.println(pause);
		
		
		System.out.println("Inference Results 1:");
		printStatements(infModel, infPerson, null, null);
		System.out.println("Inference Results 2:");
		printStatements(infModel, null, null, infPerson);
		
		System.out.println(pause);
	}
	
	
	/**
	 * Prints results using {@link org.apache.jena.query.ResultSetFormatter}
	 * @param results
	 */
	public void printResults(ResultSet results)
	{
		 ResultSetFormatter.out(System.out, results);
	     System.out.println("Number of results: " + results.getRowNumber());
	}
	
	
	/**
	 * Executes a SPARQL query on a given Jena model
	 * @param model
	 * @param queryString
	 */
	public void executeSPARQLQuery(Model model, String queryString)
	{
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println("Results of the following SPARQL query: \n" + queryString);
        
		Query query = QueryFactory.create(queryString);
		try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
		    ResultSet results = qexec.execSelect();
		    //printResults(results);
		    //System.out.println("-------------------------------------------------------------------------------------------------");
		    executeSPARQLQueryAndPrintAsJSON(results);
		}
		
		//TODO		
		// Query query = ;
        // QueryExecution qexec = 
		// use printResults(ResultSet results) in order to print the results of a query
	}
	
	
	/**
	 * Executes a SPARQL query on a FUSEKI database using the HTTP protocol
	 * @param serviceURI
	 * @param queryString
	 */
	public void executeSPARQLQueryOnFuseki(String serviceURI, String queryString)
	{
		//TODO
		QueryExecution qe = QueryExecutionFactory.sparqlService(serviceURI, queryString);
	    ResultSet results = qe.execSelect();
	    printResults(results);
	}
	
	
	/**
	 * Uploads RDF data (Model) to a given HTTP service
	 * @param serviceURI
	 * @param model
	 */
	public void uploadRDFDataToFuseki(String serviceURI, Model model)
	{
		DatasetAccessor accessor = DatasetAccessorFactory.createHTTP(serviceURI);
		accessor.putModel(model);
		
		//TODO upload the model
	}
	
	
	/**
	 * Executes SPARQL query and prints results as JSON
	 * It may only translate SPARQL results into JSON
	 */
	public void executeSPARQLQueryAndPrintAsJSON(ResultSet results)
	{
		ResultSetFormatter.outputAsJSON(results);
	}

	
	/**
	 * Validates inference model
	 * @param model
	 */
	public void validateModel(InfModel model)
	{
		ValidityReport validityReport = model.validate(); 

	    if ( !validityReport.isValid() ) 
	    {
	       	System.out.println("Inconsistent");
	        Iterator<Report> iter = validityReport.getReports();
	        while ( iter.hasNext() ) 
	        {
	          	Report report = iter.next();
	            System.out.println(report);
	        }        	
	    } else 
	    {
	        	System.out.println("Valid");
	    }
	}
	
	
	/**
	 * Main Method!
	 * @param args
	 */
    public static void main(String[] args) 
    {
    	ExampleUseOfJena ex = new ExampleUseOfJena();
    	
    	Model model = ex.readOntology("./familyOntology/family.swrl.JB_2012_dodane_noSWRL.owl");
    	Model infModel = ex.createOWLInferenceModel(model);
    	ex.checkModelsCorrectness(model, infModel);
    	
    	
    	
    	//Execute SPARQL queries on each model
    	ex.executeSPARQLQuery(model, SPARQLQueries.Woman);
    	ex.executeSPARQLQuery(model, SPARQLQueries.Man);
    	ex.executeSPARQLQuery(model, SPARQLQueries.Person);
    	ex.executeSPARQLQuery(model, SPARQLQueries.Mother);
    	ex.executeSPARQLQuery(model, SPARQLQueries.Father);
    	ex.executeSPARQLQuery(model, SPARQLQueries.Child);
    	ex.executeSPARQLQuery(model, SPARQLQueries.Parent);
    	ex.executeSPARQLQuery(model, SPARQLQueries.hasSibling);
    	
    	
    	System.out.println("\n\n=====   INFMODEL QUERY RESULTS   =====\n\n");
    	
    	ex.executeSPARQLQuery(infModel, SPARQLQueries.Woman);
    	ex.executeSPARQLQuery(infModel, SPARQLQueries.Man);
    	ex.executeSPARQLQuery(infModel, SPARQLQueries.Person);
    	ex.executeSPARQLQuery(infModel, SPARQLQueries.Mother);
    	ex.executeSPARQLQuery(infModel, SPARQLQueries.Father);
    	ex.executeSPARQLQuery(infModel, SPARQLQueries.Child);
    	ex.executeSPARQLQuery(infModel, SPARQLQueries.Parent);
    	ex.executeSPARQLQuery(infModel, SPARQLQueries.hasSibling);
    
    	System.out.println("\n\n=====   JSON RESULT FROM FUSEKI   =====\n\n");
    	//TODO - wykonane w funkcji query.
    	//ex.executeSPARQLQueryAndPrintAsJSON(results);
    	
    	
    	//zadanie 4 & zadanie 7
    	System.out.println("\n\n=====   TESTING MY OWN QUERY   =====\n\n");
    	ex.addTripleToModel(infModel, "SZFEL", "hasName", "Szymon");
    	System.out.println("\n\n=====   ADDED:    =====\n\n");
    	ex.executeSPARQLQuery(infModel, SPARQLQueries.myQuery);
    	ex.removeTripleFromModel(infModel, "SZFEL", "hasName", "Szymon");
    	System.out.println("\n\n=====   REMOVED:   =====\n\n");
    	ex.executeSPARQLQuery(infModel, SPARQLQueries.myQuery);
    	
    	
    	
    	System.out.println("\n\n=====   TESTING FUSEKI   =====\n\n");
    	//zadanie 6
    	ex.uploadRDFDataToFuseki("http://localhost:3030/family", infModel); //start fuseki and create family dataset
      
	    ex.executeSPARQLQueryOnFuseki("http://localhost:3030/family", SPARQLQueries.Person);   
	    ex.executeSPARQLQueryOnFuseki("http://localhost:3030/family", SPARQLQueries.Parent);
	    ex.executeSPARQLQueryOnFuseki("http://localhost:3030/family", SPARQLQueries.Child);
	    ex.executeSPARQLQueryOnFuseki("http://localhost:3030/family", SPARQLQueries.hasSibling);
	    
	    
    }

}
