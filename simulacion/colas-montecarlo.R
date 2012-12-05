simQueue <- function(tiempo.entre.llegadas, duracion.servicio, services.n = 1){  

# tiempo de llegadas de los cientes en el momento en que se abre la caja t0 = 0
hora.llegada <- cumsum(tiempo.entre.llegadas)
hora.salida <- cumsum(duracion.servicio)

# la duracion de servicio de services.n es igual a una caja services.n veces mas
# rapido
duracion.servicio <- duracion.servicio/services.n

tiempo.espera <- 1:queue.n
duracion.total<- 1:queue.n
usuarios.cola <- 1:queue.n
hora.atencion <- 1:queue.n

# se pueden calcular los tiempos de espera de las personas en la cola
for(i in 1:queue.n){
  if(i == 1){        #el tiempo de atencion es igual que el de llegada
    hora.atencion[i] <- hora.llegada[i]
    tiempo.espera[i] <- 0
    usuarios.cola[i] <- 0
  }
  else{
    hora.atencion[i] <- hora.atencion[i - 1] + duracion.servicio[i - 1] + 0.01
    tiempo.espera[i] <- hora.llegada[i] - hora.atencion[i]
    usuarios.cola[i] <- sum((hora.llegada[i] <=  hora.salida[1:i]))
  }  
  hora.salida[i] <- hora.atencion[i] + duracion.servicio[i]
  duracion.total[i] <- hora.salida[i] - hora.llegada[i]
}
  usuarios.cola
}