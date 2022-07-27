package com.Element.Music.Model.DAO.MusicDAO;

import com.Element.Music.Model.DAO.BaseEntity;
import com.Element.Music.Model.DAO.UserDAO.Musician;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "Album")
@Data
@Builder
@Table(name = "album")
@NoArgsConstructor
@AllArgsConstructor
public class Album extends BaseEntity {

    @NonNull
    private String albumName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "album")
    private Set<Song> songs;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Musician musician;

    private Date releaseDate;

    private String description;

}
