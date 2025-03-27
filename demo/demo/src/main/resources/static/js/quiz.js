function modificaQuiz(oggetto) {
    var form = document.getElementById("form-modifica");

    form.hidden = false;

    var id = document.getElementById("modifica-id");
    var domanda = document.getElementById("modifica-domanda");
    var risposta = document.getElementById("modifica-risposta");
    var genere = document.getElementById("modifica-genere");
    
    id.value = oggetto.getAttribute("data-modifica-id");
    domanda.value = oggetto.getAttribute("data-modifica-domanda");
    risposta.value = oggetto.getAttribute("data-modifica-risposta");
    genere.value = oggetto.getAttribute("data-modifica-genere");
    
}