package com.purohit.Photo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="album")
public class Album implements Serializable {
    @Id
    @JsonProperty("id")
    private int id;
    @Column(name="userId")
    @JsonProperty("userId")
    private int userId;
    @Column(name="title")
    @JsonProperty("title")
    private String title;

}
