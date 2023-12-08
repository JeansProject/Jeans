/*
1. 아이디 input에서 값 가져오기
2. 비밀번호 input ..

3. 가져온 데이터들을 json 객체로 만들기
4. fetch 사용해서 서버로 요청 보내기
5. 서버에서 넘어온 response 확인하기
 */

const registerBtn = document.querySelector('#registerBtn');
registerBtn.addEventListener('click', function() {
    const id = document.querySelector('#id').value;
    // const password = document.querySelector('#password').value;
    const username = document.querySelector('#username').value;
    const age = document.querySelector('#age').value;
    const phone = document.querySelector('#phone').value;

    console.log(id)
    // console.log(password)
    console.log(username)
    console.log(age)
    console.log(phone)

    const registerUser = {
        "id": id,
        "username": username,
        "age": age,
        "phone": phone
    }

    console.log(registerUser)
    console.log(registerUser.username)
    console.log(registerUser.phone)


})