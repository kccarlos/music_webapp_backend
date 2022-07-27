package com.Element.Music.Repository.TradeRepository;

import com.Element.Music.Model.DAO.TradeDAO.ConsumerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<ConsumerOrder, Long> {
    List<ConsumerOrder> findAllByConsumerIdAndDeletedIsFalse(Long consumerId);
}
