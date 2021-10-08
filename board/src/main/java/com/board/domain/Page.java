package com.board.domain;

public class Page {

	// 페이징 코드를 하나의 클래스로 분리
	
	// 현재 페이지 번호
	private int num;

	// 게시물 총 갯수
	private int count;

	// 한 페이지에 출력할 게시물 갯수
	private int postNum = 10;

	// 하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
	private int pageNum;

	// 출력할 게시물
	private int displayPost;

	// 한번에 표시할 페이징 번호의 갯수
	private int pageNumCnt = 10;

	// 표시되는 페이지 번호 중 마지막 번호
	private int endPageNum;

	// 표시되는 페이지 번호 중 첫번째 번호
	private int startPageNum;

	// 다음/이전 표시 여부
	private boolean prev;
	private boolean next;
	
	
// setter는 2개만 필요로함 
// 데이터 입력이 필요한건 현재페이지 num, 게시물 총 갯수 count 2개이기 때문

// getter는 데이터를 출력하는,현재 컨트롤러에서 model에 넣어지는 데이터를 의미함
	public void setNum(int num) {
		 this.num = num;
		}

		public void setCount(int count) {
		 this.count = count;
		 
		 dataCalc();
		 
		}

		public int getCount() {
		 return count;
		}

		public int getPostNum() {
		 return postNum;
		}

		public int getPageNum() {
		 return pageNum;
		}

		public int getDisplayPost() {
		 return displayPost;
		}

		public int getPageNumCnt() {
		 return pageNumCnt;
		}

		public int getEndPageNum() {
		 return endPageNum;
		}

		public int getStartPageNum() {
		 return startPageNum;
		}

		public boolean getPrev() {
		 return prev;
		} 

		public boolean getNext() {
		 return next;
		}
	
		// 데이터들을 계산하는 메서드
		private void dataCalc() {
			 
			 // 마지막 번호
			 endPageNum = (int)(Math.ceil((double)num / (double)pageNumCnt) * pageNumCnt);
			 
			 // 시작 번호
			 startPageNum = endPageNum - (pageNumCnt - 1);
			 
			 // 마지막 번호 재계산
			 int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNumCnt));
			 
			 if(endPageNum > endPageNum_tmp) {
			  endPageNum = endPageNum_tmp;
			 }
			 
			 prev = startPageNum == 1 ? false : true;
			 next = endPageNum * pageNumCnt >= count ? false : true;
			 
			 displayPost = (num - 1) * postNum;
			 
			}
}
