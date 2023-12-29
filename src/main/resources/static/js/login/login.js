const login = {
  init() {
    const loginBtn = document.querySelector('#loginBtn');
    this.bindEvent.bind(this);
  },

  bindEvent() {
    this.login();
  },

  login() {
    const loginUser = this.getLoginInfo();

    fetch(`/verify`, {
      method: 'POST',
      headers: {
        'Content-type': 'application/json',
      },
      body: JSON.stringify(loginUser)
    })
    .then(response => response.text())
    .then(data => {
      console.log(data);
    })
    .catch(error => {
      console.log(error)
    })
  },

  getLoginInfo() {
    const id = document.querySelector('#id');
    const password = document.querySelector('#password');

    const loginUser = {
      id,
      password
    }

    return loginUser;
  },
}

login.init();