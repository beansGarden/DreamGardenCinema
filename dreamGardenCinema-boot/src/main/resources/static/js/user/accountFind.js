// 탭
$(document).ready(function(){

    $('ul.account-tabs li').click(function(){
    var tab_id = $(this).attr('data-tab');

    $('ul.account-tabs li').removeClass('current');
    $('.tab-content').removeClass('current');

    $(this).addClass('current');
    $("#"+tab_id).addClass('current');
    })

})

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