package com.ttgis.recruit.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception
	{
		String urlPath = request.getRequestURI();
		if (urlPath.equals("/Recruit/login")
				|| urlPath.equals("/Recruit/htRecruit")
				|| urlPath.equals("/Recruit/zzjgLogin")
				|| urlPath.equals("/Recruit/Calendar")
				|| urlPath.equals("/Recruit/qtBbs")
				|| urlPath.equals("/Recruit/register")
				|| urlPath.equals("/Recruit/registerUser")
				|| urlPath.equals("/Recruit/Main")
				|| urlPath.equals("/Recruit/UserLogin")
				|| urlPath.equals("/Recruit/test")
				|| urlPath.equals("/Recruit/UserLogout")
				|| urlPath.equals("/Recruit/ViewNews")
				|| urlPath.equals("/Recruit/Group")
				|| urlPath.equals("/Recruit/SearchPosition")
				|| urlPath.equals("/Recruit/LoginFrm")
				|| urlPath.equals("/Recruit/ApplyPosition")
				|| urlPath.equals("/Recruit/MyCollection")
				|| urlPath.equals("/Recruit/DoCollection")
				|| urlPath.equals("/Recruit/LoginFrm")
				|| urlPath.equals("/Recruit/CancelPosition")
				|| urlPath.equals("/Recruit/GroupIntroduction")
				|| urlPath.indexOf("Mobile") != -1
				|| urlPath.equals("/Recruit/Corporateleadership")
				|| urlPath.equals("/Recruit/LoginFrmFirst")
				|| urlPath.equals("/Recruit/checkUserIdcard")
				|| urlPath.equals("/Recruit/checkUserName")
				|| urlPath.equals("/Recruit/checkUserSfzh")
				|| urlPath.equals("/Recruit/checkUserTelephone")
				|| urlPath.equals("/Recruit/LoadZpglDataExp")
				|| urlPath.equals("/Recruit/setTop") // 新闻置顶
				// 交流社区 ====科工论坛
				// || urlPath.equals("/Recruit/jlsq") ||
				// urlPath.equals("/Recruit/queryByBq") ||
				// urlPath.equals("/Recruit/queryBbsByLtbkId") ||
				// urlPath.equals("/Recruit/qtReviewSave") ||
				// urlPath.equals("/Recruit/BkBbsSave") ||
				// urlPath.equals("/Recruit/getBbsById") ||
				// urlPath.equals("/Recruit/getBByLtbq") ||
				// urlPath.equals("/Recruit/BbsByLtbkIdData") ||
				// urlPath.equals("/Recruit/BbsByLtbkIdDataCount") ||
				// urlPath.equals("/Recruit/LoadReviewData") ||
				// urlPath.equals("/Recruit/LoadReviewDataCount") ||
				// urlPath.equals("/Recruit/queryByBqData")
				// || urlPath.equals("/Recruit/queryByBqDataCount")
				// || urlPath.equals("/Recruit/qtLtbqReviewSave")
				// || urlPath.equals("/Recruit/addZan")
				// || urlPath.equals("/Recruit/qtGetReplyByReviewId")
				// || urlPath.equals("/Recruit/qtBbsReplySave")
				// || urlPath.equals("/Recruit/qtBbsLtbqReplySave")
				// 交流社区 ====经验博文
				// || urlPath.equals("/Recruit/zyty") ||
				// urlPath.equals("/Recruit/LoadZytyData") ||
				// urlPath.equals("/Recruit/LoadZytyDataCount") ||
				// urlPath.equals("/Recruit/queryZytyById") ||
				// urlPath.equals("/Recruit/ArticleReviewData")
				// || urlPath.equals("/Recruit/ArticleReviewDataCount")
				// || urlPath.equals("/Recruit/saveArticleReview")
				// || urlPath.equals("/Recruit/rmbw")
				// || urlPath.equals("/Recruit/LoadRmbwData")
				// || urlPath.equals("/Recruit/LoadRmbwDataCount")
				// 交流社区 ====兴趣圈子
				// || urlPath.equals("/Recruit/qtXqqz") ||
				// urlPath.equals("/Recruit/LoadqtXqqzData") ||
				// urlPath.equals("/Recruit/LoadqtXqqzDataCount")
				// || urlPath.equals("/Recruit/queryXqqzById")
				// || urlPath.equals("/Recruit/XqqzReviewData")
				// || urlPath.equals("/Recruit/XqqzReviewDataCount")
				// || urlPath.equals("/Recruit/saveXqqzReview")
				// || urlPath.equals("/Recruit/rmbwAdd")
				// || urlPath.equals("/Recruit/qtxqqzAdd")
				// || urlPath.equals("/Recruit/qtNewArticleSave")
				// || urlPath.equals("/Recruit/qtNewXqqzSave")
				// 找回密码设置
				|| urlPath.equals("/Recruit/zhmm")
				|| urlPath.equals("/Recruit/checkUserIsCZ")
				|| urlPath.equals("/Recruit/successCzUserUpdPD")
				// 测评===登陆===答卷
				|| urlPath.equals("/Recruit/DLcpgl") || urlPath.equals("/Recruit/cpglKszl") || urlPath.equals("/Recruit/cpglKssm") || urlPath.equals("/Recruit/cpglKssm1") || urlPath.equals("/Recruit/cpglKssm2") || urlPath.equals("/Recruit/cpglGxsm") || urlPath.equals("/Recruit/successSubmit") || urlPath.equals("/Recruit/successSubmit1") || urlPath.equals("/Recruit/successSubmit2") || urlPath.equals("/Recruit/cpglCpsj") || urlPath.equals("/Recruit/CpglLogin") || urlPath.equals("/Recruit/CpglLogout") || urlPath.equals("/Recruit/querySj") || urlPath.equals("/Recruit/TestSubmit") || urlPath.equals("/Recruit/glgTszgSubmit") || urlPath.equals("/Recruit/queryJsgSj") || urlPath.equals("/Recruit/JsgSjSubmit") || urlPath.equals("/Recruit/queryGlgEng") || urlPath.equals("/Recruit/GlgEngSubmit")
				|| urlPath.equals("/Recruit/querySjgx") || urlPath.equals("/Recruit/TestSubmitgx") || urlPath.equals("/Recruit/ShowCharts") || urlPath.equals("/Recruit/checkKscj") || urlPath.equals("/Recruit/queryByGxcsId") || urlPath.equals("/Recruit/JsgEngSubmit") || urlPath.equals("/Recruit/checkGxcs") || urlPath.equals("/Recruit/queryJsgEng") || urlPath.equals("/Recruit/Gxcs") || urlPath.equals("/Recruit/cpgljsgGxsm")
				|| urlPath.equals("/Recruit/resources/jquery/jquery.min.map")
				|| urlPath.equals("/Recruit/checkKscjGlg")
				|| urlPath.equals("/Recruit/checkKscjJsg")
				|| urlPath.equals("/Recruit/checkKscjEng")
				// 前台招聘
				|| urlPath.equals("/Recruit/LoadPositionData") || urlPath.equals("/Recruit/LoadPositionDataCount") || urlPath.equals("/Recruit/PositionDetails") || urlPath.equals("/Recruit/SearchEnterprise") || urlPath.equals("/Recruit/OfficialsAddress") || urlPath.equals("/Recruit/Organization") || urlPath.equals("/Recruit/DevelopmentProcess") || urlPath.equals("/Recruit/Message") || urlPath.equals("/Recruit/MessageDetails") || urlPath.equals("/Recruit/MyRecruit") || urlPath.equals("/Recruit/InformationCenter") || urlPath.equals("/Recruit/DevelopmentProcess") || urlPath.equals("/Recruit/Message") || urlPath.equals("/Recruit/MessageDetails") || urlPath.equals("/Recruit/ErospaceAndDefense") || urlPath.equals("/Recruit/TheProperty") || urlPath.equals("/Recruit/NformationTechnology")
				|| urlPath.equals("/Recruit/QuipmentManufaturing") || urlPath.equals("/Recruit/TalentStrategy") || urlPath.equals("/Recruit/Personnel") || urlPath.equals("/Recruit/AcademiciansAndExperts") || urlPath.equals("/Recruit/MyApplication") || urlPath.equals("/Recruit/AboutOnlineApplication") || urlPath.equals("/Recruit/AboutTheTest") || urlPath.equals("/Recruit/AboutEvaluation") || urlPath.equals("/Recruit/AboutEvaluation") || urlPath.equals("/Recruit/AboutMobile") || urlPath.equals("/Recruit/Recruitment") || urlPath.equals("/Recruit/Xzcc") || urlPath.equals("/Recruit/Registration") || urlPath.equals("/Recruit/qtKcglDataCount") || urlPath.equals("/Recruit/qtKcglData") || urlPath.equals("/Recruit/PrintTestCard") || urlPath.equals("/Recruit/selectBySjdw")
				|| urlPath.equals("/Recruit/InformationCenterCount") || urlPath.equals("/Recruit/InformationCenterData") || urlPath.equals("/Recruit/newPDF") || urlPath.equals("/Recruit/Revoke") || urlPath.equals("/Recruit/RecruitmentDynamics") || urlPath.equals("/Recruit/InstituteOf") || urlPath.equals("/Recruit/RecruitmentDynamicsDetails") || urlPath.equals("/Recruit/DoPrintTestCard") || urlPath.equals("/Recruit/LoadPxjl") || urlPath.equals("/Recruit/Career") || urlPath.equals("/Recruit/StaffDevelopment")
				|| urlPath.equals("/Recruit/ZwglView")
				|| urlPath.equals("/Recruit/checkmm")
				// 个人简历
				|| urlPath.equals("/Recruit/updateStar") || urlPath.equals("/Recruit/expResumes") || urlPath.equals("/Recruit/Yljl") || urlPath.equals("/Recruit/Grjl") || urlPath.equals("/Recruit/SaveJbxx") || urlPath.equals("/Recruit/SaveLxfs") || urlPath.equals("/Recruit/SaveQtxx") || urlPath.equals("/Recruit/SaveJyjl") || urlPath.equals("/Recruit/SaveJyjlagain") || urlPath.equals("/Recruit/LoadJyjl") || urlPath.equals("/Recruit/SaveXnjl") || urlPath.equals("/Recruit/SaveXnjlagain") || urlPath.equals("/Recruit/LoadXnjl") || urlPath.equals("/Recruit/SaveXnzw") || urlPath.equals("/Recruit/SaveXnzwagain") || urlPath.equals("/Recruit/LoadXnzw") || urlPath.equals("/Recruit/SaveSjjl") || urlPath.equals("/Recruit/SaveSjjlagain") || urlPath.equals("/Recruit/LoadSjjl") || urlPath.equals("/Recruit/SaveSxjl")
				|| urlPath.equals("/Recruit/SaveSxjlagain") || urlPath.equals("/Recruit/LoadSxjl") || urlPath.equals("/Recruit/SaveGzjl") || urlPath.equals("/Recruit/SaveGzjlagain") || urlPath.equals("/Recruit/LoadGzjl") || urlPath.equals("/Recruit/SaveXmjy") || urlPath.equals("/Recruit/SaveXmjyagain") || urlPath.equals("/Recruit/LoadXmjy") || urlPath.equals("/Recruit/SavePxjl") || urlPath.equals("/Recruit/SavePxjlagain") || urlPath.equals("/Recruit/LoadPxnjl") || urlPath.equals("/Recruit/SaveYynl") || urlPath.equals("/Recruit/SaveYynlagain") || urlPath.equals("/Recruit/LoadYynl") || urlPath.equals("/Recruit/SaveZs") || urlPath.equals("/Recruit/SaveItjn") || urlPath.equals("/Recruit/SaveQtxx") || urlPath.equals("/Recruit/SaveQtxxagain") || urlPath.equals("/Recruit/LoadQtxx")
				|| urlPath.equals("/Recruit/SaveFj") || urlPath.equals("/Recruit/SaveFjagain") || urlPath.equals("/Recruit/LoadFj") || urlPath.equals("/Recruit/Employee") || urlPath.equals("/Recruit/CorporateCulture") || urlPath.equals("/Recruit/linkdel") || urlPath.equals("/Recruit/LinkListJson") || urlPath.equals("/Recruit/SavePhotos") || urlPath.equals("/Recruit/DeleteJyjl") || urlPath.equals("/Recruit/DeleteXnjl") || urlPath.equals("/Recruit/DeleteYynl") || urlPath.equals("/Recruit/DeleteGzjl") || urlPath.equals("/Recruit/DeleteXnzw") || urlPath.equals("/Recruit/DeleteSjjl") || urlPath.equals("/Recruit/DeleteSx") || urlPath.equals("/Recruit/DeleteXmjy") || urlPath.equals("/Recruit/DeletePxjl") || urlPath.equals("/Recruit/DeleteQtxx") || urlPath.equals("/Recruit/DeleteFj")
				|| urlPath.equals("/Recruit/DeleteZzjl") || urlPath.equals("/Recruit/LoadJlzy") || urlPath.equals("/Recruit/DeleteItjn") || urlPath.equals("/Recruit/expjl") || urlPath.equals("/Recruit/DeleteZs") || urlPath.equals("/Recruit/DownloadResume") || urlPath.equals("/Recruit/SaveITjnagain") || urlPath.equals("/Recruit/LoadITjn") || urlPath.equals("/Recruit/DeleteITjn") || urlPath.equals("/Recruit/SaveZsagain") || urlPath.equals("/Recruit/LoadZs") || urlPath.equals("/Recruit/DeleteZs") || urlPath.equals("/Recruit/LoadXxmc") || urlPath.equals("/Recruit/SaveZzjl") || urlPath.equals("/Recruit/LoadZzjl"))
			return true;
		if (urlPath.indexOf(".") != -1)
		{
			String str = urlPath.substring(urlPath.lastIndexOf(".") + 1, urlPath.length());
			str = str.toLowerCase();
			if (str.equals("ico") || str.equals("png") || str.equals("gif") || str.equals("jpg") || str.equals("jpeg") || str.equals("html") || str.equals("js") || str.equals("css") || str.equals("xls") || str.equals("pdf") || str.equals("swf") || str.equals("doc") || str.equals("xlsx"))
			{
				return true;
			}
		}
		
		Object o = request.getSession().getAttribute("loginSession");
		
		if (o != null)
		{
			return true;
		} else
		{
			System.out.println(urlPath + "---->被拦截！");
			PrintWriter out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\">window.top.location.href='/Recruit/Main';</script>");
			out.print(builder.toString());
			out.close();
		}

		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception err) throws Exception
	{

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mv) throws Exception
	{

	}
	public static void main(String[] args){
		String ss = "Abc";
		ss = ss.toLowerCase();
		System.out.println(ss);
	}
}
