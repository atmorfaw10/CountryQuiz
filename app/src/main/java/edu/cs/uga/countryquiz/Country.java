package edu.cs.uga.countryquiz;

/**
 * This class (a POJO) represents a single country, including the id, country name, and
 * continent.
 * This id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 */
public class Country {
    private long id;
    private String country;
    private String continent;

    public Country() {
        this.id = -1;
        this.country = null;
        this.continent = null;
    }

    public Country(String country, String continent) {
        this.id = -1;
        this.country = country;
        this.continent = continent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String toString() {
        return id + ": " + country + " " + continent;
    }
}
