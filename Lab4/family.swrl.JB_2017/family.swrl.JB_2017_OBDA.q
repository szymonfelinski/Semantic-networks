[QueryItem="zadQ1-man"]
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX onto: <http://a.com/ontology#>
SELECT ?man
	WHERE { 
?man a onto:Man .

}
[QueryItem="zadQ1-female"]
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX onto: <http://a.com/ontology#>
SELECT ?female
	WHERE { 
?female a onto:Woman .

}
[QueryItem="zadQ1-person"]
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX onto: <http://a.com/ontology#>
SELECT ?person
	WHERE { 
?person a onto:Person .

}
[QueryItem="zadQ2"]
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX onto: <http://a.com/ontology#>
SELECT ?child ?parent
	WHERE { 
?child onto:hasParent ?parent .

}
[QueryItem="zadQ3-sibling"]
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX onto: <http://a.com/ontology#>
SELECT ?subject ?sibling
	WHERE { 

?subject onto:hasSibling ?sibling .

}
[QueryItem="zadQ3-sister"]
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX onto: <http://a.com/ontology#>
SELECT ?subject ?sister
	WHERE { 

?subject onto:hasSister ?sister .

}
[QueryItem="zadQ3-brother"]
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX onto: <http://a.com/ontology#>
SELECT ?subject ?brother
	WHERE { 

?subject onto:hasBrother ?brother .

}
[QueryItem="zadQ4"]
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX onto: <http://a.com/ontology#>
SELECT ?childname ?parentname
	WHERE { 

?child onto:hasParent ?parent .
?child onto:hasName ?childname .
?parent onto:hasName ?parentname .

}
[QueryItem="zadQ5"]
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX onto: <http://a.com/ontology#>
SELECT ?parentname ?childname ?siblingname ?childsex
	WHERE { 
?parent onto:hasChild ?child .
?child onto:hasName ?childname .
?parent onto:hasName ?parentname .
?child onto:hasSex ?childsex .
?child onto:hasSibling ?sibling .
?sibling onto:hasName ?siblingname .
?sibling onto:hasSex ?childsex .
}
[QueryItem="zadQ6"]
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX onto: <http://a.com/ontology#>
SELECT ?grandchild ?grandchildname ?grandfather ?grandfathername
	WHERE { 
?grandchild onto:hasGrandfather ?grandfather .
?grandchild onto:hasName ?grandchildname .
?grandfather onto:hasName ?grandfathername .
}
[QueryItem="zadQ5-1"]
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX onto: <http://a.com/ontology#>
SELECT ?parentname ?childname ?sex
	WHERE { 

?parent onto:hasChild ?child .
?child onto:hasName ?childname .
?parent onto:hasName ?parentname .
?child rdf:type ?sex .
?sex

}