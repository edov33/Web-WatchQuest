function controllaPassword() {
    var pass = document.getElementById("password").value;
    var controllo = document.getElementById("confermapassword").value;

    if(pass !== controllo){
        alert("Ricontrolla le password, i due valori non coincidono");
        return false;
    }
    return true;
}


function modificaFilm(oggetto) {
    var form = document.getElementById("form-modifica");

    form.hidden = false;

    var id = document.getElementById("modifica-id");
    var titolo = document.getElementById("modifica-titolo");
    var descrizione = document.getElementById("modifica-descrizione");
    var genere = document.getElementById("modifica-genere");
    var anno_pubblicazione = document.getElementById("modifica-anno_pubblicazione");
    var classificazione = document.getElementById("modifica-classificazione");
    var rating = document.getElementById("modifica-rating");
    var cast = document.getElementById("modifica-cast");
    var regista = document.getElementById("modifica-regista");
    var lingua_originale = document.getElementById("modifica-lingua_originale");
    var durata = document.getElementById("modifica-durata");

    id.value = oggetto.getAttribute("data-modifica-id");
    titolo.value = oggetto.getAttribute("data-modifica-titolo");
    descrizione.value = oggetto.getAttribute("data-modifica-descrizione");
    genere.value = oggetto.getAttribute("data-modifica-genere");
    anno_pubblicazione.value = oggetto.getAttribute("data-modifica-anno_pubblicazione");
    classificazione.value = oggetto.getAttribute("data-modifica-classificazione");
    rating.value = oggetto.getAttribute("data-modifica-rating");
    cast.value = oggetto.getAttribute("data-modifica-cast");
    regista.value = oggetto.getAttribute("data-modifica-regista");
    lingua_originale.value = oggetto.getAttribute("data-modifica-lingua_originale");
    durata.value = oggetto.getAttribute("data-modifica-durata");

}


function modificaUtente(oggetto) {
    var form = document.getElementById("form-modifica");
    form.hidden = false;

    var id = document.getElementById("modifica-id");
    var nome = document.getElementById("modifica-nome");
    var cognome = document.getElementById("modifica-cognome");
    var data_nascita = document.getElementById("modifica-data_nascita");
    var username = document.getElementById("modifica-username");
    var password = document.getElementById("modifica-password");
    var email = document.getElementById("modifica-email");
    var lingua = document.getElementById("modifica-lingua");


    id.value = oggetto.getAttribute("data-modifica-id");
    nome.value = oggetto.getAttribute("data-modifica-nome");
    cognome.value = oggetto.getAttribute("data-modifica-cognome");
    data_nascita.value = oggetto.getAttribute("data-modifica-data_nascita");
    username.value = oggetto.getAttribute("data-modifica-username");
    password.value = oggetto.getAttribute("data-modifica-password");
    email.value = oggetto.getAttribute("data-modifica-email");
    lingua.value = oggetto.getAttribute("data-modifica-lingua");
    abbonamento.value = oggetto.getAttribute("data-modifica-abbonamento");

}

function modificaSerie(oggetto) {
    var form = document.getElementById("form-modifica");
    form.hidden = false;

    var id = document.getElementById("modifica-id");
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

    id.value = oggetto.getAttribute("data-modifica-id");
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