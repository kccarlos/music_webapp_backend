package com.Element.Music.Service.Impl;

import com.Element.Music.Exception.MusicianException;
import com.Element.Music.Model.DAO.UserDAO.Musician;
import com.Element.Music.Repository.UserRepository.MusicianRepository;
import com.Element.Music.Service.MusicianService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicianServiceImpl implements MusicianService {

    private final MusicianRepository musicianRepository;

    public MusicianServiceImpl(MusicianRepository musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    @Override
    public Musician addMusician(Musician musician) {

        return musicianRepository.save(musician);
    }

    @Override
    public Musician getMusicianById(long id) {
        Optional<Musician> optionalMusician = musicianRepository.findById(id);
        return optionalMusician.orElse(null);
    }

    @Override
    public List<Musician> getMusicianByName(String name) throws MusicianException {
        if (name == null) throw new MusicianException("通过名字获取音乐家接口缺失name");
        return musicianRepository.findByDeletedIsFalseAndNameLike("%" + name + "%");
    }

    @Override
    public boolean updateMusicianMsg(Musician musician) throws MusicianException {
        if (musician == null) throw new MusicianException("更改歌手接口缺失musician");
        Optional<Musician> musicianOptional = musicianRepository.findById(musician.getId());
        if (musicianOptional.isEmpty()) return false;
        if (!musicianOptional.get().isDeleted()) {
            Musician musician1 = musicianOptional.orElse(null);
            musician1.setId(musician.getId());
            musician1.setName(musician.getName());
            musician1.setSex(musician.getSex());
            musician1.setPortrait(musician.getPortrait());
            musician1.setBirth(musician.getBirth());
            musician1.setLocation(musician.getLocation());
            musician1.setDescription(musician.getDescription());
            musicianRepository.save(musician1);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateSingerPic(Musician musician) throws MusicianException {
        if (musician == null || musician.getPortrait() == null) {
            if (musician == null) throw new MusicianException("更改图片接口缺失musician");
            else throw new MusicianException("更改图片接口缺失portrait");
        }
        Optional<Musician> musicianOptional = musicianRepository.findById(musician.getId());
        if (musicianOptional.isEmpty()) return false;
        if (!musicianOptional.get().isDeleted()) {
            Musician musician1 = musicianOptional.get();
            musician1.setPortrait(musician.getPortrait());
            musicianRepository.save(musician1);
            return true;
        }
        return false;
    }

    @Override
    public List<Musician> getAllMusician() {
        return musicianRepository.findAll();
    }

    @Override
    public boolean removeById(long id) {
        Optional<Musician> musicianOptional = musicianRepository.findById(id);
        if (musicianOptional.isEmpty()) return false;
        Musician musician = musicianOptional.get();
        musician.setDeleted(true);
        musicianRepository.save(musician);
        return true;
    }

    @Override
    public String getMusicianPortrait(long id) {
        Musician musician = musicianRepository.findById(id).orElse(null);
        if (musician == null) return "";
        String portraitPath = musician.getPortrait();
        if (portraitPath == null || portraitPath.length() == 0) return "";
        return portraitPath;
    }
}