For this discussion post, I will be describing the requirements for the web server for my project this semester. Requirements are actually notoriously difficult, prone to misunderstanding. Thus, in industry, we generally define requirements with strict specifications. For REST servers, we use OpenAPI specifications. OpenAPi Specifications are incredibly useful for two reasons:
1. They work as a contract negotiation between server and client, making sure both are on the same page about what the server expects and will respond with. 
2. The specifications are generally so strict you can use them generate servers and clients which implement the specification, which adds additionally verification that your implementation actually matches the spec. 

I suspect other people will be implementing web servers as well, so I wanted to include information about this incredibly useful set of tools. There's even a free online IDE for editing your spec online. Every company I have worked at thus far has used some variation on these specs for their REST servers, so I highly recommend familiarity with OpenAPi. Here's the draft of my specification: 




As a follow up to my original post, I intend to use a SQL db for my information. 
The primary advantages to me here are:
    1. My data is primary going to be programmatically entered in the database and thus can follow a strict schema. NoSQL databases can be useful when you may introduce additional fields, or when some of your documents may hold additional sub fields. That doesn't apply in my situation. 
    2. SQL databases generally have incredibly fast joins in comparison to NoSQL databases, and I will need that to implement my /citation first endpoint based on the ER diagram I have previously created for my data. 
    3. I have kept my schema very tight, with as minimal fields as possible. This will likely allow me to avoid sharding my data across multiple instances. I would like to host this api on my personal website, which will only be possible if I keep it all fairly tight because I don't rent that much server space :) 



openapi: 3.0.0
info:
  version: 1.0.0
  title: 'Protein Version Citation API '
  description: 'An API for looking at literature which cites a given protein version '
servers:
  # Added by API Auto Mocking Plugin
  - description: SwaggerHub API Auto Mocking
    url: https://virtserver.swaggerhub.com/MEGRETSON/ProteinVersionCitations/1.0.0
  - description: Primary url for protein citation database 
    url: https://megretson.com/proteincitations/v1
paths:
  /proteins:
    get: 
      summary: List the pdb ids currently available in the database
      operationId: listProteins
      tags:
        - proteins
      parameters:
        - name: limit
          in: query
          description: How many items to return at one time (max 100)
          required: false
          schema:
            type: integer
            maximum: 100
            format: int32
      responses:
        '200':
          description: A paged array of proteins
          headers:
            x-next:
              description: A link to the next page of responses
              schema:
                type: string
          content:
            application/json:    
              schema:
                $ref: "#/components/schemas/PDB_Ids"
        default:
          description: unexpected error
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Error"
    post:
      summary: Enter a new protein into the database
      operationId: createProtein
      tags: 
        - protein
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/Protein_Entry'
        required: true
      responses:
        '201':
          description: Null response
          content:
            application/json:    
              schema:
                $ref: "#/components/schemas/Protein_Entry"
        default:
          description: unexpected error
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Error"
  /proteins/{pdb_id}:
    get: 
      summary: Get the  entry for this pdb_id
      operationId: getProtein
      tags:
        - proteins
      parameters:
        - name: pdb_id
          in: path
          description: the pdb id of interest
          required: true
          schema:
            type: string
        - name: limit
          in: query
          description: How many items to return at one time (max 100)
          required: false
          schema:
            type: integer
            maximum: 100
            format: int32
      responses:
        '200':
          description: A paged array of proteins
          headers:
            x-next:
              description: A link to the next page of responses
              schema:
                type: string
          content:
            application/json:    
              schema:
                $ref: "#/components/schemas/Protein_Entry"
        default:
          description: unexpected error
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Error"
  /proteins/{pdb_id}/citations:
    get: 
      summary: Get the all citation entries for this pdb_id
      operationId: getProteinCitations
      tags:
        - proteins
      parameters:
        - name: pdb_id
          in: path
          description: the pdb id of interest
          required: true
          schema:
            type: string
        - name: limit
          in: query
          description: How many items to return at one time (max 100)
          required: false
          schema:
            type: integer
            maximum: 100
            format: int32
      responses:
        '200':
          description: A paged array of protein citations
          headers:
            x-next:
              description: A link to the next page of responses
              schema:
                type: string
          content:
            application/json:    
              schema:
                $ref: "#/components/schemas/Citations"
        default:
          description: unexpected error
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Error"
  /proteins/{pdb_id}/versions/:
    post: 
      summary: Create a new version of this pdb_id
      operationId: createProteinVersion 
      tags:
        - proteins
      parameters:
        - name: pdb_id
          in: path
          description: the pdb id of interest
          required: true
          schema:
            type: string
      
      responses:
        '200':
          description: An update protein entry with new version 
          content:
            application/json:    
              schema:
                $ref: "#/components/schemas/Protein_Entry"
        default:
          description: unexpected error
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Error"
  /proteins/{pdb_id}/versions/{version_number}/citations:
    get: 
      summary: Get the all citation entries for this pdb_id version 
      operationId: getProteinCitationsByVersion 
      tags:
        - proteins
      parameters:
        - name: pdb_id
          in: path
          description: the pdb id of interest
          required: true
          schema:
            type: string
        - name: version_number
          in: path
          description: the version_number of interest
          required: true
          schema:
            type: string
        - name: limit
          in: query
          description: How many items to return at one time (max 100)
          required: false
          schema:
            type: integer
            maximum: 100
            format: int32
      responses:
        '200':
          description: A paged array of protein citations
          headers:
            x-next:
              description: A link to the next page of responses
              schema:
                type: string
          content:
            application/json:    
              schema:
                $ref: "#/components/schemas/Citations"
        default:
          description: unexpected error
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Error"
    post: 
      summary: Enter a new protein citation
      operationId: createProteinCitation  
      tags:
        - proteins
      parameters:
        - name: pdb_id
          in: path
          description: the pdb id of interest
          required: true
          schema:
            type: string
        - name: version_number
          in: path
          description: the version_number of interest
          required: true
          schema:
            type: string
      responses:
        '200':
          description: An new citation 
          content:
            application/json:    
              schema:
                $ref: "#/components/schemas/Citation"
        default:
          description: unexpected error
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Error"
  /citations/{issn}:
    get: 
      summary: Get all proteins cited by this article 
      operationId: getProteinsCited
      tags:
        - citation
      parameters:
        - name: issn
          in: path
          description: the issn of interest
          required: true
          schema:
            type: string
        - name: limit
          in: query
          description: How many items to return at one time (max 100)
          required: false
          schema:
            type: integer
            maximum: 100
            format: int32
      responses:
        '200':
          description: A paged array of proteins
          headers:
            x-next:
              description: A link to the next page of responses
              schema:
                type: string
          content:
            application/json:    
              schema:
                $ref: "#/components/schemas/Protein_Entry"
        default:
          description: unexpected error
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Error"
      

components:
  schemas:
    PDB_Id:
      type: string
    PDB_Ids:
      type: array
      maxItems: 100
      items:
        $ref: "#/components/schemas/PDB_Id"
    Protein_Entry:
      type: object
      properties:
        pdb_id:
          type: integer
          format: int64
        versions:
          type: array
          items:
            $ref: "#/components/schemas/Version_Entry"
      required:
        - id
        - versions
    Version_Entry:
      type: object
      properties:
        major_version:
          type: integer
        minor_version:
          type: integer
        revision_type:
          type: string
        revision_date:
          type: string
          format: date
    Citations:
      type: array
      maxItems: 100
      items:
        $ref: "#/components/schemas/Citation"
    Citation:
      type: object
      properties:
        issn:
          type: string
        doi:
          type: string
        title:
          type: string
        pmd_id:
          type: string
        referenced_protein_id:
          type: string
        referenced_protein_version:
          $ref: "#/components/schemas/Version_Entry"
        authors:
          type: array
          items:
            $ref: "#/components/schemas/Author"
        version_presumed:
          type: boolean 
    Author:
      type: object
      properties:
        first_name:
          type: string
        last_name: 
          type: string
        contact_information:
          $ref: "#/components/schemas/Contact_Information"
    Contact_Information:
      type: object
      properties:
        email:
          type: string
          format: email
      
    
    Pet:
      type: object
      required:
        - id
        - name
      properties:
        id:
          type: integer
          format: int64
        name:
          type: string
        tag:
          type: string
    Pets:
      type: array
      maxItems: 100
      items:
        $ref: "#/components/schemas/Pet"
    Error:
      type: object
      required:
        - code
        - message
      properties:
        code:
          type: integer
          format: int32
        message:
          type: string