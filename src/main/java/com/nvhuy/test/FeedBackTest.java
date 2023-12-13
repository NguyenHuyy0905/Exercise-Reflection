package com.nvhuy.test;

import com.nvhuy.model.FeedBack;
import com.nvhuy.service.impliment.FeedBackServiceImpl;

import java.util.List;

public class FeedBackTest {
    public static void main(String[] args) {
        FeedBackServiceImpl service = new FeedBackServiceImpl();
        List<FeedBack> feedBackList = service.getAllFeedBacks();
        System.out.println(feedBackList);
    }
}
