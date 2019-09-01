# ManejoTransacciones---Java
Manejo de Transacciones con JDBC

- Transacción: Conjunto de instrucciones SQL agrupadas en bloques de ejecución
- Rollback: Al ejecutar una sentencia que provoca un error, podemos hacer un 
rollbackde toda la transacción y por lo tanto no afectaremos a el estado de la base de datos.
- Commit: Si todo es correcto guardamos los cambios haciendo commit de todo el bloque ejecutado.

Por default JDBC utiliza la instrucción autocommit=true, pero también podemos
utilizar el método con.setAutoCommit(false) para poder modificar el estado de la
bandera del autocommit.
