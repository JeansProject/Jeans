console.log("test js init")

const loginButton = document.querySelector('#loginButton');
loginButton.addEventListener('click', function() {
  getUserInfo();
})

const registerButton = document.querySelector('#registerButton');
registerButton.addEventListener('click', function() {
  register();
});

function getUserInfo() {
  const id = document.querySelector('#id').value;
  const password = document.querySelector('#password').value;

  const userInfo = {
    'id': id,
    'password': password
  }

  let user = {};
  user.id = id;
  user.password = password;

  console.log('userInfo = ', userInfo)
  console.log('user = ', user)

  fetch("/login", {
    method: "POST", // 또는 'PUT'
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userInfo),
  })
  .then((response) => response.json())
  .then(data => {
    console.log(data)
    console.log(data.seq);
    console.log(data.id);
    console.log(data.name);
  })
  .catch((e) => {
    console.log(e);
  });
}

function register() {
  const id = document.querySelector('#id').value;
  const password = document.querySelector('#password').value;

  const userInfo = {
    'id': id,
    'password': password
  }

  fetch("/register", {
    method: "POST", // 또는 'PUT'
    headers: {
      "Content-Type": "application/json",
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