package org.openapitools.api;

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
 * A delegate to be called by the {@link CitationsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-03T20:23:08.570069800-06:00[America/Chicago]", comments = "Generator version: 7.9.0")
public interface CitationsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /citations/{issn} : Get all proteins cited by this article
     *
     * @param issn the issn of interest (required)
     * @param limit How many items to return at one time (max 100) (optional)
     * @return A paged array of proteins (status code 200)
     *         or unexpected error (status code 200)
     * @see CitationsApi#getProteinsCited
     */
    default ResponseEntity<ProteinEntry> getProteinsCited(String issn,
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

}
