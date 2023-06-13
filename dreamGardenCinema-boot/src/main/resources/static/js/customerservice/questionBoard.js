/* 글자수 세기 */
// 제목
const QATitleCount = document.getElementById('QATitleCount');
const customerQuestionTitle = document.getElementById('customerQuestionTitle');

// 내용
const QAContentsCount = document.getElementById('QAContentsCount');
const QADetailContents = document.getElementById('QADetailContents');

// 글자수 카운트(제목)
customerQuestionTitle.addEventListener("input", () => {
    
    const count = customerQuestionTitle.value.length;
    QATitleCount.innerHTML = count;

});

// 글자수 카운트(내용)
QADetailContents.addEventListener("input", () => {
    
    const count = QADetailContents.value.length;
    QAContentsCount.innerHTML = count;

});
