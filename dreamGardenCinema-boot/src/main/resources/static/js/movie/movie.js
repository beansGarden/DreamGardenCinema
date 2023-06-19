
let currentPage = 0;

/* movie type ->  current, promise */
var type = "";

document.onload = checkType();

/* type check */
function checkType() {
    
    if(location.pathname == "/movie/promise"){
        type = "current";
    }else{
        type = "promise";
    }

    return type;
}   

/*  */


