package com.Element.Music.Repository.MusicRepository;

import com.Element.Music.Model.DAO.MusicDAO.PlayList;
import com.Element.Music.Model.DAO.UserDAO.Musician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<PlayList, Long> {
    Optional<PlayList> findByIdAndDeletedIsFalse(Long id);
}
