package com.vicheak.cms.service.impl;

import com.vicheak.cms.model.Content;
import com.vicheak.cms.repository.ContentRepository;
import com.vicheak.cms.service.ContentService;
import com.vicheak.cms.util.SlugUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    @Override
    public List<Content> findContents() {
        return contentRepository.select();
    }

    @Override
    public void createNewContent(Content content) {
        content.setUuid(UUID.randomUUID().toString());
        content.setSlug(SlugUtil.toSlug(content.getTitle()));
        content.setCreatedAt(LocalDateTime.now());
        contentRepository.insert(content);
    }

    @Override
    public void deleteContentById(Integer id) {
        //boolean isDeleted = contentRepository.deleteById(id);

        boolean isDeleted = contentRepository.updateIsDeletedById(id, true);

        if (!isDeleted)
            throw new RuntimeException("Content is failed to be deleted!");
    }

    @Override
    public Content findContentById(Integer id) {
        return contentRepository.selectById(id)
                .orElseThrow(
                        () -> new RuntimeException("There is no content")
                );
    }

    @Override
    public void editContentById(Content editContent) {
        editContent.setSlug(SlugUtil.toSlug(editContent.getTitle()));
        contentRepository.update(editContent);
    }

}
