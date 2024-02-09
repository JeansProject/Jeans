const ratingStars = [...document.getElementsByClassName("rating__star")];


const submitButton=document.querySelector(`#submit-button`);

submitButton.addEventListener(`click`,function(){
	
const title = document.getElementById("#id").value;  
const name = document.getElementById("#name").value;
const content = document.getElementById("#content").value;


})
//const i;


function executeRating(stars) {
	const starClassActive = "rating__star fas fa-star";
	const starClassInactive = "rating__star far fa-star";
	const starsLength = stars.length;
	
	
	stars.map((star) => {
		star.onclick = () => {
			i = stars.indexOf(star);

			if (star.className === starClassInactive) {
				for (i; i >= 0; --i) stars[i].className = starClassActive;
			} else {
				for (i; i < starsLength; ++i) stars[i].className = starClassInactive;
			}
		};
	});
}

executeRating(ratingStars);


//id는 유일한//class 는 동일한 값으 가질 수 있다 . name은 뭐야 
// 
const array= document.querySelectorAll(form-checklaber[0] )
//const obj =document.querySelector(#id).value // 들어간 값을 가져오고 싶다. 

//


      const inputFileUpload = document.querySelector("#upload-file");
// event라는 객체 
      const fileupload = (event) => {
        const formData = new FormData();
        formData.append("files", event.target.files[0]);

        axios({
          headers: {
            "Content-Type": "multipart/form-data",
            "Access-Control-Allow-Origin": "*",
          },
          url: "https://7942yongdae.tistory.com/file-upload", // 요청 파일 업로드 요청 URL
          method: "POST",
          data: formData, /*요청보내고 밑에 response가 오니까 이거는 비동기 */
        }).then((response) => {
          inputFileUpload.value = "";
        });
      };
	/*이벤트 발생 리스너 */
      inputFileUpload.addEventListener("change", fileupload);

/*첨부 파일을 눌렀을때 디비랑 비동기로 작업을 시키는군.  */
      document
        .querySelector("#btn-file-upload")
        .addEventListener("click", () => {
          inputFileUpload.click();
        });
    





function executeRating(stars) {
  const starClassActive = "rating__star fas fa-star";
  const starClassInactive = "rating__star far fa-star";
  const starsLength = stars.length;

  stars.map((star) => {
    star.onclick = () => {
      i = stars.indexOf(star);

      if (star.className === starClassInactive) {
        for (i; i >= 0; --i) stars[i].className = starClassActive;
      } else {
        for (i; i < starsLength; ++i) stars[i].className = starClassInactive;
      }

      // 여기에 서버로 전송하는 코드 추가
      const ratingValue = i + 1;  // 1부터 시작하는 별점 값

      // Axios를 사용하여 서버로 데이터 전송
      axios.post('/api/rating', {
        title: title.value,
        name: name.value,
        content: content.value,
        file: file.value,
        rating: ratingValue
      })
      .then((response) => {
        // 서버 응답에 대한 처리 (예: 성공 메시지 출력)
        console.log(response.data);
      })
      .catch((error) => {
        // 오류 처리
        console.error('Error sending rating to server:', error);
      });
    };
  });
}

executeRating(ratingStars);
 

/*리뷰 게시판 게시판 작성 버튼*/
if(document.getElementById("writeReviewBoard")){
	const $writeReviewBoard = document.getElementById("writeReviewBoard"); 
	$writeReviewBoard.onclick = function() {
            location.href = "/reviewBoard/regist";
        }
    
}
