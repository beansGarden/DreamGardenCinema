const toggleMenus = document.querySelectorAll(".toggle-menu");
    toggleMenus.forEach((menu) => {
        menu.addEventListener("click", () => {
            const submenu = menu.nextElementSibling;
            submenu.classList.toggle("active");
          });
        });