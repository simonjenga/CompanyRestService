package com.company.restservice.model;

import java.util.List;

import javax.annotation.Nullable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A domain object class that models a company.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
@Entity
@Table(name = "company")
@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company implements DomainObject {

/**
* Serialization marker.
*/
private static final long serialVersionUID = -3155708942599043175L;

@TableGenerator(name = "companyIdGen", 
table = "id_gen",
pkColumnName = "gen_key", 
valueColumnName = "gen_value", 
pkColumnValue = "id_company", 
initialValue = 1, 
allocationSize = 1)

@Id
@NotNull
@GeneratedValue(strategy = GenerationType.TABLE, generator = "companyIdGen")
@Column(name = "id_company", nullable = false, updatable = false)
private Long id;

@NotNull
@Column(name = "name", nullable = false)
private String name;

@NotNull
@Column(name = "address", nullable = false)
private String address;

@NotNull
@Column(name = "city", nullable = false)
private String city;

@NotNull
@Column(name = "country", nullable = false)
private String country;

@Nullable
@Email(message = "A valid email address is required") 
@Column(name = "email", nullable = true)
private String email;

@Nullable
@Column(name = "phoneNumber", nullable = true)
private String phoneNumber;

@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
@JsonProperty(value = "owner")
@JsonManagedReference
@NotNull
@OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
private List<Owner> owner;

/**
* Public default constructor
*/
public Company() {		
}

/**
* Public parametized constructor
*/
public Company(Long id, String name, String address, String city, String country, String email, String phoneNumber, List<Owner> owner) {
this.id = id;
this.name = name;
this.address = address;
this.city = city;
this.country = country;
this.email = email;
this.phoneNumber = phoneNumber;
this.owner = owner;
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

/**
* @return the address
*/
public String getAddress() {
return address;
}

/**
* @param address the address to set
*/
public void setAddress(String address) {
this.address = address;
}

/**
* @return the city
*/
public String getCity() {
return city;
}

/**
* @param city the city to set
*/
public void setCity(String city) {
this.city = city;
}

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the owner
     */
    public List<Owner> getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(List<Owner> owner) {
        this.owner = owner;
    }

    /**
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(Long id) {
        this.id = id;
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
        Company other = (Company) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Owner [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city
            + ", country=" + country + ", email=" + email + ", phoneNumber=" + phoneNumber + ", owner=" + owner + "]";
    }
}