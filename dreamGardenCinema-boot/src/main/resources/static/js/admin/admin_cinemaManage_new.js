const selectCont = document.querySelector("#selectCont");
const searchKey = document.querySelector("#searchKey");
const content = document.querySelector("#content");
const opt = document.querySelectorAll("#opt > option");
const searchDate = document.querySelector("#date");
(()=>{
    const params = new URL(location.href).searchParams;

    const key = params.get("selectOpt");  // t, c, tc, w 중 하나
    const query = params.get("content");  // 검색어
    const date = params.get("date");  // 날짜
    if(key != null){
        // 검색을 했을 때
        content.value = query;  // 검색어를 화면에 출력

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

    if(content.value.trim().length == 0){  // 검색어 미입력 시
        e.preventDefault();  // form 기본 이벤트 제거

        location.href = location.pathname;  // 해당 게시판 1페이지로 이동

        // location.pathname : 쿼리스트링을 제외한 실제 주소
    }

});

const movieTt = document.querySelectorAll("#movieTt");

for(let i=0;i<movieTt.length;i++){
    movieTt[i].addEventListener("click", e=>{
        console.log(e.target);
        console.log(e.target.parentNode.nextElementSibling);
        console.log(e.target.parentNode.previousElementSibling);
    })
}