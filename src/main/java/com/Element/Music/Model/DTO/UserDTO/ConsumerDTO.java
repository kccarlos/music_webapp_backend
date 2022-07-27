package com.Element.Music.Model.DTO.UserDTO;

import com.Element.Music.Model.DAO.TradeDAO.ConsumerOrder;
import com.Element.Music.Model.DTO.MusicDTO.SongDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ConsumerDTO {
    private static final long serialVersionUID = -2214230518390003400L;

    private String nickName;

    private Set<SongDTO> collections;

    private Set<SongDTO> mySongs;

    private Set<ConsumerOrder> orders;
}
