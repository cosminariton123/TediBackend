package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@Entity
@Table(name = "Menues")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "soup")
    private Integer soupWeightInGrams;

    @Column(name = "fries")
    private Integer friesWeightInGrams;

    @Column(name = "steak")
    private Integer steakWeightInGrams;

    @Column(name = "burger")
    private Integer burgerWeightInGrams;

    @Column(name = "boiled_eggs")
    private Integer boiledEggsWeightInGrams;

    @Column(name = "fried_eggs")
    private Integer friedEggsWeightInGrams;

    public Integer getSteakWeightInGrams() {
        return steakWeightInGrams;
    }

    public void setSteakWeightInGrams(Integer steakWeightInGrams) {
        this.steakWeightInGrams = steakWeightInGrams;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer menuId) {
        this.id = menuId;
    }

    public Integer getSoupWeightInGrams() {
        return soupWeightInGrams;
    }

    public void setSoupWeightInGrams(Integer soupWeightInGrams) {
        this.soupWeightInGrams = soupWeightInGrams;
    }

    public Integer getFriesWeightInGrams() {
        return friesWeightInGrams;
    }

    public void setFriesWeightInGrams(Integer friesWeightInGrams) {
        this.friesWeightInGrams = friesWeightInGrams;
    }

    public Integer getBurgerWeightInGrams() {
        return burgerWeightInGrams;
    }

    public void setBurgerWeightInGrams(Integer burgerWeightInGrams) {
        this.burgerWeightInGrams = burgerWeightInGrams;
    }

    public Integer getBoiledEggsWeightInGrams() {
        return boiledEggsWeightInGrams;
    }

    public void setBoiledEggsWeightInGrams(Integer boiledEggsWeightInGrams) {
        this.boiledEggsWeightInGrams = boiledEggsWeightInGrams;
    }

    public Integer getFriedEggsWeightInGrams() {
        return friedEggsWeightInGrams;
    }

    public void setFriedEggsWeightInGrams(Integer friedEggsWeightInGrams) {
        this.friedEggsWeightInGrams = friedEggsWeightInGrams;
    }
}
