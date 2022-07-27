package com.Element.Music.Model.DTO.MusicDTO;

import com.Element.Music.Model.DAO.MusicDAO.Album;

import com.Element.Music.Model.DTO.UserDTO.MusicianDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class AlbumDTO {

    private Set<SongDTO> songDTOSs;

    private MusicianDTO musicianDTO;

    private Date releaseDate;

    public AlbumDTO convertToDTO(Album album) {
        return null;
    }
}
