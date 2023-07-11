const selectCont = document.querySelector("#selectCont");
const content = document.querySelector("#content");
const opt = document.querySelectorAll("#opt > option");
const searchDate = document.querySelector("#date");
const theater = document.querySelector("#theater");
const afterCurrent = document.querySelector("#afterCurrent");
const afterLabel = document.querySelector(".afterCurrent");
const innerSvg = document.querySelector(".afterCurrent>a>svg");

afterLabel.addEventListener("click", () => {
    if(afterCurrent.checked){
        innerSvg.innerHTML = `<path d="M11.5051 31.9795L9.00781 36.4854" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M26.4922 31.9795L28.9895 36.4854" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M18.9984 33.9876C27.2754 33.9876 33.9852 27.2778 33.9852 19.0009C33.9852 10.7239 27.2754 4.01416 18.9984 4.01416C10.7215 4.01416 4.01172 10.7239 4.01172 19.0009C4.01172 27.2778 10.7215 33.9876 18.9984 33.9876Z" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M4.51479 15.006C3.36424 14.1436 2.48609 12.9684 1.98513 11.6206C1.48417 10.2729 1.38156 8.80942 1.68953 7.40493C1.99751 6.00044 2.70307 4.71421 3.72203 3.69972C4.74098 2.68523 6.0303 1.98533 7.43614 1.68353C8.84197 1.38172 10.305 1.49077 11.6505 1.99765C12.9961 2.50452 14.1674 3.38783 15.0247 4.54216" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M19 4.01438L19.0001 1.5166" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M19 11.5078V19.0011" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M19 19.001L24.2986 24.2996" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M33.4865 15.006C34.637 14.1436 35.5152 12.9684 36.0161 11.6206C36.5171 10.2729 36.6197 8.80942 36.3117 7.40493C36.0037 6.00044 35.2982 4.7142 34.2792 3.69972C33.2603 2.68523 31.9709 1.98533 30.5651 1.68353C29.1593 1.38172 27.6963 1.49077 26.3507 1.99765C25.0052 2.50452 23.8339 3.38783 22.9766 4.54216" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>`
    } else {
        innerSvg.innerHTML = `<path d="M18.9984 33.9876C27.2754 33.9876 33.9852 27.2778 33.9852 19.0009C33.9852 10.7239 27.2754 4.01416 18.9984 4.01416C10.7215 4.01416 4.01172 10.7239 4.01172 19.0009C4.01172 27.2778 10.7215 33.9876 18.9984 33.9876Z" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>`
    }
});


(()=>{
    const params = new URL(location.href).searchParams;

    const key = params.get("selectOpt");  // t, c, tc, w 중 하나
    const query = params.get("content");  // 검색어
    const date = params.get("date");  // 날짜
    const movieTheater = params.get("movieTheater");
    const after = params.get("afterCurrent");
    if(key != null){
        // 검색을 했을 때
        content.value = query;  // 검색어를 화면에 출력
        searchDate.value = date;
        theater.value = movieTheater;
        if(after == 'Y'){
            afterCurrent.checked = true;
            innerSvg.innerHTML = `<path d="M11.5051 31.9795L9.00781 36.4854" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M26.4922 31.9795L28.9895 36.4854" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M18.9984 33.9876C27.2754 33.9876 33.9852 27.2778 33.9852 19.0009C33.9852 10.7239 27.2754 4.01416 18.9984 4.01416C10.7215 4.01416 4.01172 10.7239 4.01172 19.0009C4.01172 27.2778 10.7215 33.9876 18.9984 33.9876Z" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M4.51479 15.006C3.36424 14.1436 2.48609 12.9684 1.98513 11.6206C1.48417 10.2729 1.38156 8.80942 1.68953 7.40493C1.99751 6.00044 2.70307 4.71421 3.72203 3.69972C4.74098 2.68523 6.0303 1.98533 7.43614 1.68353C8.84197 1.38172 10.305 1.49077 11.6505 1.99765C12.9961 2.50452 14.1674 3.38783 15.0247 4.54216" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M19 4.01438L19.0001 1.5166" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M19 11.5078V19.0011" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M19 19.001L24.2986 24.2996" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M33.4865 15.006C34.637 14.1436 35.5152 12.9684 36.0161 11.6206C36.5171 10.2729 36.6197 8.80942 36.3117 7.40493C36.0037 6.00044 35.2982 4.7142 34.2792 3.69972C33.2603 2.68523 31.9709 1.98533 30.5651 1.68353C29.1593 1.38172 27.6963 1.49077 26.3507 1.99765C25.0052 2.50452 23.8339 3.38783 22.9766 4.54216" stroke="black" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"/>`
        }
        // option태그를 하나씩 순차 접근해서 value가 key랑 같으면
        // selected 속성 추가
        for(let op of opt){
            if(op.value == key){
                op.selected = true;
            }
        }
    }
    if(date != null){
        searchDate.value = date;
    }
})();

// 검색어 입력 없이 제출된 경우
selectCont.addEventListener("submit", e => {

    if(content.value.trim().length == 0 && searchDate.value.trim().length == 0 && theater.value.trim().length == 0 && !afterCurrent.checked){  // 검색어 미입력 시
        e.preventDefault();  // form 기본 이벤트 제거

        location.href = location.pathname;  // 해당 게시판 1페이지로 이동

        // location.pathname : 쿼리스트링을 제외한 실제 주소
    }

});

// 영화 제목 클릭 시 상영시간 가져오기
const movieTt = document.querySelectorAll("#movieTt");
for(let i=0;i<movieTt.length;i++){
    movieTt[i].addEventListener("click", e=>{
        console.log(e.target.innerText);
        console.log(e.target.parentNode.nextElementSibling.nextElementSibling.innerText);
        console.log(e.target.parentNode.previousElementSibling.innerText);

        const movieTitle = e.target.innerText;
        const movieDate = e.target.parentNode.nextElementSibling.nextElementSibling.innerText;
        const movieTheater = e.target.parentNode.previousElementSibling.innerText;

        const timeWrap = e.target.nextElementSibling;
        console.log(timeWrap);

        if(timeWrap == null || timeWrap.innerHTML == ''){
            fetch("/adminCinemaTimeSelect", {
                method : "POST",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify({"movieTitle" : movieTitle, "movieDate" : movieDate, "movieTheater" : movieTheater})
            })
            .then(resp => resp.json())
            .then(timeList => {
                console.log(timeList);

                const parentDiv = document.createElement("div");
                parentDiv.classList.add("timeWrap");
                const div = document.createElement("div");
                div.innerHTML = `<svg width="16" height="16" viewBox="0 0 839 839" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M419.498 69.9165C350.357 69.9165 282.768 90.4192 225.279 128.832C167.791 167.245 122.984 221.842 96.5247 285.72C70.0655 349.598 63.1426 419.888 76.6314 487.7C90.1201 555.513 123.415 617.802 172.305 666.693C221.195 715.583 283.485 748.877 351.297 762.366C419.11 775.855 489.399 768.932 553.277 742.473C617.155 716.014 671.753 671.207 710.165 613.718C748.578 556.229 769.081 488.641 769.081 419.5C769.081 373.592 760.039 328.133 742.47 285.72C724.902 243.307 699.152 204.769 666.69 172.307C634.228 139.845 595.691 114.095 553.277 96.527C510.864 78.9587 465.405 69.9165 419.498 69.9165ZM419.498 699.166C364.185 699.166 310.114 682.764 264.123 652.034C218.132 621.304 182.287 577.626 161.119 526.524C139.952 475.421 134.414 419.19 145.205 364.94C155.996 310.69 182.631 260.858 221.743 221.746C260.855 182.634 310.687 155.998 364.937 145.207C419.187 134.416 475.419 139.954 526.521 161.122C577.624 182.289 621.302 218.134 652.032 264.125C682.762 310.116 699.164 364.187 699.164 419.5C699.164 493.672 669.699 564.806 617.252 617.254C564.804 669.702 493.67 699.166 419.498 699.166Z" fill="black"/>
                <path d="M559.331 384.542H454.456V279.667C454.456 270.395 450.773 261.503 444.217 254.947C437.661 248.391 428.769 244.708 419.497 244.708C410.226 244.708 401.334 248.391 394.778 254.947C388.222 261.503 384.539 270.395 384.539 279.667V419.5C384.539 428.771 388.222 437.663 394.778 444.219C401.334 450.775 410.226 454.458 419.497 454.458H559.331C568.602 454.458 577.494 450.775 584.05 444.219C590.606 437.663 594.289 428.771 594.289 419.5C594.289 410.228 590.606 401.337 584.05 394.781C577.494 388.225 568.602 384.542 559.331 384.542Z" fill="black"/>
            </svg>&nbsp;상영시간`;
                const timeDiv = document.createElement("div");
                timeDiv.classList.add("timeDiv");
                for(let i=0;i<timeList.length;i++){
                    const div = document.createElement("div");
                    const span = document.createElement("span");
                    span.innerText = timeList[i];
                    const button = document.createElement("button");
                    button.classList.add("deleteBtn");
                    button.setAttribute("onclick", "deleteTime(this)");
                    button.innerText = 'x';
                    div.append(span, button);
                    timeDiv.append(div);
                }
                parentDiv.append(div, timeDiv);
                e.target.after(parentDiv);
            })
        } else {
            timeWrap.remove();
        }
    })
}

function deleteTime(e){
    if(confirm("정말 삭제하시겠습니까?")){
    
        const movieNo = e.parentNode.parentNode.parentNode.previousElementSibling.getAttribute("movieNo");
        const movieDate = e.parentNode.parentNode.parentNode.previousElementSibling.parentNode.nextElementSibling.nextElementSibling.innerText;
        const movieTheater = e.parentNode.parentNode.parentNode.previousElementSibling.parentNode.previousElementSibling.innerText;
        const movieTime = movieDate + e.previousElementSibling.innerText;
    
        const params = new URL(location.href).searchParams;

        const date = params.get("date");  // 날짜
        const theater = params.get("movieTheater");
        const selectOpt = params.get("selectOpt");  
        const content = params.get("content");  
        const cp = params.get("cp");

        console.log(movieNo);
        console.log(movieTheater);
        console.log(movieTime);
        let f = document.createElement('form');

        let obj;
        obj = document.createElement('input');
        obj.setAttribute('type', 'hidden');
        obj.setAttribute('name', 'movieNo');
        obj.setAttribute('value', movieNo);

        let obj1;
        obj1 = document.createElement('input');
        obj1.setAttribute('type', 'hidden');
        obj1.setAttribute('name', "movieTheater");
        obj1.setAttribute('value', movieTheater);

        let obj2;
        obj2 = document.createElement('input');
        obj2.setAttribute('type', 'hidden');
        obj2.setAttribute('name', 'movieTime');
        obj2.setAttribute('value', movieTime);
        
        let obj3;
        obj3 = document.createElement('input');
        obj3.setAttribute('type', 'hidden');
        obj3.setAttribute('name', 'cp');
        obj3.setAttribute('value', cp);

        let obj4;
        obj4 = document.createElement('input');
        obj4.setAttribute('type', 'hidden');
        obj4.setAttribute('name', 'selectOpt');
        obj4.setAttribute('value', selectOpt);

        let obj5;
        obj5 = document.createElement('input');
        obj5.setAttribute('type', 'hidden');
        obj5.setAttribute('name', 'content');
        obj5.setAttribute('value', content);

        let obj6;
        obj6 = document.createElement('input');
        obj6.setAttribute('type', 'hidden');
        obj6.setAttribute('name', 'date');
        obj6.setAttribute('value', date);

        let obj7;
        obj7 = document.createElement('input');
        obj7.setAttribute('type', 'hidden');
        obj7.setAttribute('name', 'theater');
        obj7.setAttribute('value', theater);

        f.append(obj, obj1, obj2, obj3, obj4, obj5, obj6, obj7);
        f.setAttribute('method', 'post');
        f.setAttribute('action', '/adminCinemaDeleteTime');
        document.body.appendChild(f);
        f.submit();
    }
}

const registBtn = document.getElementById("regist");
registBtn.addEventListener("click", () => {
    location.href="/adminCinemaRegister";
})

function cinemaSelectAll(cinemaSelectAll) { //온클릭 
    const checkboxes
      = document.querySelectorAll('input[type="checkbox"]'); //체크박스 전체 불러오기

    checkboxes.forEach((checkbox) => { //체크박스 전체 선택
      checkbox.checked = cinemaSelectAll.checked //체크박스누르면 
    })
}

const deleteBtn = document.getElementById("delete");
deleteBtn.addEventListener("click", () => {
    const checkList = document.querySelectorAll(".chkbx:checked");

    if(checkList.length == 0){
        return;
    }

    if(confirm("정말 삭제하시겠습니까?")){
        let dataList = [];
        for(let i=0;i<checkList.length;i++){
            let data = {}
            let movieTheater = checkList[i].parentNode.nextElementSibling.innerText;
            let movieNo = checkList[i].parentNode.nextElementSibling.nextElementSibling.children[0].getAttribute("movieNo");
            let movieTime = checkList[i].parentNode.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.innerText;
            data.movieTheater = movieTheater;
            data.movieNo = movieNo;
            data.movieTime = movieTime;
            dataList.push(data);
        }
        console.log(dataList);
        let formData = JSON.stringify(dataList);
        console.log(formData);
        let f = document.createElement('form');

        let obj;
        obj = document.createElement('input');
        obj.setAttribute('type', 'hidden');
        obj.setAttribute('name', 'formData');
        obj.setAttribute('value', formData);

        f.append(obj);
        f.setAttribute('method', 'post');
        f.setAttribute('action', '/adminCinemaDelete');
        document.body.appendChild(f);
        f.submit();
    }
})