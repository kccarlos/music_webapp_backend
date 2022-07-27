package com.Element.Music.Service;

import com.Element.Music.Exception.SongException;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Model.DAO.UserDAO.Musician;

import java.util.List;

public interface SongService {

    Song addSong(Song song);

    boolean deleteSong(long id);

    Song getSongById(long id);

    List<Song> getSongByName(String name);

    List<Song> getAllSong();

    List<Song> getSongsByMusician(long musicianId);

    boolean updateSong(Song song) throws SongException;

//    void updateSongPrice(Song song, Double price);

    boolean updateSongPic(Song song) throws SongException;

    boolean updateSongUrl(Song song) throws SongException;

    Musician getMusicianById(long id);

    String getSongPic(long id);
}
