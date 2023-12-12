package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.example.common.annotations.Column;
import org.example.common.annotations.Id;
import org.example.common.annotations.Table;
import org.example.common.consts.DataType;

import java.sql.Date;
@Data
@AllArgsConstructor
@Builder
@Table(tableName = "product")
public class Product {
    @Id(columnName = "id", autoIncrement = false)
    private Long id;
    @Column(columnName = "name", type = DataType.VARCHAR)
    private String name;
    @Column(columnName = "price", type = DataType.DOUBLE)
    private double price;
    @Column(columnName = "createAt", type = DataType.DATE)
    private Date createAt;
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.createAt = new Date(System.currentTimeMillis());
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createAt=" + createAt +
                "}\n";
    }
}
