var quizButts=document.getElementsByClassName("card-butt")
var quizCard=document.getElementById("domanda");

function showQuiz() {
    quizCard.style.visibility="visible";
}

for (let i = 0; i < quizButts.length; i++) {
    var butt = quizButts[i];
    butt.addEventListener("click", showQuiz);
}

var quizzes=document.getElementsByClassName("quiz-butt");
for (let i = 0; i < quizzes.length; i++) {
    var button = quizzes[i];
    button.addEventListener("click", ()=>{
        quizCard.style.visibility="hidden";
    })
}