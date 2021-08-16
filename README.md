# Lista espera restorant
## Listar las reservas activas
Metodo: Get  
Url: /reservas 
## Crear reserva y crea usuario
Metodo: POST  
Url: /registro  
 
Data send format: x-www-form-urlencoded  
nombre:miNombre  
apellido:miApellido  
email:usuario@usuario  
numTelefono:+56987654321  
numeroPersonas:3
## Crear reserva usuarios ya registrados (email)
Metodo: POST  
Url: /reserva/crear/usuario/
 
Data send format: x-www-form-urlencoded  
email:usuario@usuario  
numeroPersonas:3
## Crear reserva usuarios ya registrados (Id)
Metodo: POST  
Url: /nuevaReserva
 
Data send format: x-www-form-urlencoded  
usuarioId:1  
numeroPersonas:3
## Deshabilitar una reserva existente.
Metodo: Get  
Url: /reserva/deshabilitar/{idReseva}  
## Habilitar una reserva existente.
Metodo: Get  
Url: /reserva/habilitar/{idReseva}  
## Crear un usuario (no crea reserva)
Metodo: Post  
Url: /user/crear

Data send format: x-www-form-urlencoded  
nombre:miNombre  
apellido:miApellido 
email:usuario@usuario  
numTelefono:+56987654321