package com.Element.Music.Service.Impl;

import com.Element.Music.Model.DTO.TradeDTO.TradeDTO;
import com.Element.Music.Service.ConsumerService;
import com.Element.Music.Service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;

public class TradeServiceImpl implements TradeService {
    @Autowired
    private final ConsumerService consumerService;

    @Autowired
    private final TradeService tradeService;

    public TradeServiceImpl(ConsumerService consumerService, TradeService tradeService) {
        this.consumerService = consumerService;
        this.tradeService = tradeService;
    }


    @Override
    public TradeDTO buySong(long songId, long consumerId) {
        return null;
    }

    @Override
    public TradeDTO refundSong(long songId, long consumerId) {
        return null;
    }

    @Override
    public TradeDTO buyMusician(long songId, long consumerId) {
        return null;
    }

    @Override
    public TradeDTO refundMusician(long songId, long consumerId) {
        return null;
    }
}
