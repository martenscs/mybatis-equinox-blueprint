package net.martenscs.osgi.mybatis.blueprint.example.mapper;

import net.martenscs.osgi.example.domain.Category;



public interface CategoryMapper {
	
	public Category selectCategoryById(int id);
}