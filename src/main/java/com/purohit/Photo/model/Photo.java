package com.purohit.Photo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name="photo")
public class Photo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("primarykey")
    @JsonIgnore
    @Column(name="id")
    private Integer id;
    @JsonProperty("id")
    @Column(name="photoId")
    private int photoId;
    @JsonProperty("title")
    @Column(name="title")
    private String title;
    @Column(name="url")
    @JsonProperty("url")
    private String url;
    @Column(name="thumbnailUrl")
    @JsonProperty("thumbnailUrl")
    private String thumbNailUrl;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="albumId",referencedColumnName = "id")
    private Album album;
    @Transient
    @JsonProperty("albumId")
    private int albumPrimaryKey;


}
