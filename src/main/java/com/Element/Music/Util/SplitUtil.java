package com.Element.Music.Util;

import com.Element.Music.Emun.Genre;

import java.util.LinkedList;
import java.util.List;

public class SplitUtil {
    public static List<Genre> musicTypeSplit(String musicTypeString) {
        if (musicTypeString == null || musicTypeString.length() == 0)
            return new LinkedList<>();
        String[] musicTypes = musicTypeString.split("/");
        List<Genre> genreList = new LinkedList<>();
        for (String s : musicTypes) {
            genreList.add(Genre.valueOf(s));
        }
        return genreList;
    }
}
