// Call the dataTables jQuery plugin
$(document).ready(function() {


});




async function iniciarSesion(){
    let datos = {}
    datos.email = document.getElementById('txtEmail').value
    datos.password = document.getElementById('txtContraseña').value

    const url = "http://localhost:8081/usuario/login";

    const response = await fetch(url, {
    method: 'POST',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
    });
    const json = await response.text();

    if(json != 'Error'){
    localStorage.token = json;
    window.location.href = 'usuarios.html'
    }else{
    alert("Credenciales invalidas")
    }

   }







