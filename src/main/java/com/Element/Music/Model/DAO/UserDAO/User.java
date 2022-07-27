package com.Element.Music.Model.DAO.UserDAO;

import com.Element.Music.Emun.Sex;
import com.Element.Music.Model.DAO.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class User extends BaseEntity {

    @NonNull
    protected String name;

    @NonNull
    protected Date birth;

    @NonNull
    protected String location;

    protected String portrait;

    protected Sex sex;

    protected String phoneNum;

    protected String email;

    protected boolean deleted;
}