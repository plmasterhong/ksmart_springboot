package kr.or.ksmart37.ksmart_mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ksmart37.ksmart_mybatis.dto.Goods;

@Mapper
public interface GoodsMapper {
	public int addGoods(Goods goods);
	
	public List<Goods> getGoodsList();
	
	public int removeGoods(String goodsCode);
	
	public int modifyGoods(Goods goods);
	
	public Goods getGoodsByCode(String goodsCode);

}
