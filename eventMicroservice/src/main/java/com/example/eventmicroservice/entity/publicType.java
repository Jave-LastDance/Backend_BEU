package com.example.eventmicroservice.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "public")
public class publicType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_public")
    private Integer idPublic;

    @Column(name = "category")
    private String category;

    public publicType() {
    }

    public publicType(Integer idPublic, String category) {
        this.idPublic = idPublic;
        this.category = category;
    }

    public Integer getIdPublic() {
        return idPublic;
    }

    public void setIdPublic(Integer idPublic) {
        this.idPublic = idPublic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
