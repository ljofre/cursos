# problema 1
#pruebas simulacion de montecarlo

#ejercicio 1
queue.n <- 20
# simulacion cola dada una distribucion de llegada y atencion
t.llegada <- c(0.4, 0.6, 0.8, 1.0, 1.2)
t.lleg.dist <- c(0.15, 0.30, 0.25, 0.20, 0.10)
t.serv <- c(1.5, 2.4, 3.5, 4.2, 5.0)
t.serv.dist <- c(0.10, 0.25, 0.30, 0.25, 0.10)

#en el presente framework se intenta generalizar el concepto de cola para cualquier
# ley de probabilidad de tiempo entre llegadas y atencion, por lo que esos datos
# deben de ser parametros de entrada

#muestra tiempo entre llegada
tiempo.entre.llegadas <- sample(t.llegada,1000,prob = t.lleg.dist,replace = TRUE)
#muestra de duracion del servicio
duracion.servicio <- sample(t.serv,1000,prob = t.serv.dist, replace = TRUE)
sim<-list()
#simulacion para cada uno de los casos planteados

#una caja de atencion
tiempo.entre.llegadas <- sample(t.llegada, queue.n, prob = t.lleg.dist,replace = TRUE)
simQueue(tiempo.entre.llegadas, duracion.servicio, services.n = 1)

#dos cajas de atencion
tiempo.entre.llegadas <- sample(t.llegada, queue.n, prob = t.lleg.dist,replace = TRUE)
simQueue(tiempo.entre.llegadas, duracion.servicio, services.n = 2)

#tres cajas de atencion
tiempo.entre.llegadas <- sample(t.llegada, queue.n, prob = t.lleg.dist,replace = TRUE)
simQueue(tiempo.entre.llegadas, duracion.servicio, services.n = 3)