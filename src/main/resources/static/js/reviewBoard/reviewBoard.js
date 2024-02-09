

/*리뷰 게시판 게시판 작성 버튼*/
if(document.getElementById("writeReviewBoard")){
	const $writeReviewBoard = document.getElementById("writeReviewBoard"); 
	$writeReviewBoard.onclick = function() {
            location.href = "/reviewBoard/regist";
        }
    
}

/*이벤트 리스너 */
document.getElementById("cancelModify_btn").addEventListener('click',function () {
    location.href= '/reviewBoard/detail?seq='+reviewBoard.seq;
})
