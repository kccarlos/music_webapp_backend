package com.Element.Music.Model.DAO.MusicDAO;

import com.Element.Music.Emun.Genre;
import com.Element.Music.Model.DAO.BaseEntity;
import com.Element.Music.Model.DAO.TradeDAO.Price;
import com.Element.Music.Model.DAO.UserDAO.Consumer;
import com.Element.Music.Model.DAO.UserDAO.Musician;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity(name = "Song")
@Data
@Builder
@Table(name = "song")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"musician"})
public class Song extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -63244234985364298L;

    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;

    private Date releaseDate;

    @Column(nullable = false)
    private String songName;

    private String representImagePath;

    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    private Musician musician;

    private String url;

    private String lyric;

    private String info;

    private String description;

    private int liked;

//    @OneToOne(mappedBy = "song")
//    @JsonIgnoreProperties({"song"})
//    private Price price;

    /*@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "collections")
    private Set<Consumer> consumers;*/
}
