package com.nvhuy.controller;

import com.nvhuy.service.FeedBackService;
import com.nvhuy.service.impliment.FeedBackServiceImpl;

public class FeedBackController {
    private final FeedBackService feedBackService;

    public FeedBackController(FeedBackServiceImpl feedBackService) {
        this.feedBackService = feedBackService;
    }
}
