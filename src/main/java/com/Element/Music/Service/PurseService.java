package com.Element.Music.Service;

import com.Element.Music.Exception.ConsumerException;
import com.Element.Music.Model.DAO.TradeDAO.Purse;
import com.Element.Music.Model.DAO.UserDAO.Consumer;

import java.util.List;

public interface PurseService {
    Purse initializePurse(Long consumer_id);
    Double getBalanceByID(Long consumer_id);
    Purse addBalanceById(Long consumer_id, Double addValue);
    Purse withdrawBalanceById(Long consumer_id, Double withdrawValue);
    Purse getPurseById(Long consumer_id);
}
