package com.Element.Music.Repository.UserRepository;

import com.Element.Music.Model.DAO.UserDAO.Musician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {
    List<Musician> findByDeletedIsFalseAndNameLike(String name);
}
