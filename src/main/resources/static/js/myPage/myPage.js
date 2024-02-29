const myPage = {

	init: function() {
		console.log('my page js init');
		this.eventBinding();
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
		document.querySelector('#id').value = data.id;
		document.querySelector('#name').value = data.name;
		document.querySelector('#email').value = data.email;
		document.querySelector('#phone').value = data.phone;
	},

	updateUser: function() {
		const seq = document.querySelector('#updateBtn').dataset.seq;
		const id = document.querySelector('#id').value.trim();
		const name = document.querySelector('#name').value.trim();
		const email = document.querySelector('#email').value.trim();
		const phone = document.querySelector('#phone').value.trim();

		const requestBody = {
			name,
			email,
			phone
		}

		fetch(`${_common.getContextPath()}`, {
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(requestBody)
		})
		.then((response) => response.text())
		.then(data => {
			const parsedData = JSON.parse(data);
			console.log(parsedData)

			if(parsedData.message) {
				return alert('수정 중 오류 발생');
			}else {
				alert('수정 완료')
				parsedData.id = id;
				this.setUserInfo(parsedData);
			}
		})
		.catch((e) => {
			console.log(e);
		});
	},

	eventBinding: function() {
		console.log('event binding');
		document.querySelector('#updateBtn').addEventListener('click', this.updateUser);
		document.querySelector('#cancelBtn').addEventListener('click', function() {
			location.href = '/main';
		});
	}
}

myPage.init();