package com.Element.Music.Service;

import com.Element.Music.Model.DTO.TradeDTO.TradeDTO;

public interface TradeService {

    TradeDTO buySong(long songId, long consumerId);

    TradeDTO refundSong(long songId, long consumerId);

    TradeDTO buyMusician(long songId, long consumerId);

    TradeDTO refundMusician(long songId, long consumerId);
}
