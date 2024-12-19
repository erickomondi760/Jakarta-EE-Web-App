/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quickrest.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "PRODUCT")

@NamedQuery(name = Product.FIND_PRODUCT_BY_SUPPLIER, query = "select p from Product p where p.supplier.identity = :identity")
@NamedQuery(name = Product.FIND_PRODUCT_BY_CODE, query = "select p from Product p where p.code = :code")
@NamedQuery(name = Product.DELETE_PRODUCT, query = "delete from Product p where p.id = :id")
@NamedQuery(name = Product.FIND_SUPPLIERS, query = "select p.supplier from Product p")

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_PRODUCT_BY_SUPPLIER = "findProductBySupplier";
    public static final String FIND_PRODUCT_BY_CODE = "findProductByCode";
    public static final String DELETE_PRODUCT = "deleteProduct";
    public static final String FIND_SUPPLIERS = "findSuppliers";

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal id;

    @Basic(optional = false)
    @Column(name = "DEPARTMENT")
    private String department;

    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;

    @Column(name = "BARCODE")
    private String barcode;

    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;

    @Basic(optional = false)
    @Column(name = "SUBDEPARTMENT")
    private String subDepartment;

    @Basic(optional = false)
    @Column(name = "PACKAGING")
    private String packaging;
    
    @Basic(optional = false)
    @Column(name = "UNIT")
    private String unit;

    @Column(name = "PRODUCTIONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date productionDate;

    @Basic(optional = false)
    @Column(name = "EXPIRYDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @Basic(optional = false)
    @Column(name = "ORDERSTATUS")
    private String orderStatus;

    @Basic(optional = false)
    @Column(name = "COSTSTATUS")
    private String costStatus;

    @Basic(optional = false)
    @Column(name = "LIST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date listDate;

    @Basic(optional = false)
    @Column(name = "CREATED_BY")
    private String createdBy;

    @Basic(optional = false)
    @Column(name = "BATCH_NUMBER")
    private BigInteger batchNumber;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "IMAGE")
    private byte[] image;

    @Embedded
    private Supplier supplier;

    public Product() {
    }

    public Product(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubDepartment() {
        return subDepartment;
    }

    public void setSubDepartment(String subDepartment) {
        this.subDepartment = subDepartment;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCostStatus() {
        return costStatus;
    }

    public void setCostStatus(String costStatus) {
        this.costStatus = costStatus;
    }

    public Date getListDate() {
        return listDate;
    }

    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public BigInteger getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(BigInteger batchNumber) {
        this.batchNumber = batchNumber;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.quickrest.entities.Product[ id=" + id + " ]";
    }

}
