package com.Element.Music;

import com.Element.Music.Emun.Genre;
import com.Element.Music.Emun.Sex;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Model.DAO.TradeDAO.Price;
import com.Element.Music.Model.DAO.UserDAO.Musician;
import com.Element.Music.Service.MusicianService;
import com.Element.Music.Service.SongService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private MusicianService musicianService;

    @Autowired
    private SongService songService;

    // @Test
    // void contextLoads() {
    //     Song song = Song.builder().description("description")
    //             .genre(Genre.JAZZ).lyric("lyric").songName("song").representImagePath("imagePath")
    //             .url("http://...com").build();
    //     Set<Song> songs = new HashSet<>();
    //     songs.add(song);

    //     Musician musician = Musician.builder().genre(Genre.JAZZ).description("这是一个歌手")
    //             .representativeWork("lujing").songs(songs).weibo("weibo").build();
    //     musician.setName("name");
    //     musician.setBirth(new Date(1995, 10, 29));
    //     musician.setEmail("740612415@qq.com");
    //     musician.setLocation("China");
    //     musician.setSex(Sex.valueOf("MALE"));
    //     musician.setPhoneNum("13151081251");
    //     musician.setPortrait("imagePath");
    //     musicianService.addMusician(musician);
    // }

//    @Test
//    void testGetPrice() {
//        Price price = songService.getSongById(4).getPrice();
//        System.out.println("原价是" + price.getOriginalPrice());
//    }
}
