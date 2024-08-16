package com.ashfaq.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COUNTRIES", schema = "HR")//if schema is not provided then default schema will be used 
public class Country {

    @Id
    @Column(name = "country_id", nullable = false,length = 2)
    private String countryId;

    private String countryName;
    private Long regionId;

}
