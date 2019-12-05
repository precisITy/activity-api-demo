package com.precisity.demo.activity.model;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.threeten.bp.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * Activity
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-12-05T14:33:51.190Z[GMT]")
public class Activity {

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("header")
  private String header = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("shortDescription")
  private String shortDescription = null;

  @JsonProperty("procurementChannel")
  private String procurementChannel = null;

  @JsonProperty("dateCreation")
  private OffsetDateTime dateCreation = null;

  @JsonProperty("author")
  private String author = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("price")
  private String price = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {

    A("A"),

    B("B"),

    C("C");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  public Activity id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * 
   * @return id
   **/
  @ApiModelProperty(value = "")

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Activity header(String header) {
    this.header = header;
    return this;
  }

  /**
   * Get header
   * 
   * @return header
   **/
  @ApiModelProperty(value = "")

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public Activity name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * 
   * @return name
   **/
  @ApiModelProperty(value = "")

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Activity shortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
    return this;
  }

  /**
   * Get shortDescription
   * 
   * @return shortDescription
   **/
  @ApiModelProperty(value = "")

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public Activity procurementChannel(String procurementChannel) {
    this.procurementChannel = procurementChannel;
    return this;
  }

  /**
   * Get procurementChannel
   * 
   * @return procurementChannel
   **/
  @ApiModelProperty(value = "")

  public String getProcurementChannel() {
    return procurementChannel;
  }

  public void setProcurementChannel(String procurementChannel) {
    this.procurementChannel = procurementChannel;
  }

  public Activity dateCreation(OffsetDateTime dateCreation) {
    this.dateCreation = dateCreation;
    return this;
  }

  /**
   * Get dateCreation
   * 
   * @return dateCreation
   **/
  @ApiModelProperty(value = "")

  @Valid
  public OffsetDateTime getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(OffsetDateTime dateCreation) {
    this.dateCreation = dateCreation;
  }

  public Activity author(String author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * 
   * @return author
   **/
  @ApiModelProperty(value = "")

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Activity quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * 
   * @return quantity
   **/
  @ApiModelProperty(value = "")

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Activity price(String price) {
    this.price = price;
    return this;
  }

  /**
   * Data type string instead number is used to avoid precision loss and to allow the implementation to use a suitable data type
   * without loss of precision
   * 
   * @return price
   **/
  @ApiModelProperty(value = "Data type string instead number is used to avoid precision loss and to allow the implementation to use a suitable data type without loss of precision")

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Activity status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * 
   * @return status
   **/
  @ApiModelProperty(value = "")

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Activity activity = (Activity) o;
    return Objects.equals(this.id, activity.id) && Objects.equals(this.header, activity.header) &&
           Objects.equals(this.name, activity.name) && Objects.equals(this.shortDescription, activity.shortDescription) &&
           Objects.equals(this.procurementChannel, activity.procurementChannel) &&
           Objects.equals(this.dateCreation, activity.dateCreation) && Objects.equals(this.author, activity.author) &&
           Objects.equals(this.quantity, activity.quantity) && Objects.equals(this.price, activity.price) &&
           Objects.equals(this.status, activity.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, header, name, shortDescription, procurementChannel, dateCreation, author, quantity, price, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Activity {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    header: ").append(toIndentedString(header)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    shortDescription: ").append(toIndentedString(shortDescription)).append("\n");
    sb.append("    procurementChannel: ").append(toIndentedString(procurementChannel)).append("\n");
    sb.append("    dateCreation: ").append(toIndentedString(dateCreation)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
