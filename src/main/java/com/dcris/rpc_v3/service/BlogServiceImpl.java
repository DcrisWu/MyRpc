package com.dcris.rpc_v3.service;

import com.dcris.rpc_v3.common.Blog;

import java.util.Random;

public class BlogServiceImpl implements BlogService {

    @Override
    public Blog getBlogById(Integer id) {
        System.out.println("查询成功，id为" + id);
        return Blog.builder().id(id).useId(new Random().nextInt()).title("title").build();
    }
}
