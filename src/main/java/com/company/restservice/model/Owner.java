package com.company.restservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A domain object class that models an owner of a company.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
@Entity
@Table(name = "owner")
@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner implements DomainObject {

    /**
     * Serialization marker.
     */
    private static final long serialVersionUID = 8447967858999530733L;

    @TableGenerator(name = "ownerIdGen",
        table = "id_gen", 
        pkColumnName = "gen_key", 
        valueColumnName = "gen_value", 
        pkColumnValue = "id_owner", 
        initialValue = 1, 
        allocationSize = 1)

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ownerIdGen")
    @Column(name = "id_owner", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @JsonBackReference
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company")
    private Company company;

    /**
     * Public default constructor
     */
    public Owner() {
    }

/**
* Public parametized constructor
*/
public Owner(Long id, String name, Company company) {
this.id = id;
this.name = name;
this.company = company;
}

/**
* @return the company
*/
public Company getCompany() {
return company;
}

/**
* @param company the company to set
*/
public void setCompany(Company company) {
this.company = company;
}

/**
* @return the id
*/
@Override
public Long getId() {
return this.id;
}

/**
* @param id the id to set
*/
@Override
public void setId(Long id) {
this.id = id;
}

/**
* @return the name
*/
public String getName() {
return name;
}

/**
* @param name the name to set
*/
public void setName(String name) {
this.name = name;
}

/* (non-Javadoc)
* @see java.lang.Object#hashCode()
*/
@Override
public int hashCode() {
final int prime = 31;
int result = 1;
result = prime * result + ((id == null) ? 0 : id.hashCode());
return result;
}

/* (non-Javadoc)
* @see java.lang.Object#equals(java.lang.Object)
*/
@Override
public boolean equals(Object obj) {
if (this == obj)
return true;
if (obj == null)
return false;
if (getClass() != obj.getClass())
return false;
Owner other = (Owner) obj;
if (id == null) {
if (other.id != null)
return false;
} else if (!id.equals(other.id))
return false;
return true;
}

@Override
public String toString() {
return "Owner [id=" + id + ", name=" + name + ", company=" + company + "]";
}
}
