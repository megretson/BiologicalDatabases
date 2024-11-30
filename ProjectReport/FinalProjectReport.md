
# Introduction: 
'''
Define the high-level objectives or purpose of your system. Describe its scope, and what activities and questions users can do/ask when using your database. What theme/issue are you addressing? (Note: you do need to be addressing some type of theme or issue in your project. Ask me if you have trouble identifying one. You may well have one without realizing it.) This may simply be a repeat from your project proposal depending on how detailed that is.
'''


PDB is the one of the largest repositories of molecular information on biological macromolecules, with a particular emphasis on proteins. PDB maintains both the atomic coordinates of proteins, and information on the experimental methods used to discover those coordinates. Experimental methods have radically changed since the inception of the PDB in 1971. This has lead to changes in the storage schema of proteins within PDB over time. 

One such change was the protein versioning system, announced in 2017. As protein spectroscopy and microscopy methods have improved, it has oftentimes been necessary to revise the original structure of proteins in the PDB.  Changes to the atomic coordinates, polymer sequence(s), and/or chemical identify of a ligand of a protein all constitute major revisions to the protein's entry. This version system allows a new set of atomic coordinates to be released for a protein while maintaining a link to the previous revision. 

Prior to this versioning system, major releases of proteins were released under new accession numbers. The previous version was then labeled obsolete. This system was not ideal, because older publications ended up with broken links to accession numbers which were now obsolete. This updated system allowed older publications to continue to link to the same accession number. However, the drawback of this system is users cannot easily tell which version of a protein the publication references. 

The purpose of this project is to create a link between a publication and the version of the protein they cited in their work. In this project, I have created a tool that links a publication with the protein version cited through a web-based API linked to a SQL database of protein version citation entries. My API addresses four major user needs. First, a user can understand the publications which have cited a particular version of a protein. Second, a user can see which version a publication cited. And third, a user can create new linkages between citations and their versions. Finally, the user can also attempt to notify authors if a new protein version is released. 

In service of these goals, I have also created a web scraper that queries the CrossRef API for mentions of a given protein (both by PDB ID and common name) and then predicts the version of the protein referenced based on publication date. I have initially populated my database using a set of PDB ids and my web scraper. 


# Related work
```
 briefly describe several other projects that are in an area related to your project objective/purpose, technology, and/or theme. Explain how these other projects are similar/different. The purpose of this section is to set your work in a larger context.

Do NOT use an AI program for section 2.
```

Finding prior art for this project was difficult. Much of my work was drawn from the original PDB versioning system. This versioning system was introduced to fix linkages between older versions of proteins and the publications which referenced them. The versioning system was introduced because it is insufficient to maintain only the latest release of a protein, as other work will continue to reference the older versions. 

However, as a computer scientist, I found it surprising this versioning system was only introduced in 2017. It is critical to track which software is in use on a system, particularly when there are frequent releases which may be incompatible with previous software revisions. Software versioning is so ubiquitous now that PDB chose to adopt semantic versioning as their version schema; a version system which originated from software development. 

This makes my project novel for PDB, but it is drawn from best practices in industry software versioning. 


XXXXXX semantic versioning 

# Requirements
```
 identify/describe what specific goals/requirements you set for system. This section is a breakdown of your main objective/purpose into the next level of things your system must do. You do not need to write this like a requirements document. You may use bullets to enumerate the main points. For example, if one of the high level objectives is to allow users to search for variants, a requirement would be to develop a UI that has a search box that accepts a gene name. 
 ```

My system is designed to answer the following questions:
- Which literature references a given protein? 
- Which literature cites this version of this protein? 
- Does this article cite the most recent version of this protein? 

It also allows users to create new citations, and to attempt to contact the authors of an article in the event their citation is out of date. 

In service of these requirements, my server includes the following endpoints:

- `GET /proteins/{pdb_id}/citations` : used to view all scraped literature associated with any version of a given protein 
- `GET /proteins/{pdb_id}/versions/{version_number}/citations` : used to view all scraped literature associated with a given version of a given protein
- `POST /proteins/{pdb_id}` : used to enter a new protein in the database
- `POST /proteins/{pdb_id}/versions` : used to enter a new protein version
- `POST /proteins/{pdb_id}/versions/{version_number}/citations?notify={true|false}` : used to enter a new citation in the database. This endpoint will also attempt to notify the author of the citation if the citation is not for the most recently released version based on the query param "notify" 
- `GET /citations/{issn}/proteins` : a helper endpoint, which gives all of the proteins referenced by some citation
- `GET /citations?title={title_search}` : a generic query endpoint for the citations in the database, with all stored fields available as query params (title shown her for example)


# Logical database design

```
describe your data model, include figures. The figures do not have to be ER diagrams; use whatever you like here. I also want a description of the fields/elements of your data model, but that will go in Appendix A. (If you want to use an AI program for this section, see below.)

```

My database includes the following tables and fields:
![ER Diagram of table](diagram.drawio.png)



My database will be populated with information from:
- PDB, which will provide data on protein versioning and original citation details
- Google Scholar, which I will use to find literature that either references the original protein, or cites the original published structure
- PubMed, which will fulfill a similar role as Google Scholar, but with a nicer interface through EUtils.  
This data will be scraped programmatically through a python script, which will act as a client to the API I will build to interact with my database. 
 

 

# Database type
```
 Describe what type of database/resource (flatfiles, RDB, etc.) you chose and why. What benefits does this choice give you and what limitations (if any)? (If you want to use an AI program for this section, see below.)
 ```

 
I used a MySQL database for this project. I chose this database for the following reasons:
1. My data is primarily entered in the database programmatically (ie, through my web scraper). Thus, it can follow a strict schema. NoSQL databases can be useful when you may introduce additional fields, or when some of your documents may hold additional sub fields. That doesn't apply to this situation. 
2. SQL databases generally have faster joins than NoSQL databases. Join tables were required for the /citation first endpoints, as information is indexed by protein, not citation. From the mongo db website, "MySQL is optimized for high performance joins across multiple tables that have been appropriately indexed," in comparison to Mongo, which supports joins, but is optimized for faster reads. 
3. For version 1 of the project, space was a consideration for the overall database. Maintaining a minimal schema allows for the project to be developed and deployed on smaller instances (read: my personal website). A SQL database allowed me to balance the rigidity of providing a minimal set of fields, with the flexibility of allowing users to perform ad-hoc queries on any those fields with reasonably high performance despite the space constraints. 


# Physical/Application design
 Describe how you turned your logical design into a physical or application design. For example, if you used ER diagrams, explain what tables you derived from the diagrams. This section may end up being very short depending upon what you have in the logical section and/or implementation section. But it may be longer if those sections are shorter. For example, if you use ER diagrams and a relational model for a relational database, it is a straightforward explanation regarding how you derived your tables. If you use an object model but implement it as a relational database, the explanation is more complex (you might need join tables, for example).If this section is short, more points will be allocated to section 4. (If you want to use an AI program for this section, see below.)

 

 

# Test Plan
```
 Essentially this is an updated copy of your Discussion 12 post. Identify and explain several tests/inspections you used to verify your system is working (screenshots will be needed -- these go in the Implementation section). 

 ```

Actual test plan
- Missing data 
- Incorrect data 
- Malicious data

Referential integrity 


 

# Implementation
 Explain how you implemented your system. What problems did you face? What lessons did you learn? How did your implementation address the theme/issue and objectives of your project? Include screenshots and discussion of your system being run for some example use cases. (If you want to use an AI program for this section, see below.)

 

#  Conclusion and Future Work
```
 Summarize what you did in a paragraph and propose some additional work you would have done if you'd had more time.

 ```

Do NOT use an AI program for section 9.

 

# References


https://www.mongodb.com/resources/compare/mongodb-mysql

 

# Appendix A
## Data Dictionary. 
```
List your data model elements with a brief definition of each, and identify the source of each element. (If you want to use an AI program for this section, see below.)

```

| Field Name              | Table Name         | Description                          | Field Type           | Source                                     |
|-------------------------|--------------------|--------------------------------------|----------------------|--------------------------------------------|
| pdb_id                  | Proteins           |The identifier for the protein from pdb. Unique within the PDB. Here used as a primary key. | string NOT NULL      | User provided or from web scraper         |
| major_version_number    | Proteins           |The major semantic version number, representing a breaking change with a previous revision| int NOT NULL         | From RCSB API                             |
| minor_version_number    | Proteins           |A minor semantic version number, which does not justify a major release| int NOT NULL         | From RCSB API                             |
| revision_date           | Proteins           |The date the revision was released. Also used to guess the revision of the protein referenced in an article if none is provided. | char(50) NOT NULL    | From RCSB API                             |
| revision_type           | Proteins           |An enumerated value from pdb describing the type of revision. Multiple enumerated values may be used, but are not stored separately for space| char(50) NOT NULL    | From RCSB API                             |
| pdb_and_version         | Protein_Citation   | A programmatically generated foreign key from the pdb_id and version | string NOT NULL | Programmatically generated                |
| doi                     | Protein_Citation   |Stands for "Digital Object Identifier". A UUID for online articles and documents. Here used as a foreign key. | string NOT NULL      | From CrossRef API                         |
| presumed_version        | Protein_Citation   |A boolean flag to highlight whether the referenced version is known, or presumed. All entries from the web scraper will be presumed, not known. | boolean NOT NULL     | Programmatically generated                |
| DOI                     | Citation           |Stands for "Digital Object Identifier". A UUID for online articles and documents. Here the primary key. | string NOT NULL      | From CrossRef API                         |
| ISSN                    | Citation           |Stands for "International Standard Serial Number." Used to represent continuing resources like journals. Another common resource identifier, included in this database for cross compatibility | string NOT NULL      | From CrossRef API                         |
| Title                   | Citation           |Open string field, can be used as a query field. | string NOT NULL      | From CrossRef API                         |
| PMID                    | Citation           |Stands for "PubMed ID." This field is how contact information is cross linked. | int NOT NULL         | From CrossRef API                         |
| author_id               | Author             | Programmatically generated primary key, as first and last name are not guaranteed unique.  | int NOT NULL       | Programmatically generated                |
| first_name              | Author             |Open string field, can be used as a query field. | string NOT NULL      | From CrossRef API                         |
| last_name               | Author             |Open string field, can be used as a query field. | string NOT NULL      | From CrossRef API                         |
| author_id (FK1)         | Authorship         |Foreign key to associate authors and articles | int NOT NULL         | Programmatically generated                |
| doi (FK2)               | Authorship         |Foreign key to associate authors and articles| string NOT NULL      | From CrossRef API                         |
