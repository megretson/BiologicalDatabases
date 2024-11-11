package org.openapitools.api;

import org.openapitools.model.Error;
import org.openapitools.model.ProteinEntry;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-03T16:44:23.127559700-06:00[America/Chicago]", comments = "Generator version: 7.9.0")
@Controller
@RequestMapping("${openapi.proteinVersionCitation.base-path:/ProteinVersionCitations/1.0.0}")
public class CitationsApiController implements CitationsApi {

    private final CitationsApiDelegate delegate;

    public CitationsApiController(@Autowired(required = false) CitationsApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new CitationsApiDelegate() {});
    }

    @Override
    public CitationsApiDelegate getDelegate() {
        return delegate;
    }

}
