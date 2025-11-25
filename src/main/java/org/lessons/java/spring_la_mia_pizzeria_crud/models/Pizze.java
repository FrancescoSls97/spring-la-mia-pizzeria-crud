package org.lessons.java.spring_la_mia_pizzeria_crud.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity // Entity sará una tabella
@Table(name = "pizze")
public class Pizze {
    // ogni variabile di istanza sará una colonna
    // le annotazioni definiscono il tipo di dato
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "La tua pizza deve avere un nome")
    private String name;

    @Column(nullable = false) 
    @Lob 
    @NotBlank (message = "Descrivi la tua pizza")
    private String description;

    @Lob
    @Column(nullable = false)
    @NotBlank(message = "Inserisci un url con l' immagine della pizza")
    private String image;

    @Column(nullable = false)
    @NotNull(message = "Inserisci un prezzo per la tua pizza")
    private BigDecimal price;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.name, this.description, this.price);
    }
}
