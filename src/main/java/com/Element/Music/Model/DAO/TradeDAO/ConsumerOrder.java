package com.Element.Music.Model.DAO.TradeDAO;

import com.Element.Music.Model.DAO.BaseEntity;
import com.Element.Music.Model.DAO.TradeDAO.Price;
import com.Element.Music.Model.DAO.UserDAO.Consumer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "ConsumerOrder")
@Data
@Builder
@Table(name = "consumer_order")
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerOrder extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private Price price;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"consumer_order"})
    private Consumer consumer;

//    private Long songId;

//    private Long consumerId;

    private Long orderCode;

    private Double payPrice;//实付金额，考虑一下重新取名字

    private Double discount;//优惠金额，就是原价-实付金额

}
