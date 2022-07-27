package com.Element.Music.Service.Impl;

import com.Element.Music.Model.DAO.TradeDAO.Purse;
import com.Element.Music.Model.DAO.UserDAO.Consumer;
import com.Element.Music.Repository.TradeRepository.PurseRepository;
import com.Element.Music.Service.ConsumerService;
import com.Element.Music.Service.PurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurseServiceImpl implements PurseService{
    private final PurseRepository purseRepository;
    private final ConsumerService consumerService;

    public PurseServiceImpl(PurseRepository purseRepository, ConsumerService consumerService) {
        this.purseRepository = purseRepository;
        this.consumerService = consumerService;
    }

    @Override
    public Purse initializePurse(Long consumer_id) {
        Purse purse = new Purse();
        purse.setConsumer(consumerService.getConsumerByID(consumer_id));
        double initialBalance = 100.00;
        purse.setBalance(initialBalance);
        return purseRepository.save(purse);
    }

    @Override
    public Purse getPurseById(Long consumer_id){
        Consumer targetConsumer = consumerService.getConsumerByID(consumer_id);
        return purseRepository.findByConsumerAndDeletedIsFalse(targetConsumer);
    }

    @Override
    public Double getBalanceByID(Long consumer_id){
        Consumer targetConsumer = consumerService.getConsumerByID(consumer_id);
        Purse res = purseRepository.findByConsumerAndDeletedIsFalse(targetConsumer);
        if (res == null) return -1.0;
        return res.getBalance();
    }

    @Override
    public Purse addBalanceById(Long consumer_id, Double addValue){
        Consumer targetConsumer = consumerService.getConsumerByID(consumer_id);
        Purse consumerPurse = purseRepository.findByConsumerAndDeletedIsFalse(targetConsumer);
        if(consumerPurse == null){
            Purse purse = new Purse();
            purse.setConsumer(targetConsumer);
            purse.setBalance(addValue);
            return purseRepository.save(purse);
        }
        double newBalance = consumerPurse.getBalance() + addValue;
        consumerPurse.setBalance(newBalance);
        return purseRepository.save(consumerPurse);
    }

    @Override
    public Purse withdrawBalanceById(Long consumer_id, Double withdrawValue){
        Consumer targetConsumer = consumerService.getConsumerByID(consumer_id);
        Purse consumerPurse = purseRepository.findByConsumerAndDeletedIsFalse(targetConsumer);
        double newBalance = consumerPurse.getBalance() - withdrawValue;
        if (newBalance < 0) return null;
        consumerPurse.setBalance(newBalance);
        return purseRepository.save(consumerPurse);
    }
}
