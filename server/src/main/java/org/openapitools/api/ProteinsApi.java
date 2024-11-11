/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.9.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import org.openapitools.model.Citation;
import org.openapitools.model.Error;
import org.openapitools.model.ProteinEntry;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-11T16:39:38.427356300-06:00[America/Chicago]", comments = "Generator version: 7.9.0")
@Validated
@Tag(name = "protein", description = "the protein API")
public interface ProteinsApi {

    default ProteinsApiDelegate getDelegate() {
        return new ProteinsApiDelegate() {};
    }

    /**
     * POST /proteins : Enter a new protein into the database
     *
     * @param proteinEntry  (required)
     * @return Null response (status code 201)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "createProtein",
        summary = "Enter a new protein into the database",
        tags = { "protein" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Null response", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProteinEntry.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/proteins",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<ProteinEntry> createProtein(
        @Parameter(name = "ProteinEntry", description = "", required = true) @Valid @RequestBody ProteinEntry proteinEntry
    ) {
        return getDelegate().createProtein(proteinEntry);
    }


    /**
     * POST /proteins/{pdb_id}/versions/{version_number}/citations : Enter a new protein citation
     *
     * @param pdbId the pdb id of interest (required)
     * @param versionNumber the version_number of interest (required)
     * @param citation  (required)
     * @return An new citation (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "createProteinCitation",
        summary = "Enter a new protein citation",
        tags = { "proteins" },
        responses = {
            @ApiResponse(responseCode = "200", description = "An new citation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Citation.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/proteins/{pdb_id}/versions/{version_number}/citations",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Citation> createProteinCitation(
        @Parameter(name = "pdb_id", description = "the pdb id of interest", required = true, in = ParameterIn.PATH) @PathVariable("pdb_id") String pdbId,
        @Parameter(name = "version_number", description = "the version_number of interest", required = true, in = ParameterIn.PATH) @PathVariable("version_number") String versionNumber,
        @Parameter(name = "Citation", description = "", required = true) @Valid @RequestBody Citation citation
    ) {
        return getDelegate().createProteinCitation(pdbId, versionNumber, citation);
    }


    /**
     * POST /proteins/{pdb_id}/versions/ : Create a new version of this pdb_id
     *
     * @param pdbId the pdb id of interest (required)
     * @return An update protein entry with new version (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "createProteinVersion",
        summary = "Create a new version of this pdb_id",
        tags = { "proteins" },
        responses = {
            @ApiResponse(responseCode = "200", description = "An update protein entry with new version", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProteinEntry.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/proteins/{pdb_id}/versions/",
        produces = { "application/json" }
    )
    
    default ResponseEntity<ProteinEntry> createProteinVersion(
        @Parameter(name = "pdb_id", description = "the pdb id of interest", required = true, in = ParameterIn.PATH) @PathVariable("pdb_id") String pdbId
    ) {
        return getDelegate().createProteinVersion(pdbId);
    }


    /**
     * GET /proteins/{pdb_id} : Get the  entry for this pdb_id
     *
     * @param pdbId the pdb id of interest (required)
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of proteins (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "getProtein",
        summary = "Get the  entry for this pdb_id",
        tags = { "proteins" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of proteins", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProteinEntry.class))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/proteins/{pdb_id}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<ProteinEntry> getProtein(
        @Parameter(name = "pdb_id", description = "the pdb id of interest", required = true, in = ParameterIn.PATH) @PathVariable("pdb_id") String pdbId,
        @Max(100) @Parameter(name = "limit", description = "How many items to return at one time (max 100)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return getDelegate().getProtein(pdbId, limit);
    }


    /**
     * GET /proteins/{pdb_id}/citations : Get the all citation entries for this pdb_id
     *
     * @param pdbId the pdb id of interest (required)
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of protein citations (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "getProteinCitations",
        summary = "Get the all citation entries for this pdb_id",
        tags = { "proteins" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of protein citations", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Citation.class)))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/proteins/{pdb_id}/citations",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<Citation>> getProteinCitations(
        @Parameter(name = "pdb_id", description = "the pdb id of interest", required = true, in = ParameterIn.PATH) @PathVariable("pdb_id") String pdbId,
        @Max(100) @Parameter(name = "limit", description = "How many items to return at one time (max 100)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return getDelegate().getProteinCitations(pdbId, limit);
    }


    /**
     * GET /proteins/{pdb_id}/versions/{version_number}/citations : Get the all citation entries for this pdb_id version
     *
     * @param pdbId the pdb id of interest (required)
     * @param versionNumber the version_number of interest (required)
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of protein citations (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "getProteinCitationsByVersion",
        summary = "Get the all citation entries for this pdb_id version",
        tags = { "proteins" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of protein citations", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Citation.class)))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/proteins/{pdb_id}/versions/{version_number}/citations",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<Citation>> getProteinCitationsByVersion(
        @Parameter(name = "pdb_id", description = "the pdb id of interest", required = true, in = ParameterIn.PATH) @PathVariable("pdb_id") String pdbId,
        @Parameter(name = "version_number", description = "the version_number of interest", required = true, in = ParameterIn.PATH) @PathVariable("version_number") String versionNumber,
        @Max(100) @Parameter(name = "limit", description = "How many items to return at one time (max 100)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return getDelegate().getProteinCitationsByVersion(pdbId, versionNumber, limit);
    }


    /**
     * GET /proteins : List the pdb ids currently available in the database
     *
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of proteins (status code 200)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "listProteins",
        summary = "List the pdb ids currently available in the database",
        tags = { "proteins" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of proteins", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))
            }),
            @ApiResponse(responseCode = "default", description = "unexpected error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/proteins",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<String>> listProteins(
        @Max(100) @Parameter(name = "limit", description = "How many items to return at one time (max 100)", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return getDelegate().listProteins(limit);
    }

}
