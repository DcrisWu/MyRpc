package com.dcris.rpc_v2.server;

import com.dcris.rpc_v2.common.Blog;
import com.dcris.rpc_v2.service.BlogService;

public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).useId(22).title("我的博客").build();
        System.out.println("client queried blog with id: " + id);
        return blog;
    }
}
