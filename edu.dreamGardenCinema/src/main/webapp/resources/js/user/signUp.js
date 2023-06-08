// input span 애니메이션
const inputs = document.querySelectorAll('input');

inputs.forEach(input => {
    input.addEventListener('focus', addClass);
    input.addEventListener('input', addClass);
    input.addEventListener('blur', removeClass);
});

function addClass(event) {
    const input = event.target;
    const span = input.nextElementSibling;
    if (span.tagName.toLowerCase() === "span") {
    span.classList.remove('output-font-size-ani');
    span.classList.add('input-font-size-ani');
    }
}

function removeClass(event) {
    const input = event.target;
    const span = input.nextElementSibling;
    if (input.value.trim() === '' && span.tagName.toLowerCase() === "span") {
        span.classList.remove('input-font-size-ani');
        span.classList.add('output-font-size-ani');
    }
}

/* 예시) 클릭 시 원하는곳에 경고 svg 추가 */
const sampleBtn = document.getElementById("sampleBtn");

sampleBtn.addEventListener("click", function() {
    const nullInputIconBox = document.createElement("div");
    nullInputIconBox.classList.add("null-input-icon-box");

    const svgCode = '<svg class="null-input-icon" fill="#d7373f" xmlns="http://www.w3.org/2000/svg" height="20" viewBox="0 -960 960 960" width="20"><path d="m40-120 440-760 440 760H40Zm104-60h672L480-760 144-180Zm340.175-57q12.825 0 21.325-8.675 8.5-8.676 8.5-21.5 0-12.825-8.675-21.325-8.676-8.5-21.5-8.5-12.825 0-21.325 8.675-8.5 8.676-8.5 21.5 0 12.825 8.675 21.325 8.676 8.5 21.5 8.5ZM454-348h60v-224h-60v224Zm26-122Z"/></svg>';

    nullInputIconBox.innerHTML = svgCode;

    const headerSearchModContainer = document.getElementById("userIdErrIcon");
    headerSearchModContainer.appendChild(nullInputIconBox);
});