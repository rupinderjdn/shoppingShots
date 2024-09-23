package com.shoppingShots.shoppingShots.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

// This is for builder pattern. Currently don't know what it is
// Builder patterns are maybe used in REST APIs
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //what is this??
    private Integer id;
    private String name;
    private String imageName;
    private Boolean isActive;

    public String toString(){
        return this.id+"|"+this.name+"|"+this.imageName+"|"+this.isActive;
    }
}
