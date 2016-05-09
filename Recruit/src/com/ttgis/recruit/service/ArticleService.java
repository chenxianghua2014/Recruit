package com.ttgis.recruit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.recruit.Mapper.ArticleMapper;
import com.ttgis.recruit.domain.Article;
import com.ttgis.recruit.domain.QueryArticle;

/**
 * 
 * @author 范井龙
 * 
 */

@Repository
@Service
public class ArticleService
{

	@Resource
	private ArticleMapper articleMapper;

	public List<Article> selectByWhere(QueryArticle qa)
	{
		return articleMapper.selectByWhere(qa);
	}

	public int selectCount(QueryArticle qa)
	{
		return articleMapper.selectCount(qa);
	}

	public Article selectByPrimaryKey(String articleId)
	{
		return articleMapper.selectByPrimaryKey(articleId);
	}

	public List<Article> selectByWhere1(QueryArticle qa)
	{
		return articleMapper.selectByWhere1(qa);
	}

	public int selectCount1(QueryArticle qa)
	{
		return articleMapper.selectCount1(qa);
	}

	@Transactional
	public void updateByPrimaryKeySelective(Article article)
	{
		articleMapper.updateByPrimaryKeySelective(article);
	}

	@Transactional
	public void insertSelective(Article article)
	{
		articleMapper.insertSelective(article);
	}

	/**
	 * 插入个人博文信息
	 * 
	 * @param article
	 */
	@Transactional
	public void insertArticle(Article article)
	{
		articleMapper.insert(article);
	}

	/**
	 * 删除标签
	 * 
	 * @param ltbqId
	 */
	@Transactional
	public void delArticle(String articleId)
	{
		articleMapper.deleteByPrimaryKey(articleId);
	}

	public List<Article> queryArticleMark(String articleFbr)
	{
		return articleMapper.queryArticleMark(articleFbr);
	}

	public List<Article> LoadZytyData(QueryArticle qa)
	{
		return articleMapper.LoadZytyData(qa);
	}

	public int LoadZytyDataCount(QueryArticle qa)
	{
		return articleMapper.LoadZytyDataCount(qa);
	}

	public List<Article> LoadRmbwData(QueryArticle qa)
	{
		return articleMapper.LoadRmbwData(qa);
	}

	public int LoadRmbwDataCount(QueryArticle qa)
	{
		return articleMapper.LoadRmbwDataCount(qa);
	}

	public int articleDshCount()
	{
		return articleMapper.articleDshCount();
	}

	public List<Article> queryarticleYgfxList()
	{
		return articleMapper.queryarticleYgfxList();
	}

	public List<Article> queryarticleXrfxList()
	{
		return articleMapper.queryarticleXrfxList();
	}

}
