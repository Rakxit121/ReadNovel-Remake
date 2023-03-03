package com.system.lightnovel.services;

import com.system.lightnovel.entity.Bookmark;
import com.system.lightnovel.entity.Novel;
import com.system.lightnovel.pojo.NovelPojo;

import java.io.IOException;
import java.util.List;

public interface NovelService {

    //Retrieve ----------------------------------------------------
    List<Novel> fetchAll();

    Novel fetchById(Integer id);

    //Create ------------------------------------------------------
    NovelPojo save(NovelPojo novelPojo) throws IOException;

    //Update ------------------------------------------------------
    String update(NovelPojo novelPojo);

    //Delete ------------------------------------------------------

    void delete(Integer id);
}
