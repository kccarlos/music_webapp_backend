package com.Element.Music.Service;

import com.Element.Music.Exception.MusicianException;
import com.Element.Music.Model.DAO.UserDAO.Musician;

import java.util.List;

public interface MusicianService {
    Musician addMusician(Musician musician);

    Musician getMusicianById(long id);

    List<Musician> getMusicianByName(String name) throws MusicianException;

    boolean updateMusicianMsg(Musician musician) throws MusicianException;

    boolean updateSingerPic(Musician musician) throws MusicianException;

    List<Musician> getAllMusician();

    boolean removeById(long id);

    String getMusicianPortrait(long id);
}
