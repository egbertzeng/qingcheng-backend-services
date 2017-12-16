package com.qingcheng.code.user.controller.api.mysql;

import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.common.util.advance.filetype.FileType;
import com.qingcheng.code.user.bean.Course;
import com.qingcheng.code.user.dao.mysql.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.qingcheng.code.common.constant.AppRestUrls.REST_URL_QINGCHENG_SQL_COURSE_GET_FOLLOWER_ORDER_LIST;
import static com.qingcheng.code.user.controller.util.DataUtil.composeResponseData;

@RestController
@Transactional
public class CourseController {
    @Autowired
    private CourseMapper courseMapper;
    /**
     * 一、普通用户部分
     */
    /**
     * 此方法用于,返回所有上传到HDFS中的课程类别
     *
     * @return 所有课程类别信息
     */
    @RequestMapping(value = AppRestUrls.REST_URL_courseAllListRestUrl, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object getAllCouses() {
        return composeResponseData(courseMapper.getAllCouses());
    }


    /**
     * 此方法用于返回，通过search途径获取的album信息
     *
     * @param k 搜索关键字
     * @return 搜索到的album信息
     */
    @RequestMapping(value = AppRestUrls.REST_URL_getCourseListBySearchKeyRestUrl, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object getCousesBySearchKey(@RequestParam(AppRestUrls.REST_URL_getCourseListBySearchKeyRestUrlPara_key) String k) {
        return composeResponseData(courseMapper.getCousesBySearchKey(k));

    }

    /**
     * 此方法用于返回，通过categoryid获取album信息
     *
     * @param categoryid 类别id
     * @return 搜索到的album信息
     */
    @RequestMapping(value = AppRestUrls.REST_URL_getCourseListByCategoryIdRestUrl, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object getCousesByCategoryId(@RequestParam(AppRestUrls.REST_URL_getCourseListByCategoryIdRestUrl_categoryid) String categoryid) {
        return composeResponseData(courseMapper.getCousesBySearchCategoryid(categoryid));

    }

    /**
     * 此方法用于返回，根据播放次数所推荐的album信息
     *
     * @return 根据播放次数所推荐的album信息
     */
    @RequestMapping(value = AppRestUrls.REST_URL_courseRecommendByPlayTimesListRestUrl, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object getCousesOrderByPlayTimes() {
        return composeResponseData(courseMapper.getCousesOrderByPlayTimes());

    }

    /**
     * 此方法用于返回，根据推荐值所推荐的album信息
     *
     * @return 根据推荐值所推荐的album信息
     */
    @RequestMapping(value = AppRestUrls.REST_URL_courseRecommendByRecommendStartListRestUrl, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object getCousesOrderByRecommendStart() {
        return composeResponseData(courseMapper.getCousesOrderByRecommendStart());
    }

    /**
     * 此方法用于返回，轮播中的的album信息
     *
     * @return 轮播中的的album信息
     */
    @RequestMapping(value = AppRestUrls.REST_URL_courseRecommendForLoopListRestUrl, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object getRecommendForLoopCouses() {
        return composeResponseData(courseMapper.getRecommendForLoopCouses());
    }

    /**
     * 课程的粉丝数量排行榜
     */
    @RequestMapping(value = REST_URL_QINGCHENG_SQL_COURSE_GET_FOLLOWER_ORDER_LIST, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object getCousesOrderByCourseFollowerNumber() {
        return composeResponseData(courseMapper.getCousesOrderByCourseFollowerNumber());

    }
    /**
     * 二、管理员部分
     */

    /**
     * [管理员]此方法用于,返回所有上传到HDFS中的课程类别
     *
     * @return 所有课程类别信息
     */
    @RequestMapping(value = AppRestUrls.REST_URL_COURSE_ADMIN_ALL_LIST_REST_URL, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object getAllCouses4Admin() {
        return composeResponseData(courseMapper.getAllCouses4Admin());
    }

    /**
     * [管理员]此方法用于,更新课程信息
     */
    @RequestMapping(value = AppRestUrls.REST_URL_COURSE_ADMIN_UPDATE_REST_URL, method = RequestMethod.POST, produces = FileType.CONTENT_TYPE_JSON)
    public Object updateCourse(@RequestBody Course course) {
        courseMapper.updateCourse(course);
        //更新成功返回ok
        return composeResponseData("ok");

    }

    /**
     * [管理员]此方法用于,根据课程id删除课程
     */
    @RequestMapping(value = AppRestUrls.REST_URL_COURSE_ADMIN_DELETE_REST_URL, method = RequestMethod.POST, produces = FileType.CONTENT_TYPE_JSON)
    public void deleteCousesByCourseId(@RequestBody String courseID) {
        courseMapper.deleteCousesByCourseId(courseID);
    }

}
