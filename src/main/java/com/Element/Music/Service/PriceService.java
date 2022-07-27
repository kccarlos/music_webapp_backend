package com.Element.Music.Service;

import com.Element.Music.Exception.SongException;
import com.Element.Music.Model.DAO.TradeDAO.Price;

import java.util.List;

public interface PriceService {
    Price initializePrice(Long songId, Double originalPrice, Double rate);
    Double getDisplayPriceById(Long songId);
    List<Price> getAllPrice();
    Price getPriceById(Long songId);
}
