const createMovieBtn = document.getElementById("typeCreateMovie");
const createHighlightBtn = document.getElementById("typeCreateHighlight");
const UpdateHighlightBtn  = document.getElementById("typeUpdateHighlight");
const readBtn = document.getElementById("typeRead");
const updateBtn = document.getElementById("typeUpdate");
const deleteBtn = document.getElementById("typeDelete");
const GoListBtn = document.getElementById("typeGoList");
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
}

if(processType == "update" ){
    if(screenType == 'highlight'){
        UpdateHighlightBtn.classList.add("process-type-active");
    }
    updateBtn.classList.add("process-type-active");
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
    createMovieBtn.classList.remove("process-type-active");
    createHighlightBtn.classList.remove("process-type-active");
    UpdateHighlightBtn.classList.remove("process-type-active");
    readBtn.classList.add("process-type-active");
    updateBtn.classList.remove("process-type-active");
    deleteBtn.classList.remove("process-type-active");
});

updateBtn.addEventListener('click', () => {
    createMovieBtn.classList.remove("process-type-active");
    createHighlightBtn.classList.remove("process-type-active");
    UpdateHighlightBtn.classList.remove("process-type-active");
    readBtn.classList.remove("process-type-active");
    updateBtn.classList.add("process-type-active")
    deleteBtn.classList.remove("process-type-active");
});

deleteBtn.addEventListener('click', () => {
    createMovieBtn.classList.remove("process-type-active");
    createHighlightBtn.classList.remove("process-type-active");
    UpdateHighlightBtn.classList.remove("process-type-active");
    readBtn.classList.remove("process-type-active");
    updateBtn.classList.remove("process-type-active");
    deleteBtn.classList.add("process-type-active")
});

