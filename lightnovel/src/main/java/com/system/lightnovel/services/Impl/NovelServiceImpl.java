package com.system.lightnovel.services.Impl;

import com.system.lightnovel.entity.Novel;
import com.system.lightnovel.pojo.NovelPojo;
import com.system.lightnovel.repo.NovelRepo;
import com.system.lightnovel.services.NovelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NovelServiceImpl implements NovelService {

    private final NovelRepo novelRepo;

    public NovelServiceImpl(NovelRepo novelRepo) {
        this.novelRepo = novelRepo;
    }

    @Override
    public List<Novel> fetchAll() {
        return novelRepo.findAll();
    }

    @Override
    public Novel fetchById(Integer id) {
        Optional<Novel> optionalNovel = novelRepo.findById(id);
        if (optionalNovel.isPresent()) {
            return optionalNovel.get();
        } else {
            throw new IllegalArgumentException("Novel with ID " + id + " not found.");
        }
    }

    @Override
    public String save(NovelPojo novelPojo) {
        Novel novel = new Novel();
        novel.setTitle(novelPojo.getTitle());
        novel.setDescription(novelPojo.getDescription());
        novel.setAuthor(novelPojo.getAuthor());
        novel.setRating(novelPojo.getRating());
        novel.setGenre(novelPojo.getGenre());
        novel.setStatus(novelPojo.getStatus());
        novel.setLatestChapter(novelPojo.getLatestChapter());
        novel.setImageData(novelPojo.getImageData());
        novelRepo.save(novel);
        return "Novel saved successfully";
    }

    @Override
    public String update(NovelPojo novelPojo) {
        Optional<Novel> existingNovel = novelRepo.findById(novelPojo.getId());
        if (existingNovel.isPresent()) {
            Novel novel = existingNovel.get();
            novel.setId(novelPojo.getId()); // set the ID of the novel
            novel.setTitle(novelPojo.getTitle());
            novel.setDescription(novelPojo.getDescription());
            novel.setAuthor(novelPojo.getAuthor());
            novel.setRating(novelPojo.getRating());
            novel.setGenre(novelPojo.getGenre());
            novel.setStatus(novelPojo.getStatus());
            novel.setLatestChapter(novelPojo.getLatestChapter());
            novel.setImageData(novelPojo.getImageData());
            novelRepo.save(novel);
            return "Novel updated successfully";
        } else {
            throw new IllegalArgumentException("Cannot update non-existent novel with ID " + novelPojo.getId());
        }
    }


    @Override
    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Novel> existingNovel = novelRepo.findById(id);
        if (existingNovel.isPresent()) {
            novelRepo.delete(existingNovel.get());
        } else {
            throw new IllegalArgumentException("Cannot delete non-existent novel with ID " + id);
        }
    }
}
