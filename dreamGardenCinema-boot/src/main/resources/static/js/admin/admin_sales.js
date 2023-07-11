

// 년도 선택
function createYearOptions(quarterYearSelect) {
    const currentYear = new Date().getFullYear();
    for (let i = currentYear - 10; i <= currentYear; i++) {
        const option = document.createElement("option");
        option.value = i;
        option.text = i;

        if (i === currentYear) {
            option.selected = true;
        }

        quarterYearSelect.appendChild(option);
    }
}
const QuarterYearSelect = document.getElementById("QuarterYearSelect");
const monthlySalesByYear = document.getElementById("monthlySalesByYear");
createYearOptions(QuarterYearSelect);
createYearOptions(monthlySalesByYear);


// 지난주 요일별 매출
let salesForLastWeekContext = document
    .getElementById('salesForLastWeek')
    .getContext("2d");
console.log(salesByPeriod);
let salesForLastWeekChart = new Chart(salesForLastWeekContext, {
    type: 'line',
    data: {
        labels: ['월', '화', '수', '목', '금', '토', '일'],
        datasets: [
            {
                label: '요일별 매출',
                fill: false,
                data: salesByPeriod,
                backgroundColor: '#aaaaaa',
                borderColor: '#444444',
                borderWidth: 1
            }
        ]
    },
    options: {
        scales: {
            y: {
                beginAtZero: false
            }
        }
    }
});

// 년별 분기 매출
const salesForQuarterChartCtx = document.getElementById('salesForQuarterChart'); // chart id
let resultQuarterlySales = [];
if (resultQuarterlySales.length < 1) {
    resultQuarterlySales = quarterlySales
}
let salesForQuarterChartConfig = {
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
let salesForQuarterChart = new Chart(salesForQuarterChartCtx, salesForQuarterChartConfig);

// 분기 매출 조회
function salesForQuarter() {
    const QuarterYearSelect = document.getElementById("QuarterYearSelect");
    const selectedYear = QuarterYearSelect.value;

    fetch('/admin/quarterlySales?selectedYear=' + selectedYear)
        .then(response => response.json())
        .then(result => {
            if (result != null) {
                resultQuarterlySales.length = 0;
                for (let i = 0; i < result.length; i++) {
                    resultQuarterlySales.push(JSON.stringify(result[i].quarterlySales))
                }
                salesForQuarterChart.update();
            }
        })
        .catch(err => console.log(err));
}


// 년별 월 매출
const monthlySalesByYearCtx = document.getElementById('monthlySalesByYearChart'); // chart id
let selectedYear = monthlySalesByYear.value;
let labels = [];

let YearAndMonthArrLabels = [];
for (let i = 1; i <= 12; i++) {
    let label = `${selectedYear - 1} ${i}월`;
    labels.push(label);
    let label2 = `${selectedYear} ${i}월`;
    labels.push(label2);
}
if (YearAndMonthArrLabels.length < 1) {
    YearAndMonthArrLabels = labels
}
let monthlySalesByYearConfig = {
    type: 'bar',
    data: {
        labels: YearAndMonthArrLabels,
        datasets: [{
            label: ['매출'],
            data: monthlySalesByYearArr,
            borderWidth: 1,
            backgroundColor: ['#aaaaaa', '#444444']
        }]
    },
    options: {
        layout: {},
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
};
let monthlySalesByYearChart = new Chart(monthlySalesByYearCtx, monthlySalesByYearConfig);

function monthlySalesByYearAjax() {
    let YearAndMonthArrAjax = [];
    const selectedYear = monthlySalesByYear.value;
    YearAndMonthArrLabels.length = 0;
    YearAndMonthArrAjax.length = 0;
    for (let i = 1; i <= 12; i++) {
        let label = `"${selectedYear - 1}-${i < 10 ? '0' + i : i}"`;
        YearAndMonthArrAjax.push(label);
        let label2 = `"${selectedYear}-${i < 10 ? '0' + i : i}"`;
        YearAndMonthArrAjax.push(label2);
        let label3 = `${selectedYear - 1} ${i < 10 ? '0' + i : i}월`;
        YearAndMonthArrLabels.push(label3);
        let label4 = `${selectedYear} ${i < 10 ? '0' + i : i}월`;
        YearAndMonthArrLabels.push(label4);
    }
    
    fetch('/admin/monthlySalesByYear?selectedYear=' + selectedYear)
        .then(response => response.json())
        .then(result => {
            if (result != null) {
                monthlySalesByYearArr.length = 0;
                monthlySalesByYearAndMonthArr.length = 0;
                for (let i = 0; i < result.length; i++) {
                    monthlySalesByYearArr.push(JSON.stringify(result[i].monthlySalesByYear))
                    monthlySalesByYearAndMonthArr.push(JSON.stringify(result[i].yearAndMonth))
                }
                addMissingDays(monthlySalesByYearAndMonthArr, monthlySalesByYearArr, YearAndMonthArrAjax)
                monthlySalesByYearChart.update();
            }
        })
        .catch(err => console.log(err));
}

const dt_fr_input = document.getElementById("dt_fr_input");
const dt_bk_input = document.getElementById("dt_bk_input");

let endDate;
function settingDate(id) {
    const currentDate = new Date();
    if (id == 'dt_1year') {
        const oneYearAgo = new Date(currentDate.getFullYear() - 1, currentDate.getMonth(), currentDate.getDate());
        endDate = oneYearAgo.toISOString().split('T')[0];
        dt_fr_input.value = endDate;
    }
    if (id == 'dt_1week') {
        const oneWeekAgo = new Date(currentDate.getTime() - (7 * 24 * 60 * 60 * 1000));
        endDate = oneWeekAgo.toISOString().split('T')[0];
        dt_fr_input.value = endDate;
    }
    if (id == 'dt_1month') {
        const oneMonthAgo = new Date(currentDate.getFullYear(), currentDate.getMonth() - 1, currentDate.getDate());
        endDate = oneMonthAgo.toISOString().split('T')[0];
        dt_fr_input.value = endDate;
    }
    if (id == 'dt_3month') {
        const threeMonthsAgo = new Date(currentDate.getFullYear(), currentDate.getMonth() - 3, currentDate.getDate());
        endDate = threeMonthsAgo.toISOString().split('T')[0];
        dt_fr_input.value = endDate;
    }

    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    dt_bk_input.value = year + "-" + month + "-" + day;
    console.log("endDate : " + endDate);
}



const ctx = document.getElementById('reservationsByMovieOnSelectedDateChart');
let reservationsByMovieOnSelectedDateChartConfig = {
    type: 'pie',
    data: {
        labels: resultMovieTitle,
        datasets: [{
            label: '예매 건수',
            data: resultMovieTicketCount,
            borderWidth: 1,
            backgroundColor: ['e7e3e3', '#c3c5c5', '#919a97', '#262a2d', '#778c86', '#b4c8bb']
        }]
    },
};
let reservationsByMovieOnSelectedDateChar = new Chart(ctx, reservationsByMovieOnSelectedDateChartConfig);

const totalTicketCountSpan = document.querySelector(".totalTicketCount");
totalTicketCountSpan.innerText = "총 예매 건수 : " + totalTicketCount + "건";

const dt_inquiry_submit = document.getElementById("dt_inquiry_submit");
const no_information = document.querySelector(".no_information");

dt_inquiry_submit.addEventListener("click", () => {

    fetch('/admin/reservationsByMovieDate?dt_fr_input=' + dt_fr_input.value + '&dt_bk_input=' + dt_bk_input.value)
        .then(response => response.json())
        .then(result => {
            if (result.length != 0) {
                no_information.classList.add("display-none");
                console.log(result);
                resultMovieTitle.length = 0;
                resultMovieTicketCount.length = 0;
                totalTicketCount = 0;
                for (let i = 0; i < result.length; i++) {
                    resultMovieTitle.push(JSON.stringify(result[i].movieTitle));
                    resultMovieTicketCount.push(JSON.stringify(result[i].ticketCount));
                    totalTicketCount += Number(JSON.stringify(result[i].ticketCount));
                    totalTicketCountSpan.innerText = "조회된 예매 건수 : " + totalTicketCount + "건";
                }
                reservationsByMovieOnSelectedDateChar.update();
            }else{
                resultMovieTitle.length = 0;
                resultMovieTicketCount.length = 0;
                totalTicketCount = 0;
                totalTicketCountSpan.innerText = "";
                no_information.classList.remove("display-none");
                reservationsByMovieOnSelectedDateChar.update();
            }
        })
        .catch(err => console.log(err));
});



