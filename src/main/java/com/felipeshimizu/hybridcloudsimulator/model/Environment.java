package com.felipeshimizu.hybridcloudsimulator.model;

import com.felipeshimizu.hybridcloudsimulator.model.enums.EnvoronmentType;
import jakarta.persistence.*;

@Entity
@Table(name = "environments")
public class Environment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EnvoronmentType type;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(nullable = false)
    private boolean active = true;

    public Environment() {
    }

    public Environment(String name, EnvoronmentType type, String location) {
        this.name = name;
        this.type = type;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EnvoronmentType getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }
}
