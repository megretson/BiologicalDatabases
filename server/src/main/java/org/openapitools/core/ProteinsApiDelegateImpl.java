package org.openapitools.core; 

import org.openapitools.api.ApiUtil;
import org.openapitools.api.ProteinsApiDelegate;
import org.openapitools.model.Citation;
import org.openapitools.model.ProteinEntry;
import org.openapitools.repository.ProteinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

@Component
public class ProteinsApiDelegateImpl implements ProteinsApiDelegate {

    @Autowired
    private ProteinRepository proteinRepo; 

    /**
     * POST /proteins : Enter a new protein into the database
     *
     * @param proteinEntry  (required)
     * @return Null response (status code 201)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#createProtein
     */
    public ResponseEntity<ProteinEntry> createProtein(ProteinEntry proteinEntry) {
        ProteinEntry protein = proteinRepo.save(proteinEntry);
        return ResponseEntity.ok(protein);

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
    public ResponseEntity<Citation> createProteinCitation(String pdbId,
        String versionNumber) {
       // TODO
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
    public ResponseEntity<ProteinEntry> createProteinVersion(String pdbId) {
        // TODO
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
    public ResponseEntity<ProteinEntry> getProtein(String pdbId,
        Integer limit) {
        // TODO
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
    public ResponseEntity<List<Citation>> getProteinCitations(String pdbId,
        Integer limit) {
        // TODO
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
    public ResponseEntity<List<Citation>> getProteinCitationsByVersion(String pdbId,
        String versionNumber,
        Integer limit) {
        // TODO
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
    public ResponseEntity<List<String>> listProteins(Integer limit) {
        // TODO
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}