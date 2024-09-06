package com.saccharine.crudapper.controller;

import com.saccharine.crudapper.entity.Post;
import com.saccharine.crudapper.entity.Rule;
import com.saccharine.crudapper.service.PostService;
import com.saccharine.crudapper.service.PostStatusService;
import com.saccharine.crudapper.service.PostTypeService;
import com.saccharine.crudapper.service.RuleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Controller
public class PostController {
    private final PostService postService;
    private final PostTypeService postTypeService;
    private final RuleService ruleService;
    private final PostStatusService postStatusService;

    public PostController(PostService postService, PostTypeService postTypeService, RuleService ruleService, PostStatusService postStatusService) {
        this.postService = postService;
        this.postTypeService = postTypeService;
        this.ruleService = ruleService;
        this.postStatusService = postStatusService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("post", postService.getById(id).orElseThrow());
        return "show";
    }

    @GetMapping("/createPost")
    public String viewCreatePost(Model model) {
        model.addAttribute("types", postTypeService.getAll());
        model.addAttribute("rules", ruleService.getAll());
        model.addAttribute("statues", postStatusService.getAll());
        return "createPost";
    }

    @PostMapping(path = "/savePost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String save(@ModelAttribute Post post,
                       @RequestParam("file") MultipartFile file,
                       HttpServletRequest request) throws IOException, ServletException {
        Set<Rule> ruleSet = new HashSet<>();
        request.getParts().forEach(part -> {
            if (part instanceof Rule) {
                ruleSet.add((Rule) part);
            } else {
                throw new IllegalArgumentException("Part is not a Rule");
            }
        });
        postService.updatePostFields(postService.create(post), ruleSet, file.getBytes());
        return "redirect:/";
    }

    @PostMapping("/updatePost")
    public String edit(@ModelAttribute Post post,
                       @RequestParam("file") MultipartFile file,
                       HttpServletRequest request) throws IOException, ServletException {
        Set<Rule> ruleSet = new HashSet<>();
        request.getParts().forEach(part -> {
            if (part instanceof Rule) {
                ruleSet.add((Rule) part);
            } else {
                throw new IllegalArgumentException("Part is not a Rule");
            }
        });
        postService.updatePostFields(post, ruleSet, file.getBytes());
        return "redirect:/";
    }

    @GetMapping("/{id}formUpdatePost")
    public String viewUpdatePost(@PathVariable String id, Model model) {
        model.addAttribute("post", postService.getById(id).orElseThrow());
        model.addAttribute("types", postTypeService.getAll());
        model.addAttribute("rules", ruleService.getAll());
        model.addAttribute("statues", postStatusService.getAll());
        return "formUpdatePost";
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Resource> download(@PathVariable("id") String id) {
        return postService.getById(id).<ResponseEntity<Resource>>map(value -> ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(value.getPhoto().length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(value.getPhoto()))).orElseThrow(null);
    }
}