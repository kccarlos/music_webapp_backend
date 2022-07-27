package com.Element.Music.Controller;

import com.Element.Music.Exception.PlaylistException;
import com.Element.Music.Exception.SongException;
import com.Element.Music.Model.DAO.MusicDAO.PlayList;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Model.DAO.UserDAO.Musician;
import com.Element.Music.Service.MusicianService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import com.Element.Music.Service.PlayListService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/playList")
public class PlayListController {

    private final PlayListService playListService;

    @Value("${playlist_pic.path}")
    private String playlistPic;

    @Value("${user.path}")
    private String userPath;

    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/playlistPic/**").addResourceLocations("file:" + playlistPic);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public Object getPlayListById(HttpServletRequest req) {
        String id = req.getParameter("id");
        return playListService.getPlaylistById(Long.parseLong(id));
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public Object getAllPlayList() {
        return  playListService.getAllPlaylist();
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addPlayList(HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String name = req.getParameter("name").trim();
        String description = req.getParameter("description").trim();
        String image = req.getParameter("imagePath").trim();
        String[] songId  = req.getParameterValues("songId");

        PlayList playlistRes = playListService.addPlaylist(name, description, songId, image);
        if (playlistRes != null) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "新建成功");
        } else {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "新建失败");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deletePlayList(HttpServletRequest req) {
        String id = req.getParameter("id");
        JSONObject jsonObject = new JSONObject();
        boolean deleteRes = playListService.deletePlayListById(Long.parseLong(id));
        if (deleteRes) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "删除成功");
        } else {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "删除失败");
        }
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/image/update", method = RequestMethod.POST)
    public Object updatePlayListPic(@RequestParam("imagePath") MultipartFile portraitFile, @RequestParam("id") long id) {
        JSONObject jsonObject = new JSONObject();

        if (portraitFile.isEmpty()) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "文件上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + portraitFile.getOriginalFilename();
        String filePath = userPath + "img";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        filePath += System.getProperty("file.separator") + "playlistPic";
        File file2 = new File(filePath);
        if (!file2.exists()) {
            file2.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storePortraitPath = "/img/playlistPic/" + fileName;

        try {
            portraitFile.transferTo(dest);
            PlayList playlist = PlayList.builder().build();
            playlist.setId(id);
            playlist.setRepresentImagePath(storePortraitPath);
            boolean res = playListService.updatePlayListPic(playlist);
            if (res) {
                jsonObject.put("code", 0);
                jsonObject.put("pic", storePortraitPath);
                jsonObject.put("msg", "上传成功");
            } else {
                jsonObject.put("code", 1);
                jsonObject.put("msg", "上传失败");
            }
        } catch (IOException | PlaylistException e) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "上传失败" + e.getMessage());
        }
        return jsonObject;
    }
}
