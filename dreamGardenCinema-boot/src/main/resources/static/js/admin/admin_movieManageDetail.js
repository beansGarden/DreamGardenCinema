
const createMovieBtn = document.getElementById("typeCreateMovie");
const createHighlightBtn = document.getElementById("typeCreateHighlight");
const UpdateHighlightBtn  = document.getElementById("typeUpdateHighlight");
const readBtn = document.getElementById("typeRead");
const updateBtn = document.getElementById("typeUpdate");
const deleteBtn = document.getElementById("typeDelete");
const GoListBtn = document.getElementById("typeGoList");

const readContent = document.querySelector(".read");
const updateContent = document.querySelector(".update");

/* 
createMovieBtn
createHighlightBtn
UpdateHighlightBtn
readBtn
updateBtn
deleteBtn
 */



if(screenType == 'wait'){
    createHighlightBtn.classList.add("process-type-none");
    UpdateHighlightBtn.classList.add("process-type-none");
    if(processType == 'create'){
        updateBtn.classList.add("process-type-none");
        deleteBtn.classList.add("process-type-none");
    }
}
if(screenType == 'promise'){
    createMovieBtn.classList.add("process-type-none");
    createHighlightBtn.classList.add("process-type-none")
    UpdateHighlightBtn.classList.add("process-type-none")
    deleteBtn.classList.add("process-type-none");
}
if(screenType == 'current'){
    createMovieBtn.classList.add("process-type-none");
    createHighlightBtn.classList.add("process-type-none")
    UpdateHighlightBtn.classList.add("process-type-none")
    deleteBtn.classList.add("process-type-none");
}
if(screenType == 'down'){
    createMovieBtn.classList.add("process-type-none");
    createHighlightBtn.classList.add("process-type-none")
    UpdateHighlightBtn.classList.add("process-type-none")
}
if(screenType == 'highlight'){
    createMovieBtn.classList.add("process-type-none");
    readBtn.classList.add("process-type-none");
    updateBtn.classList.add("process-type-none");
    deleteBtn.classList.add("process-type-none");
}
if(screenType == 'all'){
    createMovieBtn.classList.add("process-type-none");
    createHighlightBtn.classList.add("process-type-none")
    UpdateHighlightBtn.classList.add("process-type-none")
}



if(processType == "create" ){
    if(screenType == 'highlight'){
        createHighlightBtn.classList.add("process-type-active");
    }
    createMovieBtn.classList.add("process-type-active");
}

if(processType == "read" ){
    readBtn.classList.add("process-type-active");
    readContent.classList.add("detail-active");
}

if(processType == "update" ){
    if(screenType == 'highlight'){
        UpdateHighlightBtn.classList.add("process-type-active");
    }
    updateBtn.classList.add("process-type-active");
    updateContent.classList.add("detail-active");
}

if(processType == "delete" ){
    deleteBtn.classList.add("process-type-active");
}


createMovieBtn.addEventListener('click', () => {

    createMovieBtn.classList.add("process-type-active");
    createHighlightBtn.classList.remove("process-type-active");
    UpdateHighlightBtn.classList.remove("process-type-active");
    readBtn.classList.remove("process-type-active");
    updateBtn.classList.remove("process-type-active");
    deleteBtn.classList.remove("process-type-active");
});

createHighlightBtn.addEventListener('click', () => {
    createMovieBtn.classList.remove("process-type-active");
    createHighlightBtn.classList.add("process-type-active")
    UpdateHighlightBtn.classList.remove("process-type-active");
    readBtn.classList.remove("process-type-active");
    updateBtn.classList.remove("process-type-active");
    deleteBtn.classList.remove("process-type-active");
});

UpdateHighlightBtn.addEventListener('click', () => {
    createMovieBtn.classList.remove("process-type-active");
    createHighlightBtn.classList.remove("process-type-active");
    UpdateHighlightBtn.classList.add("process-type-active")
    readBtn.classList.remove("process-type-active");
    updateBtn.classList.remove("process-type-active");
    deleteBtn.classList.remove("process-type-active");
});

readBtn.addEventListener('click', () => {
    location.href="/adminMovieManage/detail?movieNo="+ movieNo +"&type=read&screen=" + screenType;
    createMovieBtn.classList.remove("process-type-active");
    createHighlightBtn.classList.remove("process-type-active");
    UpdateHighlightBtn.classList.remove("process-type-active");
    readBtn.classList.add("process-type-active");
    updateBtn.classList.remove("process-type-active");
    deleteBtn.classList.remove("process-type-active");
    readContent.classList.add("detail-active");
});

updateBtn.addEventListener('click', () => {
    location.href="/adminMovieManage/detail?movieNo="+ movieNo +"&type=update&screen=" + screenType;
    createMovieBtn.classList.remove("process-type-active");
    createHighlightBtn.classList.remove("process-type-active");
    UpdateHighlightBtn.classList.remove("process-type-active");
    readBtn.classList.remove("process-type-active");
    updateBtn.classList.add("process-type-active")
    deleteBtn.classList.remove("process-type-active");
    updateContent.classList.add("detail-active");
});

deleteBtn.addEventListener('click', () => {
    createMovieBtn.classList.remove("process-type-active");
    createHighlightBtn.classList.remove("process-type-active");
    UpdateHighlightBtn.classList.remove("process-type-active");
    readBtn.classList.remove("process-type-active");
    updateBtn.classList.remove("process-type-active");
    deleteBtn.classList.add("process-type-active")
});


const selectElementScreening = document.getElementsByName('screening')[0];
for (let i = 0; i < selectElementScreening.options.length; i++) {
    if (selectElementScreening.options[i].value === screenType) {
        selectElementScreening.selectedIndex = i;
        break;
    }
}
    


const selectElementRating = document.getElementsByName('rating')[0];
for (let i = 0; i < selectElementRating.options.length; i++) {
    if (selectElementRating.options[i].value === rating) {
        selectElementRating.selectedIndex = i;
        break;
    }
}

