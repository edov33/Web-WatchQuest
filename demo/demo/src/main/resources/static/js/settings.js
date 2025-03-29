var adminSettings=document.getElementById("admin-sez");
document.addEventListener("DOMContentLoaded", 
    function replaceLoginBut() {
        if (document.getElementById("session").value!="admin") {
            adminSettings.style.visibility="hidden";
        }
    }
);