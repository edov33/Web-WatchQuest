//LIKE BUTTON FILL
var likeIcon=document.getElementById("like-icon");
function fillHeart() {
    if (likeIcon.classList.contains("bi-heart-fill")) {
        likeIcon.classList.replace("bi-heart-fill", "bi-heart"); 
    } else {
        likeIcon.classList.replace("bi-heart", "bi-heart-fill"); 
    }
}
//WIKI LINK
var wikiButton=document.getElementById("wiki-link");
function showWikiPage() {
    window.location.assign("https://en.wikipedia.org/wiki/Tenet_(film)");
}