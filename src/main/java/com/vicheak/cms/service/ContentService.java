package com.vicheak.cms.service;

import com.vicheak.cms.model.Content;

import java.util.List;

public interface ContentService {

    List<Content> findContents();

    void createNewContent(Content content);

    void deleteContentById(Integer id);

}
