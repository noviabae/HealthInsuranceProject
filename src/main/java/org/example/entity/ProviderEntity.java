package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "healthcare_provider", schema = "public")
public class ProviderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    private String location;
    private String specialization;
    private String insuranceNetwork;

    public ProviderEntity(Long id, String name, String location, String specialization, String insuranceNetwork) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.specialization = specialization;
        this.insuranceNetwork = insuranceNetwork;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getInsuranceNetwork() {
        return insuranceNetwork;
    }

    public void setInsuranceNetwork(String insuranceNetwork) {
        this.insuranceNetwork = insuranceNetwork;
    }
}
