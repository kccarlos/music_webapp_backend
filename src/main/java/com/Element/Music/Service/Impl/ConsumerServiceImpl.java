package com.Element.Music.Service.Impl;

import com.Element.Music.Exception.ConsumerException;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Model.DAO.UserDAO.Consumer;
import com.Element.Music.Repository.UserRepository.ConsumerRepository;
import com.Element.Music.Service.ConsumerService;
import com.Element.Music.Service.SongService;
import com.Element.Music.Util.PaternUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository consumerRepository;

    private final SongService songService;

    private final MessageDigest MD5 = MessageDigest.getInstance("MD5");

    public ConsumerServiceImpl(ConsumerRepository consumerRepository, SongService songService) throws NoSuchAlgorithmException {

        this.consumerRepository = consumerRepository;

        this.songService = songService;

    }

    @Override
    public Consumer logout() {
        return null;
    }

    @Override
    public void delete(long consumerId) {
        consumerRepository.deleteById(consumerId);
    }

//    @Override
//    public Consumer addConsumer(Consumer consumer) throws ConsumerException, NoSuchAlgorithmException, UnsupportedEncodingException {
//        if (consumer.getPassWord() == null || !PaternUtil.isUserName(consumer.getName()) || !PaternUtil.isMobile(consumer.getPhoneNum())) {
//            if (consumer.getPassWord() == null) {
//                throw new ConsumerException("absence of password");
//            } else if (!PaternUtil.isMobile(consumer.getPhoneNum())) {
//                throw new ConsumerException("phoneNumber is illegal");
//            } else {
//                throw new ConsumerException("userName is illegal");
//            }
//        }
//        String pwd = consumer.getPassWord();
//        consumer.setPassWord(DigestUtils.md5DigestAsHex(pwd.getBytes()));
//        return consumerRepository.save(consumer);
//    }

    @Override
    public int addConsumer(Consumer consumer) throws ConsumerException, NoSuchAlgorithmException, UnsupportedEncodingException {
        if (consumerRepository.findByEmail(consumer.getEmail()) != null) {
            return 2;
        } else if (!PaternUtil.isUserName(consumer.getName()) || !PaternUtil.isEmail(consumer.getEmail())) {
            if (!PaternUtil.isEmail(consumer.getEmail()))  return 3;
            else return 4;
        }
        String pwd = consumer.getPassWord();
        consumer.setPassWord(DigestUtils.md5DigestAsHex(pwd.getBytes()));
        consumerRepository.save(consumer);
        return 0;
    }

    @Override
    public boolean addToWishlist(long consumerId, long songId) {
        Song song = songService.getSongById(songId);
        Consumer consumer = getConsumerByID(consumerId);
        if (consumer == null) return false;
        Set<Song> likes = consumer.getWishlist();
        if (!likes.add(song)) return false;
        consumer.setWishlist(likes);
        consumerRepository.save(consumer);
        return true;
    }

    @Override
    public boolean addToMySong(long consumerId, long songId) {
        Song song = songService.getSongById(songId);
        Consumer consumer = getConsumerByID(consumerId);
        if (consumer == null) return false;
        Set<Song> mySongs = consumer.getMySongs();
        if (!mySongs.add(song)) return false;
        consumer.setMySongs(mySongs);
        consumerRepository.save(consumer);
        return true;

//        Set<Song> myCollections = consumer.getMySongs();
//        myCollections.add(song);
//        consumer.setCollections(myCollections);
//        consumerRepository.save(consumer);
    }

    @Override
    public Set<Song> getMySong(long consumerId) {
        Consumer consumer = getConsumerByID(consumerId);
        return consumer.getMySongs();
    }

    @Override
    public Set<Song> getWishlist(long consumerId) {
        Consumer consumer = getConsumerByID(consumerId);
        return consumer.getWishlist();
    }

    @Override
    public void removeFromWishlist(long consumerId, long songId){
        Song song = songService.getSongById(songId);
        Optional<Consumer> optionalConsumer = consumerRepository.findById(consumerId);
        if (optionalConsumer.isPresent()) {
            Consumer consumer = optionalConsumer.get();
            Set<Song> likes = consumer.getWishlist();
            likes.remove(song);
            consumer.setWishlist(likes);
            consumerRepository.save(consumer);
        }
    }

    @Override
    public Consumer getConsumerByID(long id) {
        return consumerRepository.findById(id).orElse(null);
    }

    @Override
    @Deprecated
    public int verifyPasswdByUser(String user, String passWord) throws UnsupportedEncodingException {
        if (!PaternUtil.isMobile(user) && !PaternUtil.isEmail(user)) {
            return 1;
        } else if (consumerRepository.findByEmail(user) == null && consumerRepository.findByPhoneNum(user) == null) {
            return 2;
        } else if (PaternUtil.isMobile(user) && consumerRepository.
                findByPhoneNumAndPassWord(user, DigestUtils.md5DigestAsHex(passWord.getBytes())).orElse(null) == null) {
            return 3;
        } else if (PaternUtil.isEmail(user) && consumerRepository.
                findByEmailAndPassWord(user, DigestUtils.md5DigestAsHex(passWord.getBytes())).orElse(null) == null) {
            return 3;
        } else {
            return 0;
        }
    }

    @Override
    public Consumer getConsumerInfoOnceLogin(String user){
        return consumerRepository.findByEmail(user);
    }

//    @Override
//    @Deprecated
//    public int verifyPasswdByUserName(String userName, String passWord) throws UnsupportedEncodingException {
//        return PaternUtil.isUserName(userName) && consumerRepository.
//                findByNameAndPassWord(userName, DigestUtils.md5DigestAsHex(passWord.getBytes())).orElse(null) != null;
//    }

//    @Override
//    public boolean verifyPasswdByPhoneNum(String phoneNum, String passWord) throws UnsupportedEncodingException {
//        return PaternUtil.isMobile(phoneNum) && consumerRepository.
//                findByPhoneNumAndPassWord(phoneNum, DigestUtils.md5DigestAsHex(passWord.getBytes())).orElse(null) != null;
//    }
//
//    @Override
//    public boolean verifyPasswdByEmail(String Email, String passWord) throws UnsupportedEncodingException {
//        return PaternUtil.isEmail(Email) && consumerRepository.
//                findByEmailAndPassWord(Email, DigestUtils.md5DigestAsHex(passWord.getBytes())).orElse(null) != null;
//    }

    @Override
    public boolean updateConsumer(Consumer consumer) throws ConsumerException {
        if (consumer == null)
            throw new ConsumerException("更改用户接口缺失consumer");
        Optional<Consumer> consumerOptional = consumerRepository.findById(consumer.getId());
        if (consumerOptional.isEmpty()) return false;
        if (!consumerOptional.get().isDeleted()) {
            Consumer consumer1 = consumerOptional.get();
            consumer1.setId(consumer.getId());
            consumer1.setName(consumer.getName());
            consumer1.setPassWord(consumer.getPassWord());
            consumer1.setSex(consumer.getSex());
            consumer1.setPhoneNum(consumer.getPhoneNum());
            consumer1.setEmail(consumer.getEmail());
            consumer1.setBirth(consumer.getBirth());
            consumer1.setLocation(consumer.getLocation());
            consumer1.setPortrait(consumer.getPortrait());
            consumerRepository.save(consumer1);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUserPicture(Consumer consumer) throws ConsumerException {
        if (consumer.getId() == null || consumer.getPortrait() == null) {
            if (consumer.getId() == null) throw new ConsumerException("更新头像缺失ID");
            if (consumer.getPortrait() == null) throw new ConsumerException("更新头像缺失路径");
        }
        Optional<Consumer> consumerOptional = consumerRepository.findById(consumer.getId());
        if (consumerOptional.isEmpty()) return false;
        Consumer consumer1 = consumerOptional.get();
        consumer1.setPortrait(consumer.getPortrait());
        consumerRepository.save(consumer1);
        return true;
    }

    @Override
    public List<Consumer> getAllUser() { return consumerRepository.findAll(); }

    @Override
    public boolean removeById(long id) {
        Optional<Consumer> consumerOptional = consumerRepository.findById(id);
        if (consumerOptional.isEmpty()) return false;
        Consumer consumer = consumerOptional.get();
        consumer.setDeleted(true);
        consumerRepository.save(consumer);
        return true;
    }
}