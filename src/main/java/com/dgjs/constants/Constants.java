package com.dgjs.constants;

public interface Constants {

	public static final int DEFAULT_ONEPAGESIZE=10;
	
	/*打点相关信息*/
	//页面pageid
	public static final int DD_DGJS_ID = 10336265;
	public static final int DD_INDEX_ID = 10336266;
	public static final int DD_SHOW_ID = 10336267;
	public static final int DD_TIMELINE_ID = 10336268;
	public static final int DD_FEEDBACK_ID = 10336269;
	
	/*用户id*/
	public static final int USER_ID = 1;
	
	/*用户上传素材库容量*/
	public static final int MAX_CONTAINER = 50;
	public static final int ONECE_MAX_CONTAINER = 5;//用户一次最多可上传几张图片
	public static final int MAX_FILE_SIZE = 2;//上传图片最大不能超过多少兆
	
	/*微信相关*/
	public static final int CK_USERINFO_MAXAGE = 7200;
	public static final String CK_USERINFO_KEY = "usercode";
	
	/*手机端首页前缀*/
	public static final String M_INDEX_CONFIX_PREFIX = "M_INDEX_";
	
	public static final String WX_CODE_PREFIX="wx_";
	public static final String WX_CODE_ENCRYPT_KEY="1468133156538546";
	
	/*用户组织相关*/
	public static final String CPS_LOGIN_URL="https://www.cwillow.com/cpslogin?organization=";
	public static final Integer PROXY_ROLE_ID=4;
	public static final Integer INIT_ROLE_ID=3;
	public static final Integer LOW_ROLE_ID=5;
	public static final Integer SECOND_ROLE_ID=2;
	public static final Integer ROLECODE_PRIMARY_ID=1;
	
	/*推荐码相关*/
	public static final int MAX_INVITATION=5;//每个用户最多有可用的二维码多少个
	public static final int MAX_INVITATION_VALID_TIME=10;//天为单位
}
