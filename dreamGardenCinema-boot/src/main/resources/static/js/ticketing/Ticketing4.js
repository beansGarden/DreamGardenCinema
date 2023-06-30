// let printDiv;
// let initBody;
// const ticketInfo = document.getElementById("ticketInfo");

// function printDiv(ticketInfo){

//     printDiv = document.all[ticketInfo];

//     window.onbeforeprint = beforePrint;
//     window.onafterprint = afterPrint;

//     window.print();
// }

// function beforePrint(){
//     initBody=document.body.innerHTML;
//     document.body.innerHTML = printDiv.innerHTML;
// }
// function afterPrint(){
//     document.body.innerHTML = initBody;
// }

let initBody = document.body;
let hiddenHeader = document.querySelector('header');
let hiddenFooter = document.querySelector('.dg-footer-box');
let nav = document.querySelector(".ticketingNav");
let choiceHeader = document.querySelector(".choiceHeader");
let hiddenBtn = document.querySelector(".btn");
window.onbeforeprint = () => {

    hiddenHeader.style.display = 'none';
    hiddenFooter.style.display = 'none';
    nav.style.display = 'none';
    choiceHeader.style.display = 'none';
    hiddenBtn.style.display = 'none';

    document.body = document.querySelector('.ticketBox');
}

window.onafterprint = () => {
    hiddenHeader.style.display = 'block';
    hiddenFooter.style.display = 'block';
    nav.style.display = 'block';
    choiceHeader.style.display = 'flex';
    hiddenBtn.style.display = 'flex';

    document.body = initBody;
}