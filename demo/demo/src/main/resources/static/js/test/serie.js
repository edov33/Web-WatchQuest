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