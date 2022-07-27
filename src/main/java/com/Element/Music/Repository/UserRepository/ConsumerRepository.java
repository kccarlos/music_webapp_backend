package com.Element.Music.Repository.UserRepository;

import com.Element.Music.Model.DAO.UserDAO.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
//    Optional<Consumer> findByNameAndPassWord(String name, String password);

    Optional<Consumer> findByPhoneNumAndPassWord(String phoneNum, String password);

    Optional<Consumer> findByEmailAndPassWord(String Email, String password);

    Consumer findByEmail(String Email);

    Consumer findByIdAndDeletedIsFalse(Long Id);

    Consumer findByPhoneNum(String PhoneNum);
}
