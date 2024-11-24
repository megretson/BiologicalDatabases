package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.Author;
import org.openapitools.model.VersionEntry;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

/**
 * Citation
 */
@Entity
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-03T20:23:08.570069800-06:00[America/Chicago]", comments = "Generator version: 7.9.0")
public class Citation {

  private String issn;

  @Id
  private String doi;

  private String title;

  private String pmdId;

  @ManyToOne
  @JoinColumn(name = "pdbId")
  private ProteinEntry referencedProteinId;

  @ManyToOne
  @JoinColumns({
      @JoinColumn(name = "major_version", referencedColumnName = "majorVersion"),
      @JoinColumn(name = "minor_version", referencedColumnName = "minorVersion") })
  private VersionEntry referencedProteinVersion;

  @ManyToMany(mappedBy = "citations")
  private Set<@Valid Author> authors = new HashSet<>();

  private Boolean versionPresumed;

  public Citation issn(String issn) {
    this.issn = issn;
    return this;
  }

  /**
   * Get issn
   * 
   * @return issn
   */

  @Schema(name = "issn", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("issn")
  public String getIssn() {
    return issn;
  }

  public void setIssn(String issn) {
    this.issn = issn;
  }

  public Citation doi(String doi) {
    this.doi = doi;
    return this;
  }

  /**
   * Get doi
   * 
   * @return doi
   */

  @Schema(name = "doi", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("doi")
  public String getDoi() {
    return doi;
  }

  public void setDoi(String doi) {
    this.doi = doi;
  }

  public Citation title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * 
   * @return title
   */

  @Schema(name = "title", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Citation pmdId(String pmdId) {
    this.pmdId = pmdId;
    return this;
  }

  /**
   * Get pmdId
   * 
   * @return pmdId
   */

  @Schema(name = "pmd_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pmd_id")
  public String getPmdId() {
    return pmdId;
  }

  public void setPmdId(String pmdId) {
    this.pmdId = pmdId;
  }

  public Citation referencedProteinId(ProteinEntry referencedProteinId) {
    this.referencedProteinId = referencedProteinId;
    return this;
  }

  /**
   * Get referencedProteinId
   * 
   * @return referencedProteinId
   */

  @Schema(name = "referenced_protein_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("referenced_protein_id")
  public ProteinEntry getReferencedProteinId() {
    return referencedProteinId;
  }

  public void setReferencedProteinId(ProteinEntry referencedProteinId) {
    this.referencedProteinId = referencedProteinId;
  }

  public Citation referencedProteinVersion(VersionEntry referencedProteinVersion) {
    this.referencedProteinVersion = referencedProteinVersion;
    return this;
  }

  /**
   * Get referencedProteinVersion
   * 
   * @return referencedProteinVersion
   */
  @Valid
  @Schema(name = "referenced_protein_version", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("referenced_protein_version")
  public VersionEntry getReferencedProteinVersion() {
    return referencedProteinVersion;
  }

  public void setReferencedProteinVersion(VersionEntry referencedProteinVersion) {
    this.referencedProteinVersion = referencedProteinVersion;
  }

  public Citation authors(Set<@Valid Author> authors) {
    this.authors = authors;
    return this;
  }

  public Citation addAuthorsItem(Author authorsItem) {
    if (this.authors == null) {
      this.authors = new HashSet<>();
    }
    this.authors.add(authorsItem);
    return this;
  }

  /**
   * Get authors
   * 
   * @return authors
   */
  @Valid
  @Schema(name = "authors", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authors")
  public Set<@Valid Author> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<@Valid Author> authors) {
    this.authors = authors;
  }

  public Citation versionPresumed(Boolean versionPresumed) {
    this.versionPresumed = versionPresumed;
    return this;
  }

  /**
   * Get versionPresumed
   * 
   * @return versionPresumed
   */

  @Schema(name = "version_presumed", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("version_presumed")
  public Boolean getVersionPresumed() {
    return versionPresumed;
  }

  public void setVersionPresumed(Boolean versionPresumed) {
    this.versionPresumed = versionPresumed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Citation citation = (Citation) o;
    return Objects.equals(this.issn, citation.issn) &&
        Objects.equals(this.doi, citation.doi) &&
        Objects.equals(this.title, citation.title) &&
        Objects.equals(this.pmdId, citation.pmdId) &&
        Objects.equals(this.referencedProteinId, citation.referencedProteinId) &&
        Objects.equals(this.referencedProteinVersion, citation.referencedProteinVersion) &&
        Objects.equals(this.authors, citation.authors) &&
        Objects.equals(this.versionPresumed, citation.versionPresumed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(issn, doi, title, pmdId, referencedProteinId, referencedProteinVersion, authors,
        versionPresumed);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Citation {\n");
    sb.append("    issn: ").append(toIndentedString(issn)).append("\n");
    sb.append("    doi: ").append(toIndentedString(doi)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    pmdId: ").append(toIndentedString(pmdId)).append("\n");
    sb.append("    referencedProteinId: ").append(toIndentedString(referencedProteinId)).append("\n");
    sb.append("    referencedProteinVersion: ").append(toIndentedString(referencedProteinVersion)).append("\n");
    sb.append("    authors: ").append(toIndentedString(authors)).append("\n");
    sb.append("    versionPresumed: ").append(toIndentedString(versionPresumed)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
