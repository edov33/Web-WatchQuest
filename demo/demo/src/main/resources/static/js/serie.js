function ricerca() {
    var form = document.getElementById("search-form");
    if (form.style.display === "none" || form.style.display === "") {
        form.style.display = "block";
    } else {
        form.style.display = "none";
    }
}

function modificaSerie(oggetto) {
    var form = document.getElementById("form-modifica");
    form.hidden = false;//Il modulo di modifica (form-modifica) è inizialmente nascosto (hidden), e questa riga lo rende visibile.
    //quando quindi si clicca sul bottone "Modifica" 
    //si attiva l'evento onclick="modificaStudente(this) ovvero viene eseguita questa funzione
    //che attiva la visualizzazione della form
    var id = document.getElementById("modifica-id");
    //seleziono gli elementi HTML del modulo di modifica e li assegno a 
    //delle variabili per poterci lavorare.
    //La funzione document.getElementById() viene usata per cercare un elemento HTML che ha l'id specificato.
    //In questo caso, cerca un input con id="modifica-id"(riga 68 file html), che corrisponde 
    //all'input dove verrà inserito l'ID dello studente da modificare.
    //Il riferimento a questo input viene poi assegnato alla variabile id.
    var titolo = document.getElementById("modifica-titolo");
    var descrizione = document.getElementById("modifica-descrizione");
    var genere = document.getElementById("modifica-genere");
    var anno_pubblicazione = document.getElementById("modifica-anno_pubblicazione");
    var classificazione = document.getElementById("modifica-classificazione");
    var rating = document.getElementById("modifica-rating");
    var cast = document.getElementById("modifica-cast");
    var regista = document.getElementById("modifica-regista");
    var lingua_originale = document.getElementById("modifica-lingua_originale");
    var episodi = document.getElementById("modifica-episodi");
    var stagioni = document.getElementById("modifica-stagioni");

    //L'oggetto oggetto rappresenta il pulsante "Modifica" su cui si è cliccato.
    //Gli attributi data-modifica-* contengono i valori dello studente selezionato 
    //e vengono assegnati agli input corrispondenti del modulo.
    id.value = oggetto.getAttribute("data-modifica-id");
    //Il valore dell'id dello studente viene preso dal pulsante cliccato(valore che si trova in data-modifica-id)
    //e messo nel campo modifica-id della form di modifica(riga 76)
    //così, quando si apre il modulo di modifica, i campi sono già riempiti con i dati dello studente selezionato, pronti per essere aggiornati.
    titolo.value = oggetto.getAttribute("data-modifica-titolo");
    descrizione.value = oggetto.getAttribute("data-modifica-descrizione");
    genere.value = oggetto.getAttribute("data-modifica-genere");
    anno_pubblicazione.value = oggetto.getAttribute("data-modifica-anno_pubblicazione");
    classificazione.value = oggetto.getAttribute("data-modifica-classificazione");
    rating.value = oggetto.getAttribute("data-modifica-rating");
    cast.value = oggetto.getAttribute("data-modifica-cast");
    regista.value = oggetto.getAttribute("data-modifica-regista");
    lingua_originale.value = oggetto.getAttribute("data-modifica-lingua_originale");
    episodi.value = oggetto.getAttribute("data-modifica-episodi");
    stagioni.value = oggetto.getAttribute("data-modifica-stagioni");
}