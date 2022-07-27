package com.Element.Music.Service.Impl;

import com.Element.Music.Exception.SongException;
import com.Element.Music.Model.DAO.TradeDAO.Price;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Repository.TradeRepository.PriceRepository;
import com.Element.Music.Service.PriceService;
import com.Element.Music.Service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final SongService songService;

    public PriceServiceImpl(PriceRepository priceRepository, SongService songService) {
        this.priceRepository = priceRepository;
        this.songService = songService;
    }

    @Override
    public Price initializePrice(Long songId, Double originalPrice, Double rate)  {
        Price price = new Price();
        Song curSong = songService.getSongById(songId);
        price.setSong(curSong);
        price.setOriginalPrice(originalPrice);
        price.setRate(rate);
        return priceRepository.save(price);
    }

    @Override
    public Price getPriceById(Long songId){
        return priceRepository.findBySongAndDeletedIsFalse(songService.getSongById(songId));
    }

    @Override
    public Double getDisplayPriceById(Long songId){
        Price returnPrice = priceRepository.findBySongAndDeletedIsFalse(songService.getSongById(songId));
        if (returnPrice == null) return -1.0;
        return returnPrice.getShowPrice();
    }

    @Override
    public List<Price> getAllPrice(){
        return priceRepository.findAll();
    }

} 
