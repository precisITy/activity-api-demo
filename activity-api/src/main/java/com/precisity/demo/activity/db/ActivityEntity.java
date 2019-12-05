package com.precisity.demo.activity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Database entity, representing an activity. Defines the database table structure.
 */
@Entity
@Table(name = "ACTIVITY")
public class ActivityEntity {

  /**
   * Unique ID, generated automatically
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Column(name = "HEADER")
  private String header = null;

  @Column(name = "NAME")
  private String name = null;

  @Column(name = "SHORT_DESCRIPTION")
  private String shortDescription = null;

  @Column(name = "PROCUREMENT_CHANNEL")
  private String procurementChannel = null;

  @Column(name = "DATE_CREATION")
  private String dateCreation = null;

  @Column(name = "AUTHOR")
  private String author = null;

  @Column(name = "QUANTITY")
  private Integer quantity = null;

  @Column(name = "PRICE")
  private String price = null;

  @Column(name = "STATUS")
  private String status = null;

  // ---------------------------------------------------------

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getProcurementChannel() {
    return procurementChannel;
  }

  public void setProcurementChannel(String procurementChannel) {
    this.procurementChannel = procurementChannel;
  }

  public OffsetDateTime getDateCreation() {
    return dateCreation == null ? null : OffsetDateTime.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(dateCreation));
  }

  public void setDateCreation(OffsetDateTime dateCreation) {
    this.dateCreation = dateCreation == null ? null : dateCreation.toString();
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
