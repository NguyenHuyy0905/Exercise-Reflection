package com.nvhuy.dao.repository;

import com.nvhuy.dao.JpaRepositoryClone.JpaRepositoryCloneImpl;
import com.nvhuy.model.FeedBack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedBackRepository extends JpaRepositoryCloneImpl<FeedBack, Long> {
    public FeedBackRepository() {
        super(FeedBack.class);
    }

    @Override
    protected List<FeedBack> rowMapper(ResultSet rs) {
        List<FeedBack> feedBackList = new ArrayList<>();
        try {
            while (rs.next()){
                feedBackList.add(FeedBack.builder()
                        .id(rs.getLong("id"))
                        .content(rs.getString("content"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return feedBackList;
    }
}
