
const createMovieBtn = document.getElementById("typeCreateMovie");
// const createHighlightBtn = document.getElementById("typeCreateHighlight");
// const UpdateHighlightBtn  = document.getElementById("typeUpdateHighlight");
const readBtn = document.getElementById("typeRead");
const updateBtn = document.getElementById("typeUpdate");
const deleteBtn = document.getElementById("typeDelete");
const GoListBtn = document.getElementById("typeGoList");

const createContent = document.querySelector(".create");
const readContent = document.querySelector(".read");
const updateContent = document.querySelector(".update");


if(screenType == 'wait'){
    // createHighlightBtn.classList.add("process-type-none");
    // UpdateHighlightBtn.classList.add("process-type-none");
    if(processType == 'create'){
        updateBtn.classList.add("process-type-none");
        deleteBtn.classList.add("process-type-none");
        readBtn.classList.add("process-type-none");
    }
}
if(screenType == 'promise'){
    createMovieBtn.classList.add("process-type-none");
    // createHighlightBtn.classList.add("process-type-none")
    // UpdateHighlightBtn.classList.add("process-type-none")
    deleteBtn.classList.add("process-type-none");
}
if(screenType == 'current'){
    createMovieBtn.classList.add("process-type-none");
    // createHighlightBtn.classList.add("process-type-none")
    // UpdateHighlightBtn.classList.add("process-type-none")
    deleteBtn.classList.add("process-type-none");
}
if(screenType == 'down'){
    createMovieBtn.classList.add("process-type-none");
    // createHighlightBtn.classList.add("process-type-none")
    // UpdateHighlightBtn.classList.add("process-type-none")
}
if(screenType == 'highlight'){
    createMovieBtn.classList.add("process-type-none");
    readBtn.classList.add("process-type-none");
    updateBtn.classList.add("process-type-none");
    deleteBtn.classList.add("process-type-none");
}
if(screenType == 'all'){
    createMovieBtn.classList.add("process-type-none");
    // createHighlightBtn.classList.add("process-type-none")
    // UpdateHighlightBtn.classList.add("process-type-none")
}



if(processType == "create" ){
    // if(screenType == 'highlight'){
    //     createHighlightBtn.classList.add("process-type-active");
    // }
    createMovieBtn.classList.add("process-type-active");
    createContent.classList.add("detail-active");
}

if(processType == "read" ){
    readBtn.classList.add("process-type-active");
    readContent.classList.add("detail-active");
}

if(processType == "update" ){
    // if(screenType == 'highlight'){
    //     UpdateHighlightBtn.classList.add("process-type-active");
    // }
    updateBtn.classList.add("process-type-active");
    updateContent.classList.add("detail-active");
}

if(processType == "delete" ){
    deleteBtn.classList.add("process-type-active");
}


createMovieBtn.addEventListener('click', () => {
    location.href="detail?movieNo=0&type=create&screen=wait";
    createMovieBtn.classList.add("process-type-active");
    // createHighlightBtn.classList.remove("process-type-active");
    // UpdateHighlightBtn.classList.remove("process-type-active");
    readBtn.classList.remove("process-type-active");
    updateBtn.classList.remove("process-type-active");
    deleteBtn.classList.remove("process-type-active");
});

// createHighlightBtn.addEventListener('click', () => {
//     createMovieBtn.classList.remove("process-type-active");
//     // createHighlightBtn.classList.add("process-type-active")
//     // UpdateHighlightBtn.classList.remove("process-type-active");
//     readBtn.classList.remove("process-type-active");
//     updateBtn.classList.remove("process-type-active");
//     deleteBtn.classList.remove("process-type-active");
// });

// UpdateHighlightBtn.addEventListener('click', () => {
//     createMovieBtn.classList.remove("process-type-active");
//     // createHighlightBtn.classList.remove("process-type-active");
//     // UpdateHighlightBtn.classList.add("process-type-active")
//     readBtn.classList.remove("process-type-active");
//     updateBtn.classList.remove("process-type-active");
//     deleteBtn.classList.remove("process-type-active");
// });

readBtn.addEventListener('click', () => {
    location.href="/adminMovieManage/detail?movieNo="+ movieNo +"&type=read&screen=" + screenType;
    createMovieBtn.classList.remove("process-type-active");
    // createHighlightBtn.classList.remove("process-type-active");
    // UpdateHighlightBtn.classList.remove("process-type-active");
    readBtn.classList.add("process-type-active");
    updateBtn.classList.remove("process-type-active");
    deleteBtn.classList.remove("process-type-active");
    readContent.classList.add("detail-active");
});

updateBtn.addEventListener('click', () => {
    location.href="/adminMovieManage/detail?movieNo="+ movieNo +"&type=update&screen=" + screenType;
    createMovieBtn.classList.remove("process-type-active");
    // createHighlightBtn.classList.remove("process-type-active");
    // UpdateHighlightBtn.classList.remove("process-type-active");
    readBtn.classList.remove("process-type-active");
    updateBtn.classList.add("process-type-active")
    deleteBtn.classList.remove("process-type-active");
    updateContent.classList.add("detail-active");
});

deleteBtn.addEventListener('click', () => {
    createMovieBtn.classList.remove("process-type-active");
    // createHighlightBtn.classList.remove("process-type-active");
    // UpdateHighlightBtn.classList.remove("process-type-active");
    readBtn.classList.remove("process-type-active");
    updateBtn.classList.remove("process-type-active");
    deleteBtn.classList.add("process-type-active")
});


const selectElementUpdateScreening = document.getElementsByName('updateScreening')[0];

for (let i = 0; i < selectElementUpdateScreening.options.length; i++) {
    if (selectElementUpdateScreening.options[i].value === screenType) {
        selectElementUpdateScreening.selectedIndex = i;
        break;
    }
}
    


const selectElementUpdateRating = document.getElementsByName('updateRating')[0];
for (let i = 0; i < selectElementUpdateRating.options.length; i++) {
    if (selectElementUpdateRating.options[i].value === rating) {
        selectElementUpdateRating.selectedIndex = i;
        break;
    }
}



var originalImageSrc = document.getElementById("updatePosterImage").src;

document.getElementById("updatePoster").addEventListener("change", function(event) {
    var input = event.target;
    var reader = new FileReader();

    reader.onload = function() {
        var dataURL = reader.result;
        var posterImage = document.getElementById("updatePosterImage");
        posterImage.src = dataURL;
    };

    reader.readAsDataURL(input.files[0]);
});

document.getElementById("updatePosterImage").addEventListener("click", function() {
    var posterImage = document.getElementById("updatePosterImage");
    posterImage.src = originalImageSrc;
});



function openImageEditor(imageElement) {
    var input = imageElement.nextElementSibling; // 인접한 input 요소 선택
    input.click(); // input 클릭 이벤트 트리거
}

function updateImage(inputElement) {
    var file = inputElement.files[0];
    if (file) {
        var reader = new FileReader();

        reader.onload = function (e) {
            var imageElement = inputElement.previousElementSibling; // 이전 요소인 이미지 선택
            imageElement.src = e.target.result;
        }

        reader.readAsDataURL(file);
    } else {
        var imageElement = inputElement.previousElementSibling; // 이전 요소인 이미지 선택
        var originalSrc = inputElement.dataset.originalSrc;
        imageElement.src = originalSrc;
    }
}