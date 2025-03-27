//TRANSIZIONE NAVBAR
var nav=document.getElementById("navBar");


function showNav() {
    if (nav.style.visibility=="hidden") {
        nav.style.visibility="visible";
        nav.classList.remove("nav-hide");
        nav.classList.add("nav-show");
    } else {
        nav.style.visibility="hidden";
        nav.classList.add("nav-hide");
        nav.classList.remove("nav-show");
    }

}

//SHOW LOGIN POP-UP
var profileIcon=document.getElementById("profile-icon");
var login=document.getElementById("login-pop");
function showLogin() {
    if (login.style.visibility=="hidden") {
        login.style.visibility="visible";
    } else {
        login.style.visibility="hidden";
    }
}
profileIcon.addEventListener("click", showLogin);

//REPLACE LOGIN BUTTONS
var signUpButton=document.getElementById("signup-b");
var loginButton=document.getElementById("login-b");
var loginButtonLbl=document.getElementById("login-b-lb");
document.addEventListener("DOMContentLoaded", 
    function replaceLoginBut() {
        if (document.getElementById("session").value!="") {
            loginButton.innerHTML="Log Out";
            signUpButton.style.visibility="hidden";
        }
    }
);

//ADVANCED RESEARCH
var advSrcButton=document.getElementById("adv-src-b");
var advForm=document.getElementById("adv-search");
function showAdvSearch() {
    if (advForm.style.visibility=="hidden") {
        advForm.style.visibility="visible";
    } else {
        advForm.style.visibility="hidden";
    }
}
advSrcButton.addEventListener("click", showAdvSearch);



