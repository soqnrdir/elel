package com.study.common.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
public class PagingVO implements Serializable {
	
private int	curPage =1;  //현재 페이지 번호.
private int	firstRow;	// 시작 게시물 번호
private int	lastRow; // 종료 게시물 번호

private int	totalRowCount; // 총 게시물 수
private int	rowSizePerPage = 10; //
private int	totalPageCount; //총 페이지 수

private int	pageSize = 5; //
private int	firstPage; //
private int	lastPage; //

@Override
public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);

}

public void pageSetting() {
	//firstRow, lastRow
	// rowSizePerSize = 10
	//curPage =1, fR = 1, lR = 10
	//curPage =3, fR = 21, lR = 30
	firstRow = (curPage-1) * rowSizePerPage + 1;
	lastRow = curPage * rowSizePerPage;
	
	totalPageCount = (totalRowCount-1) / rowSizePerPage +1;
	
	//firstPage, lastPage
	firstPage = ((curPage-1) / pageSize)* pageSize + 1;
	lastPage = firstPage + pageSize - 1;
	if(lastPage >getTotalPageCount()) lastPage = totalPageCount;
		
	
	
}

public static void main(String[] args) {
	PagingVO page = new PagingVO();
	page.setTotalRowCount(233);
	page.pageSetting();
	System.out.println(page);
	page.setCurPage(3);
	page.pageSetting();
	System.out.println(page);
}

public int getCurPage() {
	return curPage;
}
public void setCurPage(int curPage) {
	this.curPage = curPage;
}
public int getFirstPage() {
	return firstPage;
}
public void setFirstPage(int firstPage) {
	this.firstPage = firstPage;
}
public int getLastPage() {
	return lastPage;
}
public void setLastPage(int lastPage) {
	this.lastPage = lastPage;
}
public int getTotalRowCount() {
	return totalRowCount;
}
public void setTotalRowCount(int totalRowCount) {
	this.totalRowCount = totalRowCount;
}
public int getRowSizePerPage() {
	return rowSizePerPage;
}
public void setRowSizePerPage(int rowSizePerPage) {
	this.rowSizePerPage = rowSizePerPage;
}
public int getTotalPageCount() {
	return totalPageCount;
}
public void setTotalPageCount(int totalPageCount) {
	this.totalPageCount = totalPageCount;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
public int getFirstRow() {
	return firstRow;
}
public void setFirstRow(int firstRow) {
	this.firstRow = firstRow;
}
public int getLastRow() {
	return lastRow;
}
public void setLastRow(int lastRow) {
	this.lastRow = lastRow;
}

}
