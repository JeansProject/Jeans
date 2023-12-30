const login = {

  init: function() {
    this.bindEvent();
  },

  bindEvent: function() {
    console.log(document.querySelector('#loginBtn'))
    document.querySelector('#loginBtn').addEventListener('click', this.login.bind(this));
  },

  login: function() {
    console.log('login btn clicked');
    const loginUser = this.getLoginInfo();
    console.log(loginUser);

    fetch(`/login/verify`, {
      method: 'POST',
      headers: {
        'Content-type': 'application/json',
      },
      body: JSON.stringify(loginUser)
    })
    .then(response => {
      console.log(response)
      return response.text()
    })
    .then(data => {
      console.log(data);
      if(data) location.href = '/main';
      else return alert('로그인 정보를 확인하세요.');
    })
    .catch(error => {
      console.log(error)
    })
  },

  getLoginInfo: function() {
    const id = document.querySelector('#id').value;
    const password = document.querySelector('#password').value;

    const loginUser = {
      id,
      password
    }

    return loginUser;
  },
}

login.init();