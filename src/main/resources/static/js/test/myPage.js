console.log("my page init")

const updateButton = document.querySelector('#updateButton');

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

function updateUserInfoById() {
  const id = document.querySelector('#id').value;
  const name = document.querySelector('#name').value;
  const phone = document.querySelector('#phone').value;

  const user = {
    "name": name,
    "phone": phone
  }

  fetch(`/update-user-info/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user)
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

updateButton.addEventListener('click', function() {
  updateUserInfoById();
})

getUserInfoById();
