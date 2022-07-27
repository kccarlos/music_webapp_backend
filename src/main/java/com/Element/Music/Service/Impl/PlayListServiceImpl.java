package com.Element.Music.Service.Impl;

import com.Element.Music.Exception.PlaylistException;
import com.Element.Music.Model.DAO.MusicDAO.PlayList;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Model.DAO.UserDAO.Musician;
import com.Element.Music.Repository.MusicRepository.PlaylistRepository;
import com.Element.Music.Service.SongService;
import com.Element.Music.Service.PlayListService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlayListServiceImpl implements PlayListService {

    private final PlaylistRepository playlistRepository;

    private final SongService songService;

    public PlayListServiceImpl(SongService songService, PlaylistRepository playlistRepository) {

        this.playlistRepository = playlistRepository;

        this.songService = songService;
    }

    @Override
    public PlayList addPlaylist(String ListName, String description, String[] songId, String image) {
        Set<Song> songs = new HashSet<>();
        PlayList playList = new PlayList();
        for(String id:songId){
            Song curSong = songService.getSongById(Long.parseLong(id));
            songs.add(curSong);
        }
        playList.setListName(ListName);
        playList.setDescription(description);
        playList.setSongs(songs);
        playList.setRepresentImagePath(image);
        return playlistRepository.save(playList);
    }

    @Override
    public PlayList getPlaylistById(Long id){
        Optional<PlayList> playListOptional = playlistRepository.findByIdAndDeletedIsFalse(id);
        return playListOptional.orElse(null);
    }

    @Override
    public List<PlayList> getAllPlaylist(){
        return playlistRepository.findAll();
    }

    @Override
    public boolean deletePlayListById(Long id) {
        Optional<PlayList> playlistOptional = playlistRepository.findByIdAndDeletedIsFalse(id);
        if (playlistOptional.isEmpty()) return false;
        PlayList playlist = playlistOptional.get();
        playlist.setDeleted(true);
        playlistRepository.save(playlist);
        return true;
    }

    @Override
    public boolean updatePlayListPic(PlayList playlist) throws PlaylistException {
        if (playlist == null || playlist.getRepresentImagePath() == null) {
            if (playlist == null)
                throw new PlaylistException("更改图片接口缺失musician");
            else throw new PlaylistException("更改图片接口缺失portrait");
        }
        Optional<PlayList> playlistOptional = playlistRepository.findByIdAndDeletedIsFalse(playlist.getId());
        if (playlistOptional.isEmpty()) return false;
        if (!playlistOptional.get().isDeleted()) {
            PlayList playlist1 = playlistOptional.get();
            playlist1.setRepresentImagePath(playlist.getRepresentImagePath());
            playlistRepository.save(playlist1);
            return true;
        }
        return false;
    }
}
