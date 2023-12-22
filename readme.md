Ejercicios UT4. UDP Parte I


1. Duplica el ejemplo de Emisor/Receptor de udp (ejemplos.sockets.udp.ejemplo1), y haz
   que el receptor devuelva la línea recibida por el cliente, de vuelta al cliente, precedido
   por una cadena “ECHO “. Por ejemplo, si el emisor manda “Hola, soy Diego”, el receptor
   deberá devolverle “ECHO Hola, soy Diego”.
   a. Desde el receptor, para obtener el puerto y dirección del emisor, deberás
   cogerla del datagrama recibido. De esta manera:


   //RECIBIMOS EL MENSAJE
   byte[] mensaje = new byte[100];
   DatagramPacket datagrama1 = new DatagramPacket(mensaje, 100);
   datgramSocket.receive(datagrama1);

   InetAddress direccionCliente = datagrama1.getAddress();
   int puertoCliente = datagrama1.getPort();
   i.


   b. El emisor deberá escuchar lo que le mande el receptor, e imprimirlo por
   pantalla.


2. Duplica el ejercicio anterior, y ahora haz que el receptor reciba múltiples mensajes. En
   cada mensaje que recibe, contestará lo mismo que en el anterior ejercicio. Puedes hacer
   un bucle que esté esperando mensajes y contestando un número de veces determinado.
   Ahora queremos crear muchos hilos de cliente y que manden mensajes al receptor. De
   esta manera:


   a. El emisor ya no tendrá método main. Haz que el emisor tenga características de
   hilo, implementando Runnable, y que tenga 3 atributos privados
   (mensajeEnviar, numeroEmisor, y puertoDestino). El método run hará lo mismo
   que hacía antes pero utilizando los atributos. Quita las trazas del código y sólo
   deja una que saque por pantalla lo que recibe del receptor, mostrando su
   número de emisor y el mensaje recibido. Enviará al receptor la cadena
   concatenada por “numeroEmisor” y “mensajeEnviar”.


   b. Crea una clase “PruebaEmisores” que tendrá un método main. Se encargará de
   crear un número máximo de hilos de emisor, y a cada uno enviará un mensaje
   a enviar, el número de ese emisor, y el puerto de destino del receptor. A cada
   uno le pasará una cadena aleatoria a enviar al receptor que se podrá generar
   con este código:


   private static String generarAleatorio() {
   int leftLimit = 97; // letter 'a'
   int rightLimit = 122; // letter 'z'
   int targetStringLength = 10;
   Random random = new Random();
   return random.ints(leftLimit, rightLimit +
   1).limit(targetStringLength)
   .collect(StringBuilder::new,
   StringBuilder::appendCodePoint, StringBuilder::append).toString();
   i. }


   c. Puedes probar a generar 20 emisores, y que el servidor trate 20 mensajes
   máximo, y luego ir probando combinaciones.