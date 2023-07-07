const body = document.querySelector('body');

const play = document.querySelectorAll('.playBtn');

for(let i=0;i<play.length;i++){
    play[i].addEventListener("click", e => {

        fetch("/movie/trailer/"+ e.currentTarget.getAttribute("movieNo"))
        .then(resp => resp.text())
        .then(url => {
            const wrapDiv = document.createElement('div');
            wrapDiv.classList.add('wrapDiv');
            const totalDiv = document.createElement('div');
            totalDiv.classList.add('totalFrame');
            const div = document.createElement('div');
            div.classList.add('movieFrame');
            div.innerHTML = `<iframe src='${url}' frameborder='no' scrolling='no' marginwidth='0' marginheight='0' WIDTH='890' HEIGHT='590' allow='autoplay' style='border-radius:10px' allowfullscreen></iframe>`
            totalDiv.append(div);

            const xDiv = document.createElement('div');
            xDiv.innerHTML = `
            <svg class="xBtn" width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M28.3346 28.3334L11.668 11.6667M28.3346 11.6667L11.668 28.3334" stroke="white" stroke-width="3.33333" stroke-linecap="round"/>
            </svg>`;
            totalDiv.append(div, xDiv);
            wrapDiv.append(totalDiv);
            body.append(wrapDiv);

            const xBtn = document.querySelector(".xBtn");
            xBtn.addEventListener("click", e=>{
                console.log(e.currentTarget.parentNode.parentNode.parentNode);
                e.currentTarget.parentNode.parentNode.parentNode.remove();
            });
        })
    })
}