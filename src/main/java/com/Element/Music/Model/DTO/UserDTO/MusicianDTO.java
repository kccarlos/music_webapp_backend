package com.Element.Music.Model.DTO.UserDTO;

import com.Element.Music.Emun.Genre;
import com.Element.Music.Model.DAO.MusicDAO.Album;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Model.DAO.UserDAO.Musician;
import com.Element.Music.Model.DTO.MusicDTO.AlbumDTO;
import com.Element.Music.Model.DTO.MusicDTO.SongDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class MusicianDTO {

    private long id;

    private Genre genre;

    private String name;

    private Set<SongDTO> songDTOS;

    private Set<AlbumDTO> albumDTOS;

    private String weibo;

    private String description;

    private int liked;

    public MusicianDTO convertToDTO(Musician musician) {
        return null;
    }
}
