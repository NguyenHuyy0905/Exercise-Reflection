package com.nvhuy.service.impliment;

import com.nvhuy.dao.repository.FeedBackRepository;
import com.nvhuy.service.FeedBackService;
import com.nvhuy.model.FeedBack;

import java.util.List;

public class FeedBackServiceImpl implements FeedBackService {
    private FeedBackRepository feedBackRepository;
    public FeedBackServiceImpl() {
        this.feedBackRepository = new FeedBackRepository();
    }
    @Override
    public List<FeedBack> getAllFeedBacks() {
        return feedBackRepository.getAll();
    }
}
