package com.Element.Music.Model.DAO.UserDAO;


import com.Element.Music.Emun.Genre;
import com.Element.Music.Model.DAO.MusicDAO.Album;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Data
@Builder
@Entity
@Table(name = "musician")
@AllArgsConstructor
@NoArgsConstructor
public class Musician extends User implements Serializable {

    private static final long serialVersionUID = 7659253546867155512L;

    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private String name;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "musician")
    private Set<Song> songs;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Album> albums;

    private String weibo;

    private String description;

    private String representativeWork;

    private int liked;

}
