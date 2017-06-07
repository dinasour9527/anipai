package com.anipai.auction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.anipai.auction.domain.Category;
import com.anipai.utils.Pagination;

@Mapper
public interface CategoryMapper {
	
	@SelectProvider(type = CategoryMapperProvider.class, method = "findCategoryPage")
	List<Category> findCategoryPage(@Param("page") Pagination pagination, @Param("agencyId") Long agencyId,
			@Param("level") Integer level, @Param("parentCategoryId") Long parentCategoryId);
	
	@SelectProvider(type = CategoryMapperProvider.class, method = "total")
	int total(@Param("agencyId") Long agencyId, @Param("level") Integer level, @Param("parentCategoryId") Long parentCategoryId);

	@Select("select category_id, category_name from category where category_id=#{categoryId}")
	Category findCategory(Long categoryId);
	
	@SelectProvider(type = CategoryMapperProvider.class, method = "findCategoryByLevel")
	List<Category> findCategoryByLevel(@Param("level") Integer level, @Param("agencyId") Long agencyId);

	@SelectProvider(type = CategoryMapperProvider.class, method = "findCategoryByParentCategoryId")
	List<Category> findCategoryByParentCategoryId(@Param("parentCategoryId") Long parentCategoryId, @Param("agencyId") Long agencyId);

	@Insert("insert into category(category_name, level, parent_category_id, agency_id) "
			+ "values (#{categoryName}, #{level}, #{parentCategory.categoryId}, #{agency.agencyId})")
	@Options(useGeneratedKeys = true, keyProperty = "categoryId", keyColumn = "category_id")
	void addCategory(Category category);

	@Update("update category set category_name=#{categoryName} where category_id=#{categoryId}")
	void updateCategory(Category category);

	@Delete("delete from category where category_id=#{categoryId}")
	void deleteCategory(Long categoryId);

}
