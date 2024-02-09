let data = null;
let _page = 1; //현재 페이지 번호
let _start = 0; //조회 시작 데이터 배열 번호
let _limit = 10; //조회 마지막 데이터 배열 번호
const modal = new bootstrap.Modal(document.getElementById('myModal')); //모달 버튼

getList();

//// testList 내용을 DB에 문자형식으로 넣는다
$(this).ready(function(){
data = testList;
//
//boardListAction(data); //게시물 목록 바인딩
renderPagination(data, _page); // 페이징 바인딩
modalOpen();
modalClose();
});



//리스트 초기화
function boardListRemove(){
let dataTable = document.getElementById('dataTable');
while (dataTable.hasChildNodes()){
dataTable.removeChild(dataTable.firstChild);
}
}
function getList(){
	fetch(`/noticeList`, {
	Method : "GET",
	headers : {
	"Content-Type" : "application/json",
	}
	})
	.then((response) => response.json())
	.then(data =>{
	console.log(data);
		boardListAction(data);
	})
	.catch((e) => {
	console.log(e);
	});
}


//DB의 내용을 읽어서 테이블 데이터를 바인딩시킨다. -> 디비를 다녀오는 로직 추가.
function boardListAction(data) {
const dataTable = document.getElementById('dataTable');
boardListRemove();

if(data){
for(let i=_start; i<_limit; i++){
if(data[i]){
let tr = document.createElement("tr");
let td_no = document.createElement("td");
let td_title = document.createElement("td");
let td_content = document.createElement("td");
let td_writer = document.createElement("td");
let td_date = document.createElement("td");

td_no.setAttribute('scope', 'row');
td_no.innerText = data[i].seq;
td_title.innerText = data[i].title;
td_content.innerText = data[i].content.length > 20 ? data[i].content.substr(0, 20) + '...' : data[i].content;
//20글자 뒤에는 ...으로 처리
td_writer.innerText = data[i].writer;
td_date.innerText = data[i].write_time;

tr.appendChild(td_no);
tr.appendChild(td_title);
tr.appendChild(td_content);
tr.appendChild(td_writer);
tr.appendChild(td_date);

dataTable.appendChild(tr);
}
}
}
}

//function boardListAction(data) {
//const dataTable = document.getElementById('dataTable');
//boardListRemove();
//
//if(_dbData){
//for(let i=_start; i<_limit; i++){
//if(_dbData[i]){
//let tr = document.createElement("tr");
//let td_no = document.createElement("td");
//let td_title = document.createElement("td");
//let td_content = document.createElement("td");
//let td_writer = document.createElement("td");
//let td_date = document.createElement("td");
//
//td_no.setAttribute('scope', 'row');
//td_no.innerText = _dbData[i].seq;
//td_title.innerText = _dbData[i].title;
//td_content.innerText = _dbData[i].content.length > 20 ? _dbData[i].content.substr(0, 20) + '...' : _dbData[i].content;
////20글자 뒤에는 ...으로 처리
//td_writer.innerText = _dbData[i].writer;
//td_date.innerText = _dbData[i].write_time;
//
//tr.appendChild(td_no);
//tr.appendChild(td_title);
//tr.appendChild(td_content);
//tr.appendChild(td_writer);
//tr.appendChild(td_date);
//
//dataTable.appendChild(tr);
//}
//}
//}
//}


//페이지 컨트롤 영역 그리기
function renderPagination(totalCount, currentPage){
if (totalCount.length <= 10) return; //게시물 데이터가 10개 이하이면 페이지 영역을 그리지 않는다.

let totalPage = Math.ceil(totalCount.length / 10); //페이징 목록의 총 개수를 구한다.
let pageGroup = Math.ceil(currentPage / 10); // 한 페이징 목록은 10개씩 구성한다.

let last = pageGroup * 10;
if (last > totalPage) last = totalPage;
let first = last - (10 - 1) <= 0 ? 1 : last - (10 -1);
let next = last + 1;
let prev = first - 1;

let pageList = document.createElement('ul');
pageList.id = 'page_ul';
pageList.className = 'pagination';

if (prev > 0) { //이전 페이징 버튼
const preli = document.createElement('li');
preli.className = 'page-item';
preli.onclick = () => prePage(first);
preli.insertAdjacentHTML("beforeend", `<span class="page-link">Previous</span>`);
pageList.appendChild(preli);
}

for (let i = first; i <= last; i++){ //페이징목록 숫자 버튼
const li = document.createElement("li");
li.className = 'page-item-' + i;
li.onclick = () => movePage(i);
li.insertAdjacentHTML("beforeend", `<a class="page-link" href="#">${i}</a>`);
pageList.appendChild(li);
}

if (last < totalPage) { // 다음페이징 버튼
const endLi = document.createElement('li');
endLi.className = 'page-item';
endLi.onclick = () => nextPage(last);
endLi.insertAdjacentHTML("beforeend", `<a class="page-link" href="#">Next</a>`);
pageList.appendChild(endLi);
}

document.getElementById('page_area').appendChild(pageList); //최종 페이징을 HTML에 바인딩

let pageItem = document.getElementsByClassName(`page-item-${currentPage}`);
pageItem[0].classList.add("active"); // 페이징 번호 active 스타일 적용 처리
};

//페이지 이동
function movePage(page){
_page = page;
_start = Number(String(page) +  '0') - 10;
_limit = Number(String(page) + '0');

let activeItem = document.getElementsByClassName('active');
activeItem[0].classList.remove('active') //기존 active 스타일 제거
let pageItem = document.getElementsByClassName(`page-item-${page}`);
pageItem[0].classList.add("active"); // 페이징 번호 active 스타일 적용 처리

getList()
}

//페이징 다음 목록 이동
function nextPage(last){
let page_ul = document.getElementById('page_ul');
while (page_ul.hasChildNodes() ) {
page_ul.removeChild(page_ul.firstChild);
}
page_ul.remove();

renderPagination(data, last + 1);
movePage(last+1)
}

//페이징 이전 목록 이동
function prePage(first){
	let page_ul = document.getElementById('page_ul');
		while (page_ul.hasChild()) {
			page_ul.removeChild(page_ul.firstChild);
		}
	page_ul.remove();

	renderPagination(data, first-10); //이전 페이지는 현재의 시작페이지의 -10에서 시작
	movePage(first-10);
}

//게시판 상세 내용 (작성 버튼 수정버튼으로 변경, 데이터 끌어오기)
$("#dataTable").on('click', 'tr', function (e) {

	var cellIndex = e.target.cellIndex;
	if(cellIndex != 5){
	var array = new Array();
	var td = $(this).children();
	td.each(function(i){
		array.push(td.eq(i).text());
	});
	}
		const seq = td.eq(0).text();
    	const title = td.eq(1).text();
        const content =td.eq(2).text();
        const writer = td.eq(3).text();
        const file = document.querySelector("#file");



//    	const data = {
//        		"title" : title,
//        		"writer" : writer,
//        		"content" : content,
//        	}

//    	 const formData = new FormData(form);
//
//                	formData.append('data', new Blob([JSON.stringify(data)], { type: "application/json" }));
//
//                    formData.encType ='multipart/form-data';
//                    const file = document.querySelector('#file').files[0];
//
//                    if(file !== null){
//                    formData.append("file", file);
//                    }

    	fetch(`/notice/${seq}`, {
    	method : "GET",
    	headers : {
//    	'Content-type' :'multipart/form-data',
		'Content-type' : 'application/json',
    	}
    	})
    	.then(response => response.json())
    	.then(data => {
    	console.log(data);
    	setContent(data);
    	})
    	.catch((e) => {
    	console.log(e);
    	});


//
//
//
//  fetch(`/notice/seq`, {
//   method: "GET",
//      headers: {
//        'X-Requested-With': 'XMLHttpRequest',
//      }
//    })
//    .then((response) => response.json())
//    .then(data => {
//      console.log(data)
//      setFileContent(data);
//    })
//    .catch((e) => {
//      console.log(e);
//    });
//

//                file.filename = data.client_file_name;
//

modal.show();

const updateBtn = document.querySelector("#updateBtn");
const deleteBtn = document.querySelector("#deleteBtn");
const fileDownBtn = document.querySelector("#fileDownBtn");

updateBtn.dataset.seq = seq;
deleteBtn.dataset.seq = seq;
fileDownBtn.dataset.seq = seq;

insertBtn.style.display="none";
updateBtn.style.display="block";
deleteBtn.style.display="block";
file.value='';
});

// 모달을 열었을 때 해당 게시판의 내용을 db로부터 가져오기.
  function setContent(data){

  const title = document.querySelector("#title");
  const writer = document.querySelector("#writer");
  const content = document.querySelector("#content");
  const showFile = document.querySelector("#showFile");
  const deleteFileBtn = document.querySelector("#deleteFileBtn");
  const fileDownBtn = document.querySelector("#fileDownBtn");
  console.log(fileDownBtn);

    title.value = data.title;
    writer.value = data.writer;
    content.value = data.content;
    console.log(data.client_file_name);
    showFile.value = '';
        if(data.client_file_name != null){
            showFile.style.display = "block";
            deleteFileBtn.style.display = "block";
            fileDownBtn.style.display = "block";
            showFile.value = data.client_file_name;
        }else {
            showFile.style.display = "none";
            deleteFileBtn.style.display = "none";
            fileDownBtn.style.display = "none";
        }
  }

//모달 오픈
function modalOpen(){
	const openModalBtn = document.querySelector("#open-modal");

		openModalBtn.addEventListener("click", ()=>{
		  const title = document.querySelector("#title");
          const writer = document.querySelector("#writer");
          const content = document.querySelector("#content");

			modal.show();
				title.value='';
                writer.value='';
                content.value='';
                file.value='';

          const insertBtn = document.querySelector("#insertBtn");
          	const updateBtn = document.querySelector("#updateBtn");
              const deleteBtn = document.querySelector("#deleteBtn");

                           insertBtn.style.display="block";
                           updateBtn.style.display="none";
                           deleteBtn.style.display="none";
		});
	}

//모달 닫기
function modalClose(){
	const closeModalBtn = document.querySelectorAll(".close");
	closeModalBtn.forEach( (e)=>{
		e.addEventListener('click', ()=>{
			console.log("닫기 버튼 활성화");
			modal.hide();
		});
	});
}

//게시글 데이터
function save(data){
	const inputTitle = document.querySelector('#title');
	const inputWriter = document.querySelector('#writer');
	const inputContent = document.querySelector('#content');

	data.title = inputTitle.value ;
	data.writer = inputWriter.value ;
	data.content = inputContent.value ;
      }

//게시글 저장
function boardSave(){
	const title =document.querySelector("#title").value;
	const writer =document.querySelector("#writer").value;
	const content =document.querySelector("#content").value;

	const formData = new FormData(form);

	const data = {
		"title" : title,
		"writer" : writer,
		"content" : content,
	}

formData.append('data', new Blob([JSON.stringify(data)], { type: "application/json" }));

formData.encType ='multipart/form-data';
const file = document.querySelector('#file').files[0];

if(file !== null){
formData.append("file", file);
}

fetch(`/insertProc`, {
    method: "POST",
    headers: {
       'X-Requested-With': 'XMLHttpRequest',
    },
    body: formData,
  })
      .then((response) => response.json())
      .then(data => {
      console.log(data);
      if(data > 0){
      alert("등록이 완료되었습니다.");
      modal.hide();
      getList();
      }else {
      alert("등록에 실패하였습니다.");
      modal.hide();
      }
      })
      .catch((e) => {
        console.log(e);
      });
}
//
//// 업로드 파일
//function upload(formData){
//
//formData.encType ='multipart/form-data';
//const file = document.querySelector('#fileInput').files[0];
//
//formData.append('seq', file);
//
//try {
//const response = fetch("/insertProc", {
//method:"POST",
//body: formData,
//});
//const result = response.json();
//} catch(error) {
//console.error("실패", error);
//}
//}

//삭제 로직
function deleteFile(){
const file = document.querySelector("#showFile");
file.value ='';
}

// 저장 버튼
const insertBtn = document.querySelector("#insertBtn");
insertBtn.addEventListener("click", ()=>{
	boardSave();
})


// 수정 버튼
const updateBtn = document.querySelector("#updateBtn");
updateBtn.addEventListener("click", ()=>{
update();
});



// 파일 삭제 버튼
const deleteFileBtn = document.querySelector("#deleteFileBtn");

deleteFileBtn.addEventListener("click", ()=>{
	deleteFile();
	console.log("삭제 완료");
});

//게시판 삭제 버튼
const deleteBtn = document.querySelector("#deleteBtn");

deleteBtn.addEventListener("click", ()=>{
deleteNotice();
});


//수정로직
function update (){
const updateBtn = document.querySelector("#updateBtn");
const seq = updateBtn.dataset.seq;

const title = document.querySelector("#title").value.trim();
const writer = document.querySelector("#writer").value;
const content = document.querySelector("#content").value.trim();
const fileFlag = document.querySelector("#showFile").value;
console.log(fileFlag);

if(title == ''){
alert('제목은 필수값입니다.')
return
}
if(content == ''){
alert('내용은 필수값입니다.')
return
}

	const formData = new FormData(form);

    	const data = {
    		"title" : title,
    		"writer" : writer,
    		"content" : content,
    		"fileFlag" : fileFlag,
    		"seq":seq
    	}

    formData.append('data', new Blob([JSON.stringify(data)], { type: "application/json" }));

    formData.encType ='multipart/form-data';
    const file = document.querySelector('#file').files[0];


    if(file !== null){
    formData.append("file", file);
    console.log(file);
    }

	fetch(`/updateProc`, {
        method: "PUT",
        headers: {
           'X-Requested-With': 'XMLHttpRequest',
        },
        body: formData,
      })
          .then((response) => response.json())
          .then(data => {
          console.log(data);
          if(data > 0){
          alert("수정이 완료되었습니다.");
          modal.hide();
          getList();
          }else {
          alert("수정에 실패하였습니다.");
          }
          })
          .catch((e) => {
            console.log(e);
          });
}
//삭제 로직
function deleteNotice(){
const seq = deleteBtn.dataset.seq;

fetch(`/delete/${seq}`, {
        	method : "DELETE",
        	})
        	.then(response => response.json())
        	.then(data => {
        	console.log('삭제 완료.');
        	modal.hide();
        	getList();
        	})
        	.catch((e) => {
        	console.log(e);
        	});
}

//다운로드 버튼

const fileDownBtn = document.querySelector("#fileDownBtn");

fileDownBtn.addEventListener('click' , ()=>{
fileDown();
})

// 다운로드 로직
function fileDown(){
const fileDownBtn = document.querySelector("#fileDownBtn");
const filename = document.querySelector("#showFile").value;

const seq = fileDownBtn.dataset.seq;


fetch(`/fileDown/${seq}`, {
        method: "GET",
        headers: {
           'Content-type': 'application/json',
        },
      })
          .then((response) => response.blob())
          .then(data => {
          console.log("다운로드 로직" + data);
          const a = document.createElement("a");
          		  a.href = window.URL.createObjectURL(data);
          		  a.download = filename;
          		  a.click();
          modal.hide();
          getList();
          })
          .catch((e) => {
            console.log(e);
          });
}



