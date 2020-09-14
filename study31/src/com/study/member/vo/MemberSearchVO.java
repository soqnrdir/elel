package com.study.member.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.study.common.vo.PagingVO;

@SuppressWarnings("serial")
public class MemberSearchVO extends PagingVO{

	private String searchType;
	private String searchWord;
	private String searchJob;
	private String searchLike;
	
	public String getSearchLike() {
		return searchLike;
	}

	public String getSearchJob() {
		return searchJob;
	}

	public void setSearchJob(String searchJob) {
		this.searchJob = searchJob;
	}

	public void setSearchLike(String searchLike) {
		this.searchLike = searchLike;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
}
