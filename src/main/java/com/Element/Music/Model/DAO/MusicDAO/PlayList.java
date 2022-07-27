package com.Element.Music.Model.DAO.MusicDAO;

import com.Element.Music.Model.DAO.BaseEntity;
import com.Element.Music.Model.DAO.UserDAO.Consumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "PlayList")
@Data
@Builder
@Table(name = "play_list")
@NoArgsConstructor
@AllArgsConstructor
public class PlayList extends BaseEntity {

    /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(nullable = true)
    private Consumer createdBy;*/

    private String ListName;

    private String representImagePath;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Song> songs;

    private String description;

}
