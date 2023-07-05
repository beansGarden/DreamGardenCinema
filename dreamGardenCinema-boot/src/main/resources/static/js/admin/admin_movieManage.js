
const screenWaitBtn = document.getElementById("screenWait");
const screenPromiseBtn = document.getElementById("screenPromise");
const screenCurrentBtn = document.getElementById("screenCurrent");
const screenDownBtn = document.getElementById("screenDown");
const highlightBtn  = document.getElementById("highlight");
const wholeBtn = document.getElementById("whole");

screenWaitBtn.addEventListener("click", () => {
    screenWaitBtn.className = "colorsubbutton";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "";
    highlightBtn.className = "";
    wholeBtn.className = "";
});

screenPromiseBtn.addEventListener("click", () => {
    screenWaitBtn.className = "";
    screenPromiseBtn.className = "colorsubbutton";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "";
    highlightBtn.className = "";
    wholeBtn.className = "";
});

screenCurrentBtn.addEventListener("click", () => {
    screenWaitBtn.className = "";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "colorsubbutton";
    screenDownBtn.className = "";
    highlightBtn.className = "";
    wholeBtn.className = "";
});

screenDownBtn.addEventListener("click", () => {
    screenWaitBtn.className = "";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "colorsubbutton";
    highlightBtn.className = "";
    wholeBtn.className = "";
});

highlightBtn.addEventListener("click", () => {
    screenWaitBtn.className = "";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "";
    highlightBtn.className = "colorsubbutton";
    wholeBtn.className = "";
});

wholeBtn.addEventListener("click", () => {
    screenWaitBtn.className = "";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "";
    highlightBtn.className = "";
    wholeBtn.className = "colorsubbutton";
});

  // Ajax 요청 함수
function ajaxRequest(url, method, successCallback) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            successCallback(xhr.responseText);
        }
    };
    xhr.send();
}
