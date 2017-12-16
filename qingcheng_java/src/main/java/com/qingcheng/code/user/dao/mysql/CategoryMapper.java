package com.qingcheng.code.user.dao.mysql;

import com.qingcheng.code.user.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.LinkedList;


@Component
@Mapper
public interface CategoryMapper {
    @Select("select distinct categoryId,categoryName,categoryNameCn,categoryIconName from Category where categoryIsOnLine!= 0 order by categoryId")
    public LinkedList<Category> getAllCategories();
}
