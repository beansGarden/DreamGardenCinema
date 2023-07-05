const dropdownBtn = document.querySelector('.dropdown-btn');
const showMenu = document.querySelector('.show-menu');

dropdownBtn.addEventListener('click', function() {
    showMenu.classList.toggle('visible');
});