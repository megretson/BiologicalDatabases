package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;

/**
 * VersionEntry
 */
@Entity
@JsonTypeName("Version_Entry")
@IdClass(VersionId.class)
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-03T20:23:08.570069800-06:00[America/Chicago]", comments = "Generator version: 7.9.0")
public class VersionEntry {

  @Id
  private Integer majorVersion;

  @Id
  private Integer minorVersion;

  private String revisionType;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate revisionDate;

  @JsonIgnore
  @ManyToOne
  private ProteinEntry protein; 


  public VersionEntry majorVersion(Integer majorVersion) {
    this.majorVersion = majorVersion;
    return this;
  }

  /**
   * Get majorVersion
   * @return majorVersion
   */
  
  @Schema(name = "major_version", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("major_version")
  public Integer getMajorVersion() {
    return majorVersion;
  }

  public void setMajorVersion(Integer majorVersion) {
    this.majorVersion = majorVersion;
  }

  public VersionEntry minorVersion(Integer minorVersion) {
    this.minorVersion = minorVersion;
    return this;
  }

  /**
   * Get minorVersion
   * @return minorVersion
   */
  
  @Schema(name = "minor_version", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("minor_version")
  public Integer getMinorVersion() {
    return minorVersion;
  }

  public void setMinorVersion(Integer minorVersion) {
    this.minorVersion = minorVersion;
  }

  public VersionEntry revisionType(String revisionType) {
    this.revisionType = revisionType;
    return this;
  }

  /**
   * Get revisionType
   * @return revisionType
   */
  
  @Schema(name = "revision_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("revision_type")
  public String getRevisionType() {
    return revisionType;
  }

  public void setRevisionType(String revisionType) {
    this.revisionType = revisionType;
  }

  public VersionEntry revisionDate(LocalDate revisionDate) {
    this.revisionDate = revisionDate;
    return this;
  }

  /**
   * Get revisionDate
   * @return revisionDate
   */
  @Valid 
  @Schema(name = "revision_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("revision_date")
  public LocalDate getRevisionDate() {
    return revisionDate;
  }

  public void setRevisionDate(LocalDate revisionDate) {
    this.revisionDate = revisionDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VersionEntry versionEntry = (VersionEntry) o;
    return Objects.equals(this.majorVersion, versionEntry.majorVersion) &&
        Objects.equals(this.minorVersion, versionEntry.minorVersion) &&
        Objects.equals(this.revisionType, versionEntry.revisionType) &&
        Objects.equals(this.revisionDate, versionEntry.revisionDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(majorVersion, minorVersion, revisionType, revisionDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VersionEntry {\n");
    sb.append("    majorVersion: ").append(toIndentedString(majorVersion)).append("\n");
    sb.append("    minorVersion: ").append(toIndentedString(minorVersion)).append("\n");
    sb.append("    revisionType: ").append(toIndentedString(revisionType)).append("\n");
    sb.append("    revisionDate: ").append(toIndentedString(revisionDate)).append("\n");
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

