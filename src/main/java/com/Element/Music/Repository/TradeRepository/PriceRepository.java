package com.Element.Music.Repository.TradeRepository;

import com.Element.Music.Model.DAO.TradeDAO.Price;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
    Price findBySongAndDeletedIsFalse(Song song);
}
