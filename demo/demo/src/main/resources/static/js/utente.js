function ricerca() {
    var form = document.getElementById("search-form");
    if (form.style.display === "none" || form.style.display === "") {
        form.style.display = "block";
    } else {
        form.style.display = "none";
    }
}



function modificaUtente(oggetto) {
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
    var nome = document.getElementById("modifica-nome");
    var cognome = document.getElementById("modifica-cognome");
    var data_nascita = document.getElementById("modifica-data_nascita");
    var username = document.getElementById("modifica-username");
    var password = document.getElementById("modifica-password");
    var email = document.getElementById("modifica-email");
    var lingua = document.getElementById("modifica-lingua");
    var abbonamento = document.getElementById("modifica-abbonamento");
    // var abbonamento = button.getAttribute("data-modifica-abbonamento") === "true";

    //L'oggetto oggetto rappresenta il pulsante "Modifica" su cui si è cliccato.
    //Gli attributi data-modifica-* contengono i valori dello studente selezionato 
    //e vengono assegnati agli input corrispondenti del modulo.
    id.value = oggetto.getAttribute("data-modifica-id");
    //Il valore dell'id dello studente viene preso dal pulsante cliccato(valore che si trova in data-modifica-id)
    //e messo nel campo modifica-id della form di modifica(riga 76)
    //così, quando si apre il modulo di modifica, i campi sono già riempiti con i dati dello studente selezionato, pronti per essere aggiornati.
    nome.value = oggetto.getAttribute("data-modifica-nome");
    cognome.value = oggetto.getAttribute("data-modifica-cognome");
    data_nascita.value = oggetto.getAttribute("data-modifica-data_nascita");
    username.value = oggetto.getAttribute("data-modifica-username");
    password.value = oggetto.getAttribute("data-modifica-password");
    email.value = oggetto.getAttribute("data-modifica-email");
    lingua.value = oggetto.getAttribute("data-modifica-lingua");
    abbonamento.value = oggetto.getAttribute("data-modifica-abbonamento");
    
    
    //abbonamento = document.getElementById("modifica-abbonamento").checked;
}