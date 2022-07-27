package com.Element.Music.Model.DAO.TradeDAO;

import com.Element.Music.Model.DAO.BaseEntity;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "Price")
@Table(name = "price")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Price extends BaseEntity {

//    private Long songId;

    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "song_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"price"})
    private Song song;

    private Double originalPrice;

    private Double rate;//平台收取的比例

    private Double showPrice;//含义为最终展示的价格，没有做discount之前

    public Double getShowPrice() {
        this.showPrice = originalPrice * (1 + rate);
        return showPrice;
    }

    @Override
    public String toString() {
        return showPrice.toString();
    }


}
