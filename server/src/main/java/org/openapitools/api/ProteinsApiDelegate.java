package org.openapitools.api;

import org.openapitools.model.Citation;
import org.openapitools.model.Error;
import org.openapitools.model.ProteinEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link ProteinsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-03T20:23:08.570069800-06:00[America/Chicago]", comments = "Generator version: 7.9.0")
public interface ProteinsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /proteins : Enter a new protein into the database
     *
     * @param proteinEntry  (required)
     * @return Null response (status code 201)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#createProtein
     */
    default ResponseEntity<ProteinEntry> createProtein(ProteinEntry proteinEntry) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"pdb_id\" : 0, \"versions\" : [ { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 0, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /proteins/{pdb_id}/versions/{version_number}/citations : Enter a new protein citation
     *
     * @param pdbId the pdb id of interest (required)
     * @param versionNumber the version_number of interest (required)
     * @return An new citation (status code 200)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#createProteinCitation
     */
    default ResponseEntity<Citation> createProteinCitation(String pdbId,
        String versionNumber) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"referenced_protein_id\" : \"referenced_protein_id\", \"issn\" : \"issn\", \"version_presumed\" : true, \"title\" : \"title\", \"referenced_protein_version\" : { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, \"pmd_id\" : \"pmd_id\", \"doi\" : \"doi\", \"authors\" : [ { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" }, { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 0, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /proteins/{pdb_id}/versions/ : Create a new version of this pdb_id
     *
     * @param pdbId the pdb id of interest (required)
     * @return An update protein entry with new version (status code 200)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#createProteinVersion
     */
    default ResponseEntity<ProteinEntry> createProteinVersion(String pdbId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"pdb_id\" : 0, \"versions\" : [ { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 0, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /proteins/{pdb_id} : Get the  entry for this pdb_id
     *
     * @param pdbId the pdb id of interest (required)
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of proteins (status code 200)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#getProtein
     */
    default ResponseEntity<ProteinEntry> getProtein(String pdbId,
        Integer limit) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"pdb_id\" : 0, \"versions\" : [ { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 0, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /proteins/{pdb_id}/citations : Get the all citation entries for this pdb_id
     *
     * @param pdbId the pdb id of interest (required)
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of protein citations (status code 200)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#getProteinCitations
     */
    default ResponseEntity<List<Citation>> getProteinCitations(String pdbId,
        Integer limit) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"referenced_protein_id\" : \"referenced_protein_id\", \"issn\" : \"issn\", \"version_presumed\" : true, \"title\" : \"title\", \"referenced_protein_version\" : { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, \"pmd_id\" : \"pmd_id\", \"doi\" : \"doi\", \"authors\" : [ { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" }, { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" } ] }, { \"referenced_protein_id\" : \"referenced_protein_id\", \"issn\" : \"issn\", \"version_presumed\" : true, \"title\" : \"title\", \"referenced_protein_version\" : { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, \"pmd_id\" : \"pmd_id\", \"doi\" : \"doi\", \"authors\" : [ { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" }, { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" } ] }, { \"referenced_protein_id\" : \"referenced_protein_id\", \"issn\" : \"issn\", \"version_presumed\" : true, \"title\" : \"title\", \"referenced_protein_version\" : { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, \"pmd_id\" : \"pmd_id\", \"doi\" : \"doi\", \"authors\" : [ { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" }, { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" } ] }, { \"referenced_protein_id\" : \"referenced_protein_id\", \"issn\" : \"issn\", \"version_presumed\" : true, \"title\" : \"title\", \"referenced_protein_version\" : { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, \"pmd_id\" : \"pmd_id\", \"doi\" : \"doi\", \"authors\" : [ { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" }, { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" } ] }, { \"referenced_protein_id\" : \"referenced_protein_id\", \"issn\" : \"issn\", \"version_presumed\" : true, \"title\" : \"title\", \"referenced_protein_version\" : { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, \"pmd_id\" : \"pmd_id\", \"doi\" : \"doi\", \"authors\" : [ { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" }, { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" } ] } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 0, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /proteins/{pdb_id}/versions/{version_number}/citations : Get the all citation entries for this pdb_id version
     *
     * @param pdbId the pdb id of interest (required)
     * @param versionNumber the version_number of interest (required)
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of protein citations (status code 200)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#getProteinCitationsByVersion
     */
    default ResponseEntity<List<Citation>> getProteinCitationsByVersion(String pdbId,
        String versionNumber,
        Integer limit) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"referenced_protein_id\" : \"referenced_protein_id\", \"issn\" : \"issn\", \"version_presumed\" : true, \"title\" : \"title\", \"referenced_protein_version\" : { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, \"pmd_id\" : \"pmd_id\", \"doi\" : \"doi\", \"authors\" : [ { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" }, { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" } ] }, { \"referenced_protein_id\" : \"referenced_protein_id\", \"issn\" : \"issn\", \"version_presumed\" : true, \"title\" : \"title\", \"referenced_protein_version\" : { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, \"pmd_id\" : \"pmd_id\", \"doi\" : \"doi\", \"authors\" : [ { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" }, { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" } ] }, { \"referenced_protein_id\" : \"referenced_protein_id\", \"issn\" : \"issn\", \"version_presumed\" : true, \"title\" : \"title\", \"referenced_protein_version\" : { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, \"pmd_id\" : \"pmd_id\", \"doi\" : \"doi\", \"authors\" : [ { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" }, { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" } ] }, { \"referenced_protein_id\" : \"referenced_protein_id\", \"issn\" : \"issn\", \"version_presumed\" : true, \"title\" : \"title\", \"referenced_protein_version\" : { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, \"pmd_id\" : \"pmd_id\", \"doi\" : \"doi\", \"authors\" : [ { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" }, { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" } ] }, { \"referenced_protein_id\" : \"referenced_protein_id\", \"issn\" : \"issn\", \"version_presumed\" : true, \"title\" : \"title\", \"referenced_protein_version\" : { \"revision_type\" : \"revision_type\", \"major_version\" : 6, \"revision_date\" : \"2000-01-23\", \"minor_version\" : 1 }, \"pmd_id\" : \"pmd_id\", \"doi\" : \"doi\", \"authors\" : [ { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" }, { \"last_name\" : \"last_name\", \"contact_information\" : { \"email\" : \"email\" }, \"first_name\" : \"first_name\" } ] } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 0, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /proteins : List the pdb ids currently available in the database
     *
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of proteins (status code 200)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#listProteins
     */
    default ResponseEntity<List<String>> listProteins(Integer limit) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ null, null, null, null, null ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"code\" : 0, \"message\" : \"message\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
