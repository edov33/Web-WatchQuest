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

var menuIcon=document.getElementById("menu-button");
