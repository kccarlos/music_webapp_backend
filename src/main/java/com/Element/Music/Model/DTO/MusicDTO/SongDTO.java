package com.Element.Music.Model.DTO.MusicDTO;

import com.Element.Music.Emun.Genre;
import com.Element.Music.Model.DAO.MusicDAO.Album;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Model.DAO.TradeDAO.Price;
import com.Element.Music.Model.DAO.UserDAO.Consumer;
import com.Element.Music.Model.DAO.UserDAO.Musician;
import com.Element.Music.Model.DTO.UserDTO.ConsumerDTO;
import com.Element.Music.Model.DTO.UserDTO.MusicianDTO;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class SongDTO {

    private AlbumDTO albumDTO;

    private Date releaseDate;

    private String songName;

    private Genre genre;

    private MusicianDTO musicianDTO;

    private String lyric;

    private String info;

    private String description;

    private int liked;

    private Price price;

    private List<ConsumerDTO> consumerDTOS;//consumer的名字的list

    private SongDTO convertToDTO(Song song) {
        return null;
    }
}
