package com.system.lightnovel.services.Impl;

import com.system.lightnovel.entity.Novel;
import com.system.lightnovel.pojo.NovelPojo;
import com.system.lightnovel.repo.NovelRepo;
import com.system.lightnovel.services.NovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class NovelServiceImpl implements NovelService {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "\\images\\novel\\";

    private final NovelRepo novelRepo;


    @Override
    public List<Novel> fetchAll() {
        return this.novelRepo.findAll();
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
    public NovelPojo save(NovelPojo novelPojo) throws IOException {
        Novel novel = new Novel();
        novel.setId(novelPojo.getId());
        novel.setTitle(novelPojo.getTitle());
        novel.setDescription(novelPojo.getDescription());
        novel.setAuthor(novelPojo.getAuthor());
        novel.setRating(novelPojo.getRating());
        novel.setGenre(novelPojo.getGenre());
        novel.setStatus(novelPojo.getStatus());
        novel.setLatestChapter(novelPojo.getLatestChapter());
//        novel.setImageData(novelPojo.getImageData());

        if(novelPojo.getImageData() != null && !novelPojo.getImageData().isEmpty()){
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, novelPojo.getImageData().getOriginalFilename());
            Files.write(fileNameAndPath, novelPojo.getImageData().getBytes());

            novel.setImageData(novelPojo.getImageData().getOriginalFilename());
        }

        novelRepo.save(novel);
        return new NovelPojo(novel);
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
            novel.setImageBase64(getImageBase64(novel.getImageData()));
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
//}


    public String getImageBase64(String fileName) {
        if (fileName!=null) {
            String filePath = System.getProperty("user.dir")+"/images/novel/";
            File file = new File(filePath + fileName);
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return Base64.getEncoder().encodeToString(bytes);
        }
        return null;
    }
}
