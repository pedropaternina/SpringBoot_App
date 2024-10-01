// Call the dataTables jQuery plugin
$(document).ready(function() {


});

async function registrarUsuarios(){
    let datos = {}
    datos.nombres = document.getElementById('txtNombre').value
    datos.apellidos = document.getElementById('txtApellido').value
    datos.telefono = document.getElementById('txtTelefono').value
    datos.email = document.getElementById('txtEmail').value
    datos.password = document.getElementById('txtContraseña').value

    let repetirPassword = document.getElementById('txtRepetirContraseña').value
    console.log(datos.nombres)
    console.log(datos.apellidos)
    if(datos.password != repetirPassword){
        alert('Las contraseñas no coinciden')
        return;
    }

    const url = "http://localhost:8081/usuario";

    const response = await fetch(url, {
    method: 'POST',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
    });
    const json = await response.json();
   console.log(json);
   alert("La cuenta fue creada con exito!")
    window.location.href = 'login.html'

   }







