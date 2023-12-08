const registerBtn = document.querySelector('#registerBtn');
registerBtn.addEventListener('click', function() {
    register();
})

function register() {
    const name = document.querySelector('#name').value;
    const id = document.querySelector('#id').value;
    const password = document.querySelector('#password').value;
    const email = document.querySelector('#email').value;
    const age = document.querySelector('#age').value;
    const phone = document.querySelector('#phone').value;
    const birthday = document.querySelector('#birthday').value;

    const userInfo = {
        'name' : name,
        'id' : id,
        'password' : password,
        'email' : email,
        'age' : age,
        'phone' : phone,
        'birthday' : birthday
    }

    fetch('/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userInfo),
    })
    .then((response) => response.json())
    .then(data => {
        console.log(data)
    })
    .catch((e) => {
        console.log(e);
    });

}

