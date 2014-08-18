package net.martenscs.osgi.mybatis.blueprint.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.martenscs.osgi.example.domain.Category;
import net.martenscs.osgi.mybatis.blueprint.example.mapper.CategoryMapper;

public class CategoryService {

	private SqlSessionFactory factory;

	public Category getCategory(int id) {
		SqlSession sqlSession = getFactory().openSession();
		try {
			CategoryMapper categoryMapper = sqlSession
					.getMapper(CategoryMapper.class);
			return categoryMapper.selectCategoryById(id);
		} finally {
			sqlSession.close();
		}
	}

	public SqlSessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SqlSessionFactory factory) {
		this.factory = factory;
	}

	public void init() {
		Category category = getCategory(1);
		System.out.println(category.getCategoryName());
	}
}
