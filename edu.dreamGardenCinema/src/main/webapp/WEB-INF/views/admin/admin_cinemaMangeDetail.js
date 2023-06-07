const box = document.querySelector('.admin_cinemaManageDetailDate .box');
const innerBox = document.querySelector('.admin_cinemaManageDetailDate .innerBox');
const input = document.querySelector('.admin_cinemaManageDetailDate input');
let tags = null;
initHandler();

function initHandler(){ // 초기화
    let repo = localStorage.tagsList;

    if(repo == ''){
        localStorage.tagsList = `["테스트", "해보세요", "저장은됩니다", "다만", "이블로그는", "개발자도구가", "금지되어있습니다"]`;
    } else if(repo == '[]'){
        localStorage.tagsList = `["테스트", "해보세요", "저장은됩니다", "다만", "이블로그는", "개발자도구가", "금지되어있습니다"]`;
    }
    // Events
    box.addEventListener('click', ()=>{ input.focus() });
    box.addEventListener('click', removeTagHandler);
    input.addEventListener('keydown', addTagHandler);
    input.addEventListener('keydown', removeLastTagHandler);
    render();
}

// 로컬스토리지 가져오기
function getStorage(){
    tags = localStorage.tagsList?JSON.parse(localStorage.tagsList):[];
}

// 로컬스토리지 업데이트
function updateStorage(){
    localStorage.tagsList = JSON.stringify(tags);
    render();
}

// 태그 추가
function addTagHandler(ev){ // 태그 추가
    if(ev.key === "Enter"){
        let sp = input.value.split(' ');
        sp.forEach(tag=>{
            if(tag.length>0){
                tags.push(tag);
            }
        })
        updateStorage();
        input.value = "";
    }
}

// 태그 제거
function removeTagHandler(ev){
    let target = ev.target;
    if(target.tagName === "SPAN" && target.className === "close"){
        let textNode = target.previousElementSibling.innerText.trim();
        tags = tags.filter(tag=> textNode !== tag);
        updateStorage();
    }
}

function removeLastTagHandler(ev){
    let target = ev.target;
    if(ev.key === 'Backspace' && target.value.length == 0){
        tags.pop();
        updateStorage();
    }
}

// 출력부
function render(){
    getStorage();
    let tmp = '';
    let template = tag => `<span class="tag"><span>${tag}</span><span class="close">&times;</span></span>`;
    tags.forEach(tag=>{
        tmp += template(tag);
    });
    innerBox.innerHTML = tmp;
}