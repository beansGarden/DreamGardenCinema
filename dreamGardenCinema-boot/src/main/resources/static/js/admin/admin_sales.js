

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
            if (true) {
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
for (let i = 1; i <= 12; i++) {
    let label = `${selectedYear - 1} ${i}월`;
    labels.push(label);
    let label2 = `${selectedYear} ${i}월`;
    labels.push(label2);
}
let monthlySalesByYearConfig = {
    type: 'bar',
    data: {
        labels: labels,
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
    const selectedYear = monthlySalesByYear.value;
    let YearAndMonthArrAjax = [];
    for (let i = 1; i <= 12; i++) {
        let label = `"${selectedYear - 1}-${i < 10 ? '0' + i : i}"`;
        YearAndMonthArrAjax.push(label);
        let label2 = `"${selectedYear}-${i < 10 ? '0' + i : i}"`;
        YearAndMonthArrAjax.push(label2);
    }
    fetch('/admin/monthlySalesByYear?selectedYear=' + selectedYear)
        .then(response => response.json())
        .then(result => {
            if (true) {
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