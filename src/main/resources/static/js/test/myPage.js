console.log("my page init")

function getUserInfoById() {
  const id = 'aa';

  fetch(`/get-user-info?id=${id}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    }
  })
  .then((response) => response.json())
  .then(data => {
    console.log(data)
    setUserInfo(data);
  })
  .catch((e) => {
    console.log(e);
  });

}

function setUserInfo(data) {
  const idInput = document.querySelector('#id');
  const passwordInput = document.querySelector('#password');
  const nameInput = document.querySelector('#name');
  const emailInput = document.querySelector('#email');
  const phoneInput = document.querySelector('#phone');

  idInput.value = data.id;
  passwordInput.value = data.password;
  nameInput.value = data.name;
  emailInput.value = data.email;
  phoneInput.value = data.phone;
}

getUserInfoById();
