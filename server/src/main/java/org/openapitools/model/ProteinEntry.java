package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.model.VersionEntry;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * ProteinEntry
 */
@Entity
@JsonTypeName("Protein_Entry")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-03T20:23:08.570069800-06:00[America/Chicago]", comments = "Generator version: 7.9.0")
public class ProteinEntry {

  @Id
  private String pdbId;

  @Valid
  @OneToMany(mappedBy="protein", cascade=CascadeType.ALL, orphanRemoval = true)
  private List<@Valid VersionEntry> versions = new ArrayList<>();

  public ProteinEntry() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProteinEntry(List<@Valid VersionEntry> versions) {
    this.versions = versions;
  }

  public ProteinEntry pdbId(String pdbId) {
    this.pdbId = pdbId;
    return this;
  }

  /**
   * Get pdbId
   * @return pdbId
   */
  
  @Schema(name = "pdb_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pdb_id")
  public String getPdbId() {
    return pdbId;
  }

  public void setPdbId(String pdbId) {
    this.pdbId = pdbId;
  }

  public ProteinEntry versions(List<@Valid VersionEntry> versions) {
    this.versions = versions;
    return this;
  }

  public ProteinEntry addVersionsItem(VersionEntry versionsItem) {
    if (this.versions == null) {
      this.versions = new ArrayList<>();
    }
    this.versions.add(versionsItem);
    return this;
  }

  /**
   * Get versions
   * @return versions
   */
  @NotNull @Valid 
  @Schema(name = "versions", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("versions")
  public List<@Valid VersionEntry> getVersions() {
    return versions;
  }

  public void setVersions(List<@Valid VersionEntry> versions) {
    this.versions = versions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProteinEntry proteinEntry = (ProteinEntry) o;
    return Objects.equals(this.pdbId, proteinEntry.pdbId) &&
        Objects.equals(this.versions, proteinEntry.versions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pdbId, versions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProteinEntry {\n");
    sb.append("    pdbId: ").append(toIndentedString(pdbId)).append("\n");
    sb.append("    versions: ").append(toIndentedString(versions)).append("\n");
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

