const body = document.querySelector('body');
body.addEventListener('click', function(e){
    let x = e.pageX;
    let y = e.pageY;

    let color = "#" + Math.floor(Math.random() * 0xFFFFFF).toString(16);

    let ripples = document.createElement('span');
    ripples.classList.add('effect');
    ripples.style.left = x + 'px';
    ripples.style.top = y + 'px';
    ripples.style.borderColor = color;
    body.append(ripples);

    setTimeout(()=>{
        ripples.remove()
    }, 2000);
})