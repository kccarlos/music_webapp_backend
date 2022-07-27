package com.Element.Music.Service;

import com.Element.Music.Exception.PlaylistException;
import com.Element.Music.Model.DAO.MusicDAO.PlayList;

import java.util.List;

public interface PlayListService {
    PlayList addPlaylist(String ListName, String description, String[] songId, String image);
    PlayList getPlaylistById(Long id);
    List<PlayList> getAllPlaylist();
    boolean deletePlayListById(Long id);
    boolean updatePlayListPic(PlayList playlist) throws PlaylistException;
}
