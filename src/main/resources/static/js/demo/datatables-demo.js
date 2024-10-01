// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#dataTable').DataTable();
});

function getHeaders(){

        return{
         'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        }

}


async function cargarUsuarios(){
  const url = "http://localhost:8081/usuario";

    const response = await fetch(url, {
    method: 'GET',
    headers: getHeaders()
    });
    const json = await response.json();
   console.log(json);

   let listadoUsuarios = '';

   for(let usuarios of json){
      let botonElminar = '<a href="#" onclick="eliminarUsuario('+usuarios.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>'

       let info = '<tr> <td>'+ usuarios.id+ '</td> <td>'+ usuarios.nombres + ' ' + usuarios.apellidos + '</td><td>'+usuarios.email+'</td> <td>'+usuarios.telefono+'</td><td>' + botonElminar+' </td> </tr>';

    listadoUsuarios += info;
   }

    document.querySelector('#dataTable tbody').outerHTML = listadoUsuarios;



}

 async function eliminarUsuario(id){

        if(!confirm('¿Desea eliminar este usuario?')) {
            return;


        }

      const url = "http://localhost:8081/usuario/delete/" + id;

        const response = await fetch(url, {
        method: 'DELETE',
        headers: getHeaders()
        });

       console.log(response);

       location.reload();
}