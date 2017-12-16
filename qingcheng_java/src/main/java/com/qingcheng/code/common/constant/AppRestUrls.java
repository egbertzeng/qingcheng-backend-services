package com.qingcheng.code.common.constant;

public interface AppRestUrls {
    /**
     * 零、工具类所需URL（有认证）
     */
    String REST_URL_UTIL_COMMON_PARAS_DIR = "dir";
    String REST_URL_UTIL_COMMON_PARAS_FILE = "file";

    //用于提供，生成课程目录
    String REST_URL_UTIL_MEDIA_DIR_TO_COURSE = "/qingcheng/util/media/dir/to/course";
    String REST_URL_UTIL_MEDIA_VIDEO_TO_TS = "/qingcheng/util/media/video/to/ts";

    //用于提供，更加输入路径，获取该路径下的所有文件
    String REST_URL_QINGCHENG_LOCALFILE_GET_FILELIST = "/qingcheng/localfile/get/filelist";

    //用于提供，判断ts文件是否存在
    String REST_URL_QINGCHENG_LOCALFILE_CHECK_TSFILE_ISEXIST = "/qingcheng/localfile/check/tsfile/isexist";
    /**
     * 一、播放器所需URL（有认证）
     */
    /**
     * 普通用户部分
     */

    //用于提供，所有category信息
    String REST_URL_CATEGORY_ALL_LIST_REST_URL = "/qingcheng/sql/category/all";

    //用于提供，所有课程信息
    String REST_URL_courseAllListRestUrl = "/qingcheng/sql/course/all";

    //用于提供，所有课程信息
    String REST_URL_getCourseListBySearchKeyRestUrl = "/qingcheng/sql/course/search";
    String REST_URL_getCourseListBySearchKeyRestUrlPara_key = "k";

    //用于提供，通过CategoryId获取课程信息
    String REST_URL_getCourseListByCategoryIdRestUrl = "/qingcheng/sql/get/course/by/categoryid";
    String REST_URL_getCourseListByCategoryIdRestUrl_categoryid = "categoryid";

    //用于提供，根据播放次数排行榜推荐
    String REST_URL_courseRecommendByPlayTimesListRestUrl = "/qingcheng/sql/course/recommend/playtimes";

    //用于提供，根据设定推荐级别排行榜推荐
    String REST_URL_courseRecommendByRecommendStartListRestUrl = "/qingcheng/sql/course/recommend/star";

    //用于提供，根据设定轮播中的推荐
    String REST_URL_courseRecommendForLoopListRestUrl = "/qingcheng/sql/course/recommend/for/loop";

    //用于提供，课程的播放列表
    String REST_URL_videoListOneCourseRestUrl = "/qingcheng/dfs/video/playlist";
    String REST_URL_videoListOneCourseRestUrlPrara = "dirPath";

    //用于堆外提供根据邮箱查询已经激活的用户
    String REST_URL_QINGCHENG_FIND_ACCOUNT_BY_EMAIL = "/qingcheng/find/account/by/email";

    //用于提供，根据email获取用户关注的课程
    String REST_URL_QINGCHENG_SQL_COURSE_GET_BY_EMAIL = "/qingcheng/sql/course/get/by/email";

    //用于提供，根据courseid获取关注该课程所有的关注用户
    String REST_URL_QINGCHENG_SQL_USER_GET_BY_COURSEID = "/qingcheng/sql/user/get/by/courseid";

    //用于提供，插入或更新用户关注的课程
    String REST_URL_QINGCHENG_SQL_USER_UPDATE_FAVORITE_COURSE = "/qingcheng/sql/user/update/favorite/course";

    //用于提供，根据courseID获取老师的信息
    String REST_URL_QINGCHENG_SQL_TEACHER_GET_BY_COURSEID = "/qingcheng/sql/teacher/get/by/courseid";

    //用于提供，根据teacherEmail获取教师发布的课程
    String REST_URL_QINGCHENG_SQL_COURSE_GET_BY_TEACHER_EMAIL = "/qingcheng/sql/course/get/by/teacher/email";

    //用于提供，根据fromEmail获取所关注的教师
    String REST_URL_QINGCHENG_SQL_COURSE_GET_USER_BY_FROMEMAIL = "/qingcheng/sql/course/get/user/by/fromemail";

    //用于提供,添加或更新用户关系
    String REST_URL_QINGCHENG_UPDATE_USER_RELATION = "/qingcheng/update/user/relation";

    //用于提供,根据查询所有已经激活的老师
    String REST_URL_QINGCHENG_GET_ALL_ACTIVE_TEACHER = "/qingcheng/get/all/active/teacher";

    //课程的粉丝数量排行榜
    String REST_URL_QINGCHENG_SQL_COURSE_GET_FOLLOWER_ORDER_LIST = "/qingcheng/sql/get/course/follower/order/list";

    //用户粉丝数量排行榜
    String REST_URL_QINGCHENG_SQL_GET_USER_FOLLOWER_ORDER_LIST = "/qingcheng/sql/get/user/follower/order/list";

    //学生，关注课程数量的排行榜
    String REST_URL_QINGCHENG_SQL_GET_USER_FOLLOW_COURSE_ORDER_LIST = "/qingcheng/sql/get/user/follow/course/order/list";

    //老师，发布课程数量的排行榜
    String REST_URL_QINGCHENG_SQL_GET_USER_PUBLISH_COURSE_ORDER_LIST = "/qingcheng/sql/get/user/publish/course/order/list";

    //用于提供,获取课程的附加资料
    String REST_URL_QINGCHENG_DFS_GET_MARTERIAL_BY_PATH = "/qingcheng/dfs/get/marterial/by/path";

    //用于获取课程的评论信息
    String QINGCHENG_SQL_COURSE_COMMENT_GET_BY_COURSEID = "/qingcheng/sql/course/comment/get/by/courseid";
    //用于添加课程的评论信息
    String QINGCHENG_SQL_ADD_COURSE_COMMENT = "/qingcheng/sql/add/course/comment";
    //用于显示或隐藏课程的评论信息
    String QINGCHENG_SQL_HIDE_OR_SHOW_COURSE_COMMENT = "/qingcheng/sql/hide/or/show/course/comment";
    /**
     * 管理员部分
     */
    //用于提供，所有课程信息
    String REST_URL_COURSE_ADMIN_ALL_LIST_REST_URL = "/qingcheng/sql/admin/course/all";

    //用于提供，更新课程信息，根据课程编号
    String REST_URL_COURSE_ADMIN_UPDATE_REST_URL = "/qingcheng/sql/admin/course/update";

    //用于提供，根据课程编号,删除课程
    String REST_URL_COURSE_ADMIN_DELETE_REST_URL = "/qingcheng/sql/admin/course/delete";

    //用于提供，在分布式文件系统上移动课程
    String REST_URL_COURSE_ADMIN_DFS_RENAME_REST_URL = "/qingcheng/dfs/admin/course/rename";

    //用户，获取所有用户的信息
    String REST_URL_QINGCHENG_SQL_ADMIN_GET_ALL_USER = "/qingcheng/sql/admin/get/all/user";

    //用户，更新用户的信息
    String REST_URL_QINGCHENG_SQL_ADMIN_UPDATE_USER = "/qingcheng/sql/admin/update/user";
    //用户，根据email获取用户信息
    String REST_URL_QINGCHENG_SQL_GET_USER_BY_EMAIL = "/qingcheng/sql/get/user/by/email";
    /**
     * 二、以下是没有经过安全认证的连接
     */
    //用于对外提供，程的video数据流，dfs上的文件读取功能，可以返回文件的输出流
    String REST_URL_courseFileOpneRestUrl = "/open/dfs/open";
    //todo 带验证的数据流请求
    //String REST_URL_courseFileOpneRestUrl = "/qingcheng/open/dfs/open";

    String REST_URL_courseFileOpneRestUrl_FilePath = "filePath";

    //用于堆外注册，登陆和激活账户
    String REST_URL_APP_LOGIN = "/open/login";
    String REST_URL_APP_REGIST = "/open/regist";
    String REST_URL_APP_ACTIVE = "/open/active";
    String REST_URL_APP_ACTIVE_P = "c";

    /**
     * 三、网盘所需URL （有认证）
     */
    //通用连接
    String REST_URL_wangpanListByDirRestUrl = "/qingcheng/wangpan/list/dir";
    String REST_URL_wangpanUriIsDirRestUrl = "/qingcheng/wangpan/uri/is/dir";
    String REST_URL_wangpanIsThisUriExistRestUrl = "/qingcheng/wangpan/uri/is/exist";

    //通用参数
    String REST_URL_wangpanListByDirRestUrl_dir = "dir";
    String REST_URL_wangpanCommonPara_url = "url";
    String REST_URL_wangpanCommonPara_compressType = "compresstype";
    String REST_URL_wangpanCommonPara_targeturl = "targeturl";

    //1.删除
    String REST_URL_wangpanGetTrashUrlRestUrl = "/qingcheng/wangpan/list/trash/url";
    String REST_URL_wangpanInsertTrashUrlRestUrl = "/qingcheng/wangpan/insert/trash/url";
    String REST_URL_wangpanDeleteTrashUrlRestUrl = "/qingcheng/wangpan/delete/trash/url";
    String REST_URL_wangpanDeleteTrashUrlTrueRestUrl = "/qingcheng/wangpan/delete/trash/true/url";
    String REST_URL_wangpanCleanTrashRestUrl = "/qingcheng/wangpan/clean/trash/url";
    //2.剪切
    String REST_URL_wangpanGetCutUrlRestUrl = "/qingcheng/wangpan/list/cut/url";
    String REST_URL_wangpanInsertCutUrlRestUrl = "/qingcheng/wangpan/insert/cut/url";
    String REST_URL_wangpanReplayCutUrlRestUrl = "/qingcheng/wangpan/replay/cut/url";
    String REST_URL_wangpanPasteCutUrlRestUrl = "/qingcheng/wangpan/paste/cut/url";

    //3.收藏
    String REST_URL_wangpanGetFavoriteUrlRestUrl = "/qingcheng/wangpan/list/favorite/url";
    String REST_URL_wangpanInsertFavoriteUrlRestUrl = "/qingcheng/wangpan/insert/favorite/url";
    String REST_URL_wangpanDeleteFavoriteUrlRestUrl = "/qingcheng/wangpan/delete/favorite/url";

    //3.历史
    String REST_URL_wangpanGetHistoryUrlRestUrl = "/qingcheng/wangpan/list/history/url";
    String REST_URL_wangpanInsertHistoryUrlRestUrl = "/qingcheng/wangpan/insert/history/url";
    String REST_URL_wangpanDeleteHistoryUrlRestUrl = "/qingcheng/wangpan/delete/history/url";


    //4.读取文件
    String REST_URL_wangpanOpneLineByLineRestUrl = "/qingcheng/dfs/open/line/by/line";

    //5.新建文件夹
    String REST_URL_wangpanMakeDirRestUrl = "/qingcheng/dfs/makedir";

    //6.下载文件
    String REST_URL_wangpanFileDownloadRestUrl = "/qingcheng/dfs/download";

    //7.当前活动的nameNode
    String REST_URL_wangpanGetActiveNamenodeRestUrl = "/qingcheng/dfs/get/active/namenode";
    String REST_URL_wangpanGetActiveNamenodeWebuiUrlRestUrl = "/qingcheng/dfs/get/active/namenode/webui/rul";

    //8.当前文件的处理进度（压缩进度，解压进度）
    String REST_URL_wangpanGetProcessProgressRestUrl = "/qingcheng/dfs/get/process/progress/info";

    //9.文件压缩
    String REST_URL_wangpanCompressRestUrl = "/qingcheng/dfs/compress/do";

    //10.文件解压
    String REST_URL_wangpanDeCompressRestUrl = "/qingcheng/dfs/decompress/do";
}
