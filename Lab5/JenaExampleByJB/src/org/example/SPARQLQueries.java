package org.example;

public class SPARQLQueries {
	
	
	
    public static String prefixes = 	"PREFIX jbo: <http://a.com/ontology#> " +
            							"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
    		
    public static String myQuery = 		prefixes + "SELECT ?x WHERE { " +
										"	?x jbo:hasName ?y . " +
										"}";
    
	public static String Woman = 		prefixes + 
										"SELECT ?x WHERE { " +
							    		"    ?x rdf:type jbo:Woman . " +
							    		"}";
	
	
	public static String Man = 			prefixes + 
										"SELECT ?x WHERE { " +
							    		"    ?x rdf:type jbo:Man . " +
							    		"}";


	public static String Person = 		prefixes + 
										"SELECT ?x WHERE { " +
							    		"    ?x rdf:type jbo:Person . " +
							    		"}";

	

	public static String Father =  		prefixes + 
										"SELECT ?x WHERE { " +
							    		"    ?x rdf:type jbo:Father . " +
							    		"}";

	

	public static String Mother = 		prefixes + 
										"SELECT ?x WHERE { " +
							    		"    ?x rdf:type jbo:Mother . " +
							    		"}";

	

	public static String Parent = 		prefixes + 
										"SELECT ?x WHERE { " +
							    		"    ?x rdf:type jbo:Parent . " +
							    		"}";


	public static String Child = 		prefixes + 
										"SELECT ?x WHERE { " +
							    		"    ?x rdf:type jbo:Child . " +
							    		"}";
	
	
	public static String hasSibling = 	prefixes + 
										"SELECT ?x ?y WHERE { " +
							    		"    ?x jbo:hasSibling ?y . " +
							    		"}";
	
	
	
	
	//TODO
	//public static String myQuery = 		prefixes + "TODO";

}
