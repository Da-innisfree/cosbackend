package com.example.demo.mapper;

import com.example.demo.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
//    List<ProductVO> productList();
    List<ProductVO> productList();
    ProductVO fetchProductByID(int product_seq);
    List<ProductColorLastVO> productCategory(String product_category);
    List<ColorVO> selectColor(int SEQ);
    List<ColorVO> color(int SEQ);
    List<ProductColorLastVO> colorLast();

}
