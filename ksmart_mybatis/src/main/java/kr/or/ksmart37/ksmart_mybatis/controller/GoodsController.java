package kr.or.ksmart37.ksmart_mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ksmart37.ksmart_mybatis.dto.Goods;
import kr.or.ksmart37.ksmart_mybatis.mapper.GoodsMapper;
import kr.or.ksmart37.ksmart_mybatis.service.GoodsService;

@Controller
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@GetMapping("/addGoods")
	public String addGoods(Model model) {
		
		model.addAttribute("title", "상품등록");
		
		return "goods/gInsert";
	}
	
	@PostMapping("/addGoods")
	public String addGoods( Goods goods
						   ,@RequestParam(name="memberLevel", required = false, defaultValue = "4") int memberLevel) {
		
		System.out.println("상품등록화면에서 입력 받은 값->" + goods);
		System.out.println("상품등록화면에서 입력 받은 값->" + memberLevel);
		
		if(memberLevel < 3) {
			//상품등록 
			String result = goodsService.addGoods(goods);
			
			System.out.println("상품등록 아이디: " + goods.getGoodsSellerId() + ", 상품등록정보 : "
								+ goods.getGoodsCode() + "||" + goods.getGoodsName() + "===" + result);
			
			return "redirect:/goodsList";			
		}else if(memberLevel == 3) {
			return "redirect:/";
		
		}else {
			return "redirect:/login";
		}
		
	}
	
	//modifyGoods
	@GetMapping("/modifyGoods")
	public String modifyGoods(@RequestParam(name="goodsCode", required = false) String goodsCode
							 ,Model model) {
		System.out.println("상품 수정화면에 입력받은 값->" + goodsCode);
		
		Goods goods = goodsService.getGoodsByCode(goodsCode);
		
		model.addAttribute("title", "상품 수정");
		model.addAttribute("goods", goods);
		
		return "goods/gUpdate";
	}
	//removeGoods
	@GetMapping("/removeGoods")
	public String removeGoods(@RequestParam(name="goodsCode", required = false) String goodsCode
							 ,Model model) {
		
		System.out.println("상품 수정화면에 입력받은 값->" + goodsCode);
		
		Goods goods = goodsService.getGoodsByCode(goodsCode);
		
		model.addAttribute("title", "상품 삭제");
		model.addAttribute("goodsCode", goodsCode);
		model.addAttribute("goodsSellerId", goods.getGoodsSellerId());
		
		return "goods/gDelete";
	}
	
	@GetMapping("/goodsList")
	public String getGoodsList(Model model) {
		List<Goods> goodsList = goodsService.getGoodsList();
		
		System.out.println("===========================상품목록=========================");
		System.out.println(goodsList);
		System.out.println("==========================================================");
		
		model.addAttribute("title", "상품 목록");
		model.addAttribute("goodsList", goodsMapper.getGoodsList());
		return "goods/gList";
	}
	
	@PostMapping("/removeGoods")
	public String removeGoods(@RequestParam(name="goodsCode", required = false) String goodsCode
							 ,@RequestParam(name="memberId", required = false) String memberId
							 ,@RequestParam(name="memberPw", required = false) String memberPw) {
		System.out.println("상품 삭제화면에서 입력 받은 값->" + goodsCode);
		System.out.println("상품 삭제화면에서 입력 받은 값->" + memberId);
		System.out.println("상품 삭제화면에서 입력 받은 값->" + memberPw);
		
		String result = goodsService.removeGoods(goodsCode, memberId, memberPw);
		
		System.out.println(goodsCode + " : " + result);
		
		return "redirect:/goodsList";
	}
	
	@PostMapping("/modifyGoods")
	public String modifyGoods(Goods goods) {
		
		System.out.println("상품 수정화면에서 입력 받은 값->"+ goods);
		
		String result = goodsService.modifyGoods(goods);
		
		System.out.println(goods.getGoodsCode() + " : " + result);
		
		return "redirect:/goodsList";
	}

}
