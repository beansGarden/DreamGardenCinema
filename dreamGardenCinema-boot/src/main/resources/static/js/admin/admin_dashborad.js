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
                data: salesByPeriod,
                backgroundColor: '#aaaaaa',
                borderColor: '#444444',
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


// 년별 분기 매출
// 차트
const ctx = document.getElementById('SalesforQuarterChart');
let resultQuarterlySales = [];
if(resultQuarterlySales.length < 1){
    resultQuarterlySales = quarterlySales
}
var config = {
    type: 'bar',
    data: {
        labels: ['1분기', '2분기', '3분기', '4분기'],
        datasets: [{
            label: '매출',
            data: resultQuarterlySales,
            borderWidth: 1,
            backgroundColor: ['#444444', '#aaaaaa']
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
};
var myChart = new Chart(ctx, config);

// 년도
const currentYear = new Date().getFullYear();
for (let i = currentYear - 10; i <= currentYear; i++) {
    const option = document.createElement("option");
    option.value = i;
    option.text = i;

    if (i === currentYear) {
        option.selected = true;
    }

    document.getElementById("yearSelect").appendChild(option);
}

// 매출 조회
function salesForQuarter() {
    const yearSelect = document.getElementById("yearSelect");
    const selectedYear = yearSelect.value;

    fetch('/admin/quarterlySales?selectedYear=' + selectedYear)
        .then(response => response.json())
        .then(result => {
            if (true) {
                resultQuarterlySales.length = 0;
                for(let i = 0; i < result.length; i++){
                    resultQuarterlySales.push(JSON.stringify(result[i].quarterlySales))
                }
                myChart.update();
            } 
        })
        .catch(err => console.log(err));
}