package com.jk.blog.service;

import com.jk.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: Jiakun
 * @create: 2020-02-13 11:18
 **/
public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag updateTag(Long id,Tag tag);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

    void deleteTag(Long id);
}