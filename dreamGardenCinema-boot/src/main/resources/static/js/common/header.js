const dropdownBtn = document.querySelector(".dropdown-btn");
const showMenu = document.querySelector(".show-menu");

dropdownBtn.addEventListener('click', ()=>{
    showMenu.classList.toggle("display-none");
});

