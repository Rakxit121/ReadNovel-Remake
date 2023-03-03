package com.system.lightnovel.controller;

import com.system.lightnovel.entity.Novel;
import com.system.lightnovel.pojo.NovelPojo;
import com.system.lightnovel.services.NovelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/novels")
public class NovelController {

    private final NovelService novelService;

//    @PostMapping
//    public ResponseEntity<Integer> createNovel(@RequestBody NovelPojo novelPojo) {
//        try {
//            Integer id = novelService.save(novelPojo);
//            return new ResponseEntity<>(id, HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>("Invalid request body: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }




    @PutMapping("/{id}")
    public ResponseEntity<String> updateNovel(@PathVariable Integer id, @RequestBody NovelPojo novelPojo) {
        try {
            novelPojo.setId(id);
            String result = novelService.update(novelPojo);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid request body: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Novel with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNovel(@PathVariable Integer id) {
        try {
            novelService.delete(id);
            return new ResponseEntity<>("Novel with ID " + id + " deleted", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid request body: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Novel with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Novel> getNovelById(@PathVariable Integer id) {
        try {
            Novel novel = novelService.fetchById(id);
            return new ResponseEntity<>(novel, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
