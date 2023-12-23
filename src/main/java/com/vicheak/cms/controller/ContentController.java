package com.vicheak.cms.controller;

import com.vicheak.cms.model.Content;
import com.vicheak.cms.service.ContentService;
import com.vicheak.cms.service.FileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Controller
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;
    private final FileService fileService;

    @GetMapping
    public String viewContent(ModelMap modelMap) {
        modelMap.addAttribute("contents", contentService.findContents());
        return "content/main";
    }

    @GetMapping("/form")
    public String viewContentForm(Content content,
                                  ModelMap modelMap) {
        modelMap.addAttribute("content", content);
        return "content/form";
    }

    @PostMapping("/create")
    public String createNewContent(@RequestPart("file") MultipartFile file,
                                   @Valid Content content,
                                   BindingResult bindingResult,
                                   ModelMap modelMap) {
        if (file.isEmpty()) {
            //System.out.println("working");

            bindingResult.addError(new FieldError("content", "thumbnail",
                    "Content thumbnail must not be blank!"));

            return "content/form";
        }

        //System.out.println("Image file : " + file.getOriginalFilename());

        //ignore content thumbnail validation
        if (bindingResult.hasErrors()) {
            //System.out.println("Has errors!");

            return "content/form";
        }

        /*System.out.println("Content title : " + content.getTitle());
        System.out.println("Content keyword : " + content.getKeyword());
        System.out.println("Content description : " + content.getDescription());
        System.out.println("Content is deleted : " + content.getIsDeleted());*/

        //handle thumbnail image file
        String uniqueFileName = fileService.uploadThumbnail(file);
        if (Objects.isNull(uniqueFileName)) {
            modelMap.addAttribute("isSupported", false);
            return "content/form";
        }

        //handle content object with content service
        content.setThumbnail(uniqueFileName);
        contentService.createNewContent(content);

        return "redirect:/content";
    }

}
