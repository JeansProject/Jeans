/*
1. 아이디 input에서 값 가져오기
2. 비밀번호 input ..

3. 가져온 데이터들을 json 객체로 만들기
4. fetch 사용해서 서버로 요청 보내기
5. 서버에서 넘어온 response 확인하기
 */

// 아이디 입력창 정보 가져오기
let elInputId = document.querySelector('#id');
// 성공 메세지 정보 가져오기
let elSuccessMessage = document.querySelector('.success-message');
// 실패 메세지 정보 가져오기 (글자수 제한)
let elFailureMessage = document.querySelector('.failure-message');
// 실패 메세지 정보 가져오기 (영어, 숫자)
let elFailureMessageTwo = document.querySelector('.failure-message2');

// 비밀번호 입력창 정보 가져오기
let elInputPassword = document.querySelector('#password');
// 비밀번호 확인 입력창 정보 가져오기
let elInputPasswordRetype = document.querySelector('#password-retype');
// 실패 메세지 정보 가져오기 (비밀번호 불일치)
let elMismatchMessage = document.querySelector('.mismatch-message');
// 실패 메세지 정보 가져오기 (8글자 이상, 영문, 숫자, 특수문자 미사용)
let elStrongPasswordMessage = document.querySelector('.strongPassword-message');

// 아이디 글자수 제한
function idLength(value) {
    return value.length >= 4 && value.length <= 12
}
// 아이디 영어 또는 숫자만
function idNumberAndEng(str) {
    return /^[A-Za-z0-9][A-Za-z0-9]*$/.test(str);
}

// 비밀번호 8글자 이상, 영문, 숫자, 특수문자 사용
function strongPassword(str) {
    return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(str);
}

// 비밀번호 확인
function isMatch(password1, password2) {
    return password1 === password2;
}

elInputId.onkeyup = function () {
    // 값을 입력한 경우
    if (elInputId.value.length !== 0) {
        if (idNumberAndEng(elInputId.value) === false) {
            elSuccessMessage.classList.add('hide');
            elFailureMessage.classList.add('hide');
            elFailureMessageTwo.classList.remove('hide');
        } else if (idLength(elInputId.value) === false) {
            elSuccessMessage.classList.add('hide');
            elFailureMessage.classList.remove('hide');
            elFailureMessageTwo.classList.add('hide');
        } else if (idLength(elInputId.value) || idNumberAndEng(elInputId.value)) {
            elSuccessMessage.classList.remove('hide');
            elFailureMessage.classList.add('hide');
            elFailureMessageTwo.classList.add('hide');
        }
    } else { 
        // 값을 입력하지 않은 경우 (지웠을 때)
        elSuccessMessage.classList.add('hide');
        elFailureMessage.classList.add('hide');
        elFailureMessageTwo.classList.add('hide');
    }
}

elInputPassword.onkeyup = function () {
    // 값을 입력한 경우
    if (elInputPassword.value.length !== 0) {
        if (strongPassword(elInputPassword.value)) {
            elStrongPasswordMessage.classList.add('hide');
        } else {
            elStrongPasswordMessage.classList.remove('hide');
        }
    } else {
        // 값을 입력하지 않은 경우 (지웠을 때)
        elStrongPasswordMessage.classList.add('hide');
    }
}

elInputPasswordRetype.onkeyup = function () {
    if (elInputPasswordRetype.value.length !== 0) {
        if (isMatch(elInputPassword.value, elInputPasswordRetype.value)) {
            elMismatchMessage.classList.add('hide');
        } else {
            elMismatchMessage.classList.remove('hide');
        }
    } else {
        // 값을 입력하지 않은 경우 (지웠을 때)
        elMismatchMessage.classList.add('hide');
    }
}

const registerBtn = document.querySelector('#registerBtn');
registerBtn.addEventListener('click', function () {
    const username = document.querySelector('#username').value.trim();
    const id = document.querySelector('#id').value.trim();
    const password = document.querySelector('#password').value.trim();
    const email = document.querySelector('#email').value.trim();
    const phone = document.querySelector('#phone').value.trim();
    const age = document.querySelector('#age').value.trim();
    const birthday = document.querySelector('#birthday').value.trim();

    console.log(username)
    console.log(id)
    console.log(password)
    console.log(email)
    console.log(phone)
    console.log(age)
    console.log(birthday)

    const registerUser = {
        'name': username,
        'id': id,
        'password': password,
        'email': email,
        'phone': phone,
        'age': age,
        'birthday': birthday
    }

    fetch('/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerUser)
    })
        .then((response) => {
            console.log(response)
            // response.json()
            return response.text()
        })
        .then((data) => {
            console.log(data)
            data.status === 200 ? alert('저장되었습니다.') : alert('저장에 실패하였습니다.');
        })
        .catch((e) => {
            console.log(e);
        });

    console.log(registerUser)
    console.log(registerUser.username)
    console.log(registerUser.phone)
    console.log(registerUser.age)
    console.log(registerUser.birthday)
})

