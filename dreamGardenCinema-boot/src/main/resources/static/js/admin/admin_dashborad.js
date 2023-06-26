var context = document
    .getElementById('myChart')
    .getContext("2d");
var myChart = new Chart(context, {
    type: 'line', // 차트의 형태
    data: { // 차트에 들어갈 데이터
        labels: [
            //x 축
            '월', '화', '수', '목', '금', '토', '일'
        ],
        datasets: [
            { //데이터
                label: '요일별 매출', //차트 제목
                fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
                data: [
                    21, 19, 25, 20, 23, 40, 25 //x축 label에 대응되는 데이터 값
                ],
                backgroundColor: [
                    //색상
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                ],
                borderColor: [
                    //경계선 색상
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1 //경계선 굵기
            }
        ]
    },
    options: {
        scales: {
            yAxes: [
                {
                    ticks: {
                        beginAtZero: true
                    }
                }
            ]
        }
    }
});

/* 비동기) option에서 선택한 영화의 매출 불러오기 */
function getTicketAmount(selectElement) {
    // 영화 선택값 가져오기
    var selectedMovieNo = selectElement.value;
    // 선택한 영화를 표시할 요소 가져오기
    var selectedMovieElement = document.getElementById("selectedMovie");
    // 매출을 표시할 요소 가져오기
    var movieAmount = document.getElementById("ticketAmount");

    // 요소가 없을 경우 오류 처리
    if (!selectedMovieElement || !movieAmount) {
        console.log("DOM element not found.");
        return;
    }
    // 선택한 영화 번호를 input 요소에 할당
    selectedMovieElement.value = selectedMovieNo;

    // AJAX 요청 보내기
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/ticketAmount?movieNo=" + selectedMovieNo, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // 응답 데이터 파싱
                var response = JSON.parse(xhr.responseText);
                // 매출을 표시할 요소에 데이터 적용
                movieAmount.innerHTML = response.ticketAmount.toLocaleString()
            } else {
                console.log("AJAX Error:", xhr.status);
            }
        }
    };
    xhr.send();
}

