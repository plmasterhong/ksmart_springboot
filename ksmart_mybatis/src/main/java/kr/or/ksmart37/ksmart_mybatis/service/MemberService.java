package kr.or.ksmart37.ksmart_mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart37.ksmart_mybatis.dto.Member;
import kr.or.ksmart37.ksmart_mybatis.mapper.MemberMapper;

@Service
@Transactional
public class MemberService {
	// DI
	@Autowired 
	private MemberMapper memberMapper;
	
	public List<Member> getSellerList(){
		List<Member> sellerList = memberMapper.getSellerList();
		return sellerList;
	}
	
	public String removeMember(String memberId, String memberPw, String memberLevel) {
		String result = "회원 삭제 실패";

		//id member테이블 조회하고 조회한 결과 값 중 비밀번호와 화면에서 입력받은 비밀번호가 일치하면 삭제 처리
		Member member = memberMapper.getMemberById(memberId);
		
		if(member != null && member.getMemberPw() != null && memberPw.equals(member.getMemberPw())) {
			int removeCheck = memberMapper.callProRemoveMemberById(memberId, memberLevel);
			/*
			int removeCheck = memberMapper.removeLoginById(memberId);
			
			if("판매자".equals(memberLevel)) removeCheck += memberMapper.removeGoodsById(memberId);
			
			if("구매자".equals(memberLevel)) removeCheck += memberMapper.removeOrderById(memberId);
			
			removeCheck += memberMapper.removeMemberById(memberId);
			*/
			if(removeCheck > 0) result = "회원 삭제 성공";
			
		}
		
		return result;
	}
	
	public String modifyMember(Member member) {
		String result = "회원 수정 실패";
		
		int modifyCheck = memberMapper.modifyMember(member);
		
		if(modifyCheck > 0) result = "회원 수정 성공";
		
		return result;
	}
	
	public Member getMemberById(String memberId) {
		
		Member member = memberMapper.getMemberById(memberId);
		
		return member;
	}
	
	public String addMember(Member member) {
		String insertCheck = "회원가입 실패";
		if(member != null) {
			int result = memberMapper.addMember(member);
			if(result > 0) insertCheck = "회원가입 성공";
		}
		
		return insertCheck;
	}

	public List<Member> getMemeberList(){
		
		List<Member> memberList = memberMapper.getMemberList();
		int listSize = memberList.size();
		for(int i=0; i<listSize; i++) {
			if("1".equals(memberList.get(i).getMemberLevel())) {
				memberList.get(i).setMemberLevel("관리자");
			}else if("2".equals(memberList.get(i).getMemberLevel())) {
				memberList.get(i).setMemberLevel("판매자");
			}else if("3".equals(memberList.get(i).getMemberLevel())) {
				memberList.get(i).setMemberLevel("구매자");				
			}else {
				memberList.get(i).setMemberLevel("일반회원");
			}
		}
		
		return memberList;
	}

}
