/*
1. 아이디 input에서 값 가져오기
2. 비밀번호 input ..

3. 가져온 데이터들을 json 객체로 만들기
4. fetch 사용해서 서버로 요청 보내기
5. 서버에서 넘어온 response 확인하기
 */

const registerBtn = document.querySelector('#registerBtn');
registerBtn.addEventListener('click', function () {
    const username = document.querySelector('#username').value;
    const id = document.querySelector('#id').value;
    const password = document.querySelector('#password').value;
    const email = document.querySelector('#email').value;
    const phone = document.querySelector('#phone').value;
    const age = document.querySelector('#age').value;
    const birthday = document.querySelector('#birthday').value;

    console.log(username)
    console.log(id)
    console.log(password)
    console.log(email)
    console.log(phone)
    console.log(age)
    console.log(birthday)

    const registerUser = {
        'username': username,
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
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
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

//     fetch('/register', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify(userInfo),
//     })
//         .then((response) => response.json())
//         .then(data => {
//             console.log(data)
//         })
//         .catch((e) => {
//             console.log(e);
//         });

// })

// const registerBtn = document.querySelector('#registerBtn');
// registerBtn.addEventListener('click', function() {
//     register();
// })

// function register() {
//     const name = document.querySelector('#name').value;
//     const id = document.querySelector('#id').value;
//     const password = document.querySelector('#password').value;
//     const email = document.querySelector('#email').value;
//     const age = document.querySelector('#age').value;
//     const phone = document.querySelector('#phone').value;
//     const birthday = document.querySelector('#birthday').value;

//     const userInfo = {
//         'name' : name,
//         'id' : id,
//         'password' : password,
//         'email' : email,
//         'age' : age,
//         'phone' : phone,
//         'birthday' : birthday
//     }

//     fetch('/register', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify(userInfo),
//     })
//     .then((response) => response.json())
//     .then(data => {
//         console.log(data)
//     })
//     .catch((e) => {
//         console.log(e);
//     });

// }

