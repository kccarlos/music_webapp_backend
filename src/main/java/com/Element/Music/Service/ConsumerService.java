package com.Element.Music.Service;

import com.Element.Music.Exception.ConsumerException;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Model.DAO.UserDAO.Consumer;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

public interface ConsumerService {

    Consumer logout();

    void delete(long ConsumerId);

//    boolean verifyPasswdByEmail(String Email, String passWord) throws UnsupportedEncodingException;

    int addConsumer(Consumer consumer) throws ConsumerException, NoSuchAlgorithmException, UnsupportedEncodingException;

    boolean addToWishlist(long consumerId, long songId);

    void removeFromWishlist(long consumerId, long songId);

    Set<Song> getWishlist(long consumerId);

    boolean addToMySong(long consumerId, long songId);

    Set<Song> getMySong(long consumerId);

    int verifyPasswdByUser(String user, String passWord) throws UnsupportedEncodingException;

    boolean updateConsumer(Consumer consumer) throws ConsumerException;

    boolean updateUserPicture(Consumer consumer) throws ConsumerException;

    Consumer getConsumerByID(long id);

    List<Consumer> getAllUser();

    boolean removeById(long id);

    Consumer getConsumerInfoOnceLogin(String user);
}
