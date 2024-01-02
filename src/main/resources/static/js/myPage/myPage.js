const myPage = {

	init: function() {
		console.log('my page js init');
	},

	getUserInfoById: function() {
		const id = 'aa';
		//물음표 앞까지는 url 매핑 ? 뒤에는 get 방식으로 키와 벨류 형식
		//이것이 파라미터로 넘어가는데 request header에 담아서 넘어간다.
		// 여기서 id는 키값이다.
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
	},

	setUserInfo: function(data) {
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
}

myPage.init();