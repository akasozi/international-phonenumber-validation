package com.exercise.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
@Entity
public class Country {
    @Id
    @NotBlank(message = "Country Code is mandatory")
    private String code;
    @NotBlank(message = "Country Name is Mandatory")
    private String name;
    @NotBlank(message = "Regex is Mandatory")
    private String regex;

    public Country() {

    }

    public Country(String code, String name, String regex) {
        this.code = code;
        this.name = name;
        this.regex = regex;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", regex='" + regex + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(code, country.code) &&
                Objects.equals(name, country.name) &&
                Objects.equals(regex, country.regex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, regex);
    }
}
