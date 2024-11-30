package org.openapitools.core;

import org.openapitools.api.ApiUtil;
import org.openapitools.api.ProteinsApiDelegate;
import org.openapitools.model.Citation;
import org.openapitools.model.ProteinEntry;
import org.openapitools.model.VersionEntry;
import org.openapitools.model.VersionId;
import org.openapitools.repository.CitationRepository;
import org.openapitools.repository.ProteinRepository;
import org.openapitools.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.Version;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ProteinsApiDelegateImpl implements ProteinsApiDelegate {

    @Autowired
    private ProteinRepository proteinRepo;

    @Autowired
    private CitationRepository citationRepo;

    @Autowired
    private VersionRepository versionRepo;

    private static final Logger LOGGER = Logger.getLogger(ProteinsApiDelegateImpl.class.getName());

    /**
     * POST /proteins : Enter a new protein into the database
     *
     * @param proteinEntry (required)
     * @return Null response (status code 201)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#createProtein
     */
    public ResponseEntity<ProteinEntry> createProtein(ProteinEntry proteinEntry) {


        LOGGER.log(Level.INFO, "Setting up protein object");
        LOGGER.log(Level.INFO, proteinEntry.toString());
        List<VersionEntry> versions = proteinEntry.getVersions();
        for (VersionEntry version : versions) {
            version.setProtein(proteinEntry);
            version.generateId(); 
        }
        LOGGER.log(Level.INFO, proteinEntry.toString());
        ProteinEntry protein = proteinRepo.saveAndFlush(proteinEntry);
        return ResponseEntity.ok(protein);


        // proteinEntry.setVersions(new ArrayList<VersionEntry>());
        // ProteinEntry protein = proteinRepo.save(proteinEntry);
        // for (VersionEntry version : versions) {
        //     version.setProtein(proteinEntry);
        //     version.generateId(); 
        //     LOGGER.log(Level.INFO, "Saving Version");
        //     LOGGER.log(Level.INFO, version.toString());
        //     versionRepo.saveAndFlush(version);
        //     // try {
        //     //     versionRepo.save(version);
        //     // } catch (Exception e) {
        //     //     LOGGER.log(Level.INFO, e.getMessage());
        //     //     continue; 
        //     // }
            
        // }
        // protein.setVersions(versions);
        // LOGGER.log(Level.INFO, proteinEntry.toString());
        
        // protein = proteinRepo.saveAndFlush(proteinEntry);
        // return ResponseEntity.ok(protein);

        // LOGGER.log(Level.INFO, "Setting up protein object");
        // List<VersionEntry> versions = proteinEntry.getVersions();
        // proteinEntry.setVersions(new ArrayList<VersionEntry>());
        // proteinRepo.save(proteinEntry);
        // for (VersionEntry version : versions) {
        //     version.setProtein(proteinEntry);
        //     // version.generateId(); 
        //     LOGGER.log(Level.INFO, "Saving Version");
        //     LOGGER.log(Level.INFO, version.toString());
        //     versionRepo.save(version);
        // }
        // LOGGER.log(Level.INFO, proteinEntry.toString());
        // proteinEntry.setVersions(versions);
        // ProteinEntry protein = proteinRepo.save(proteinEntry);
        // return ResponseEntity.ok(protein);
    }

    /**
     * POST /proteins/{pdb_id}/versions/{version_number}/citations : Enter a new
     * protein citation
     *
     * @param pdbId         the pdb id of interest (required)
     * @param versionNumber the version_number of interest (required)
     * @param citation      (required)
     * @return An new citation (status code 200)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#createProteinCitation
     */
    public ResponseEntity<Citation> createProteinCitation(String pdbId,
            String versionNumber,
            Citation citation) {

        Optional<ProteinEntry> fetchedProtein = proteinRepo.findById(pdbId);
        if (fetchedProtein.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProteinEntry protein = fetchedProtein.get();
        VersionId versionString = VersionId.parseVersionString(versionNumber);
        VersionEntry version = versionRepo.findByProteinAndMajorVersionAndMinorVersion(protein, versionString.majorVersion, versionString.minorVersion); 

        LOGGER.info(version.toString());

        citation.setReferencedProteinVersion(version);
        citation.setReferencedProteinId(protein);

        Citation savedCitation = citationRepo.save(citation);
        return ResponseEntity.ok(savedCitation);

    }

    /**
     * POST /proteins/{pdb_id}/versions/ : Create a new version of this pdb_id
     *
     * @param pdbId the pdb id of interest (required)
     * @return An update protein entry with new version (status code 200)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#createProteinVersion
     */
    public ResponseEntity<ProteinEntry> createProteinVersion(String pdbId, VersionEntry version) {
        Optional<ProteinEntry> fetchedProtein = proteinRepo.findById(pdbId);
        if (fetchedProtein.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProteinEntry protein = fetchedProtein.get();
        LOGGER.info(version.toString());
        version.setProtein(protein);
        version.generateId();
        versionRepo.save(version); 
        protein.addVersionsItem(version);
        LOGGER.log(Level.INFO, "Creating protein version");
        LOGGER.log(Level.INFO, version.toString());
        ProteinEntry savedProtein = proteinRepo.save(protein);

        return ResponseEntity.ok(savedProtein);

    }

    /**
     * GET /proteins/{pdb_id} : Get the entry for this pdb_id
     *
     * @param pdbId the pdb id of interest (required)
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of proteins (status code 200)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#getProtein
     */
    public ResponseEntity<ProteinEntry> getProtein(String pdbId,
            Integer limit) {
        Optional<ProteinEntry> fetchedProtein = proteinRepo.findById(pdbId);
        if (fetchedProtein.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProteinEntry protein = fetchedProtein.get();
        LOGGER.log(Level.INFO, protein.toString());
        List<VersionEntry> fetchedVersions = versionRepo.findByProtein(protein);
        LOGGER.log(Level.INFO, fetchedVersions.toString());
        protein.setVersions(fetchedVersions);
        return ResponseEntity.ok(protein);
    }

    /**
     * GET /proteins/{pdb_id}/citations : Get the all citation entries for this
     * pdb_id
     *
     * @param pdbId the pdb id of interest (required)
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of protein citations (status code 200)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#getProteinCitations
     */
    public ResponseEntity<List<Citation>> getProteinCitations(String pdbId,
            Integer limit) {
        List<Citation> citations = citationRepo.findAll();
        return ResponseEntity.ok(citations);
    }

    /**
     * GET /proteins/{pdb_id}/versions/{version_number}/citations : Get the all
     * citation entries for this pdb_id version
     *
     * @param pdbId         the pdb id of interest (required)
     * @param versionNumber the version_number of interest (required)
     * @param limit         How many items to return at one time (max 100)
     *                      (optional)
     * @return A paged array of protein citations (status code 200)
     *         or unexpected error (status code 200)
     * @see ProteinsApi#getProteinCitationsByVersion
     */
    public ResponseEntity<List<Citation>> getProteinCitationsByVersion(String pdbId,
            String versionNumber,
            Integer limit) {
        // TODO: implement an actual method comme ca:
        // https://www.baeldung.com/spring-data-jpa-find-by-vs-find-all-by

        Optional<ProteinEntry> fetchedProtein = proteinRepo.findById(pdbId);
        if (fetchedProtein.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProteinEntry protein = fetchedProtein.get();
        VersionId versionString = VersionId.parseVersionString(versionNumber);
        VersionEntry version = versionRepo.findByProteinAndMajorVersionAndMinorVersion(protein, versionString.majorVersion, versionString.minorVersion); 

        LOGGER.info(version.toString());

        List<Citation> citations = citationRepo.findAllByReferencedProteinVersionAndReferencedProteinVersion(protein, version);
        return ResponseEntity.ok(citations);

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
        List<String> idsList = new ArrayList<>();
        List<ProteinEntry> proteins = proteinRepo.findAll();
        for (ProteinEntry p : proteins) {
            idsList.add(p.getPdbId());
        }
        return ResponseEntity.ok(idsList);

    }

}