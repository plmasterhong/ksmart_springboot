package kr.or.ksmart37.ksmart_mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart37.ksmart_mybatis.dto.Goods;
import kr.or.ksmart37.ksmart_mybatis.dto.Member;
import kr.or.ksmart37.ksmart_mybatis.mapper.GoodsMapper;
import kr.or.ksmart37.ksmart_mybatis.mapper.MemberMapper;

@Service
@Transactional
public class GoodsService {
	
	@Autowired 
	private GoodsMapper goodsMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	public List<Goods> getGoodsList(){
		
		return goodsMapper.getGoodsList();
	}
	
	public String addGoods(Goods goods) {
		String result = "상품 등록 실패";
		
		int insertCheck = goodsMapper.addGoods(goods);
		
		if(insertCheck > 0) result = "상품 등록 완료";
		
		return result;
	}
	
	public String removeGoods(String goodsCode, String memberId, String memberPw) {
		String result = "상품 삭제 실패";
		
		Member member = memberMapper.getMemberById(memberId);
		
		if(member != null && memberPw != null && member.getMemberPw() != null && memberPw.equals(member.getMemberPw())) {
			int removeCheck = goodsMapper.removeGoods(goodsCode);
			
			if(removeCheck > 0) result = "상품 삭제 완료";
		}
		
		return result;
	}
	
	public String modifyGoods(Goods goods) {
		String result = "상품 수정 실패";
		
		int modifyCheck = goodsMapper.modifyGoods(goods);
		
		if(modifyCheck > 0) result = "상품 수정 완료";
		
		return result;
	}
	
	public Goods getGoodsByCode(String goodsCode) {
		
		//goodsMapper에서 코드에 일치하는 상품정보가 담긴 객체 받아오기
		Goods goods = goodsMapper.getGoodsByCode(goodsCode);
		
		// Goods 객체 return 
		return goods;
	}

}
