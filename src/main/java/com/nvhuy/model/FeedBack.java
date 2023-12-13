package com.nvhuy.model;

import com.nvhuy.common.annotations.Id;
import com.nvhuy.common.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.nvhuy.common.annotations.Column;
import com.nvhuy.common.consts.DataType;

@Data
@AllArgsConstructor
@Builder
@Table(tableName = "feedback")
public class FeedBack {
    @Id(columnName = "id", autoIncrement = false)
    private Long id;
    @Column(columnName = "content", type = DataType.TEXT)
    private String content;
}
