const ratingStars = [...document.getElementsByClassName("rating__star")];

let title = document.getElementById("id");
let name = document.getElementById("name");
let content = document.getElementById("content");
let file = document.getElementById("file");
let i;


function executeRating(stars) {
	const starClassActive = "rating__star fas fa-star";
	const starClassInactive = "rating__star far fa-star";
	const starsLength = stars.length;
	
	
	stars.map((star) => {
		star.onclick = () => {
			i = stars.indexOf(star);

			if (star.className === starClassInactive) {
				for (i; i >= 0; --i) stars[i].className = starClassActive;
			} else {
				for (i; i < starsLength; ++i) stars[i].className = starClassInactive;
			}
		};
	});
}

executeRating(ratingStars);




axios({
  method: 'post',   /*목적지 */
  url: 'https://localhost:3000/reviewBoard',
  data: {
    userName: 'Cocoon',
    userId: 'co1234'
  }
}).then((response) => console.log(response));





