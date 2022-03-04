


		let intro = document.getElementById('intro');
		let one = document.getElementById('one');

		let link_main = document.getElementById('link_main')
		let link_one = document.getElementById('link_one');		

		link_main.onclick = evt => {
			intro.style.display = 'flex';
			one.style.display = "none";
			evt.preventDefault();
			evt.stopPropagation();

		}

		link_one.onclick = evt => {
			intro.style.display = 'none';
			one.style.display = "flex";
			evt.preventDefault();
			evt.stopPropagation();

		}