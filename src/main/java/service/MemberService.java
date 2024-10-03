package service;

import java.util.List;

import model.Member;
import repository.implementation.MemberRepositoryImpl;
import repository.interfaces.MemberRepository;

public class MemberService {

	private MemberRepository memberRepository ;
	
	public MemberService() {
		this.memberRepository = new MemberRepositoryImpl() ;
	}
	
	public List<Member> getAllMembers(int page , int pageSize){
		return memberRepository.getAllMembers(page, pageSize);
	}
	
	public Member getMemberById(Long id) {
		return memberRepository.getMemberById(id);
	}
	
	public void addMember(Member member) {
		memberRepository.addMember(member);
	}
	
	public void updateMember(Member member) {
		memberRepository.updateMember(member);
	}
	
	public void deleteMember(Long id) {
		memberRepository.deleteMember(id);
	}
}
