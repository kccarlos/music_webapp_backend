package com.Element.Music.Controller;

import com.Element.Music.Exception.SongException;
import com.Element.Music.Model.DAO.MusicDAO.Song;
import com.Element.Music.Service.SongService;
import com.Element.Music.Util.FileUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/song")
public class SongController {

    private final SongService songService;

    @Value("${song_pic.path}")
    private String songPicture;

    @Value("${song.path}")
    private String songPath;

    @Value("${user.path}")
    private String userPath;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大15M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(15, DataUnit.MEGABYTES));
        /// 设置总上传数据总大小15M
        factory.setMaxRequestSize(DataSize.of(15, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songPic/**").addResourceLocations("file:" + songPicture);
            registry.addResourceHandler("/song/**").addResourceLocations("file:" + songPath);
        }
    }

    //    添加歌曲
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addSong(HttpServletRequest req, @RequestParam("file") MultipartFile mpfile) {
        JSONObject jsonObject = new JSONObject();
        String musicianId = req.getParameter("musicianId").trim();
        //String musicianName = req.getParameter("musicianName").trim();
        String songName = req.getParameter("songName").trim();
        String description = req.getParameter("description").trim();
        String pic = req.getParameter("pic").trim();
        String lyric = req.getParameter("lyric").trim();

        if (mpfile.isEmpty()) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "音乐上传失败！");
            return jsonObject;
        }
        String fileName = mpfile.getOriginalFilename();
        String filePath = userPath + "song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        pic = "/songPic/" + pic;
        try {
            mpfile.transferTo(dest);
            Song song = Song.builder().musician(songService.getMusicianById(Long.parseLong(musicianId))).description(description)
                    .songName(songName).lyric(lyric).url(storeUrlPath).representImagePath(pic).build();
            Song res = songService.addSong(song);
            if (res != null) {
                jsonObject.put("code", 0);
                jsonObject.put("urlPath", storeUrlPath);
                jsonObject.put("msg", "上传成功");
            } else {
                jsonObject.put("code", 1);
                jsonObject.put("msg", "上传失败");
            }
        } catch (Exception e) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "上传失败" + e.getMessage());
        }
        return jsonObject;
    }

    //    返回所有歌曲
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Object allSong() {
        return songService.getAllSong();
    }


    //    返回指定歌曲ID的歌曲
    @RequestMapping(value = "/songId/detail", method = RequestMethod.GET)
    public Object songOfId(HttpServletRequest req) {
        String id = req.getParameter("id");
        return songService.getSongById(Long.parseLong(id));
    }

    //    //    返回指定歌手ID的歌曲
//    @RequestMapping(value = "/singer/detail", method = RequestMethod.GET)
//    public Object songOfSingerId(HttpServletRequest req){
//        String singerId = req.getParameter("singerId");
//        return songService.getSongsByMusician(Integer.parseInt(singerId));
//    }
/*
    //    返回指定歌手名的歌曲
    @RequestMapping(value = "/singerName/detail", method = RequestMethod.GET)
    public Object songOfSingerName(HttpServletRequest req){
        String name = req.getParameter("name");
        return songService.songOfSingerName('%'+ name + '%');
    }
*/
    //    返回指定歌曲名的歌曲
    @RequestMapping(value = "/songName/detail", method = RequestMethod.GET)
    public Object songOfName(HttpServletRequest req) {
        String name = req.getParameter("name").trim();
        return songService.getSongByName(name);
    }

    //    删除歌曲
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deleteSong(HttpServletRequest req) {
        String id = req.getParameter("id");
        JSONObject jsonObject = new JSONObject();
        if (!songService.deleteSong(Long.parseLong(id))) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "删除失败");
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "删除成功");
        }
        return jsonObject;
    }

    //    更新歌曲信息
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateSongMsg(HttpServletRequest req) throws SongException {
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String musicianId = req.getParameter("musicianId").trim();
        //String musicianName = req.getParameter("musicianName").trim();
        String songName = req.getParameter("songName").trim();
        String description = req.getParameter("description").trim();
        String lyric = req.getParameter("lyric").trim();

        Song song = Song.builder().musician(songService.getMusicianById(Long.parseLong(musicianId))).
                description(description).songName(songName).lyric(lyric).build();
        song.setId(Long.parseLong(id));
        song.setLyric(lyric);

        boolean res = songService.updateSong(song);
        if (res) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改成功");
        } else {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改失败");
        }
        return jsonObject;
    }

    //    更新歌曲图片
    @ResponseBody
    @RequestMapping(value = "/img/update", method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") long id) {
        JSONObject jsonObject = new JSONObject();

        if (urlFile.isEmpty()) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "音乐上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = userPath + "img";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        filePath += System.getProperty("file.separator") + "songPic";
        File file2 = new File(filePath);
        if (!file2.exists()) {
            file2.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeImagePath = "/img/songPic/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = Song.builder().representImagePath(storeImagePath).build();
            song.setId(id);
            boolean res = songService.updateSongPic(song);
            if (res) {
                jsonObject.put("code", 0);
                jsonObject.put("portraitPath", storeImagePath);
                jsonObject.put("msg", "上传成功");
            } else {
                jsonObject.put("code", 1);
                jsonObject.put("msg", "上传失败");
            }
        } catch (Exception e) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "上传失败" + e.getMessage());
        }
        return jsonObject;
    }

    //    更新歌曲URL
    @Deprecated
    @ResponseBody
    @RequestMapping(value = "/url/update", method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") long id) {
        JSONObject jsonObject = new JSONObject();

        if (urlFile.isEmpty()) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "音乐上传失败！");
            return jsonObject;
        }
        String fileName = urlFile.getOriginalFilename();
        String filePath = userPath + "song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeUrlPath);
            boolean res = songService.updateSongUrl(song);
            if (res) {
                jsonObject.put("code", 0);
                jsonObject.put("urlPath", storeUrlPath);
                jsonObject.put("msg", "上传成功");
            } else {
                jsonObject.put("code", 1);
                jsonObject.put("msg", "上传失败");
            }
        } catch (Exception e) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "上传失败" + e.getMessage());
        }
        return jsonObject;
    }

//    @RequestMapping(value = "/download")
//    public void download(@RequestParam("fileName") String filename) throws IOException {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletResponse response = requestAttributes.getResponse();
//        String type = new MimetypesFileTypeMap().getContentType(filename);
//        response.setHeader("Content-type", type);
//        String header = new String(filename.getBytes("utf-8"), "iso-8859-1");
//        response.setHeader("Content-Disposition", "attachment;filename=" + header);
//        FileUtils.download(filename, response);
//    }

    @RequestMapping(value = "/songPicture")
    public Object getSongPicture(HttpServletRequest req) {
        String id = req.getParameter("id");
        String path = songService.getSongPic(Long.parseLong(id));
        JSONObject jsonObject = new JSONObject();
        if (path == null || path.equals("")) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "id或者图片错误");
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", path);
        }
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/songsByMusicianId")
    public Object getSongsByMusicianId(HttpServletRequest req){
        String id = req.getParameter("id");
        return songService.getSongsByMusician(Long.parseLong(id));
    }
}
