package com.qingcheng.code.user.controller.api.mysql;

import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.common.util.advance.filetype.FileType;
import com.qingcheng.code.user.bean.UserCourse;
import com.qingcheng.code.user.dao.mysql.UserCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.qingcheng.code.user.controller.util.DataUtil.composeResponseData;


@RestController
@Transactional
public class UserCourseController {
    @Autowired
    private UserCourseMapper userCourseMapper;

    /**
     * 此方法用于，根据email获取用户关注的课程,或用户观看课程的历史记录
     *
     * @param email email
     * @return 用户关注的课程
     */
    @RequestMapping(value = AppRestUrls.REST_URL_QINGCHENG_SQL_COURSE_GET_BY_EMAIL, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object getCousesByUserEmail(@RequestParam(value = "email") String email, @RequestParam(value = "favorite") Boolean favoite) {
        if (favoite) {
            //查询收藏的课程
            return composeResponseData(userCourseMapper.getCousesByUserEmail4Favorite(email));
        } else {
            //查询历史记录
            return composeResponseData(userCourseMapper.getCousesByUserEmail4History(email));
        }
    }


    /**
     * 此方法用于，根据courseID获取所有关注的用户
     *
     * @param courseID courseID
     * @return 所有关注的用户
     */
    @RequestMapping(value = AppRestUrls.REST_URL_QINGCHENG_SQL_USER_GET_BY_COURSEID, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    Object getUserByUserCourseId(@RequestParam(value = "courseID") String courseID) {
        return composeResponseData(userCourseMapper.getUserByUserCourseId(courseID));
    }

    /**
     * 此方法用于，插入或更新用户关注的课程
     */
    @RequestMapping(value = AppRestUrls.REST_URL_QINGCHENG_SQL_USER_UPDATE_FAVORITE_COURSE, method = RequestMethod.POST, produces = FileType.CONTENT_TYPE_JSON)
    void upsertUserCourse(@RequestBody UserCourse userCourse) {
        if (userCourse.getFavorite()) {
            if (userCourseMapper.courseHasExistInFavoriteTable(userCourse) == 0) {
                //插入
                userCourseMapper.addCouses2UserFavorite(userCourse);
            }
        } else {
            //删除
            userCourseMapper.deletCourse2UserFavorite(userCourse);
        }
    }
}
