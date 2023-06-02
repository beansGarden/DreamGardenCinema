const inputs = document.querySelectorAll('input');

inputs.forEach(input => {
    input.addEventListener('focus', addClass);
    input.addEventListener('input', addClass);
    input.addEventListener('blur', removeClass);
});

function addClass(event) {
    const input = event.target;
    const span = input.nextElementSibling;
    span.classList.remove('output-font-size-ani');
    span.classList.add('input-font-size-ani');
}

function removeClass(event) {
    const input = event.target;
    const span = input.nextElementSibling;
    if (input.value.trim() === '') {
        span.classList.remove('input-font-size-ani');
        span.classList.add('output-font-size-ani');
    }
}