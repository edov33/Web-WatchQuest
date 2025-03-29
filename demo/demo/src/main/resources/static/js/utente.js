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
