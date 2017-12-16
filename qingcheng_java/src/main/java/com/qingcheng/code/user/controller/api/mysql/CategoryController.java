package com.qingcheng.code.user.controller.api.mysql;

import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.common.util.advance.filetype.FileType;
import com.qingcheng.code.user.dao.mysql.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.qingcheng.code.user.controller.util.DataUtil.composeResponseData;

@RestController
@Transactional
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 此方法用于,返回所有category信息
     *
     * @return 所有category信息
     */
    @RequestMapping(value = AppRestUrls.REST_URL_CATEGORY_ALL_LIST_REST_URL, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object  getAllCategories() {
        return composeResponseData(categoryMapper.getAllCategories());
    }
}
