package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.model.ContactInformation;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

/**
 * Author
 */
@Entity
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-03T20:23:08.570069800-06:00[America/Chicago]", comments = "Generator version: 7.9.0")
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;

  private String firstName;

  private String lastName;

  @Embedded
  private ContactInformation contactInformation;

  @ManyToMany
  @JoinTable(
          name = "author_citation", // Name of the join table
          joinColumns = @JoinColumn(name = "id"), // Column in the join table that references Author
          inverseJoinColumns = @JoinColumn(name = "doi") // Column in the join table that references Citation
  )
  private Set<@Valid Citation> citations = new HashSet<>();

  public Author firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * 
   * @return firstName
   */

  @Schema(name = "first_name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Author lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * 
   * @return lastName
   */

  @Schema(name = "last_name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("last_name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Author contactInformation(ContactInformation contactInformation) {
    this.contactInformation = contactInformation;
    return this;
  }

  /**
   * Get contactInformation
   * 
   * @return contactInformation
   */
  @Valid
  @Schema(name = "contact_information", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contact_information")
  public ContactInformation getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(ContactInformation contactInformation) {
    this.contactInformation = contactInformation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Author author = (Author) o;
    return Objects.equals(this.firstName, author.firstName) &&
        Objects.equals(this.lastName, author.lastName) &&
        Objects.equals(this.contactInformation, author.contactInformation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, contactInformation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Author {\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    contactInformation: ").append(toIndentedString(contactInformation)).append("\n");
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
