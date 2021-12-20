Realizzare un programma di rete (componente client + componente server) in grado di simulare il gioco della Bomba.  

Il server attiva la sessione innescando la bomba con un time-out a 100sec, passa poi il pacchetto al client.  
Il client attiva una funzione di riduzione rnd (da 1 a 20 sec) sul tempo residuo, sconta il tempo sulla bomba e la rispedisce al server.  
Il server esegue la stessa elaborazione svolta dal client al rigo precedente e rimanda al mittente la bomba.  

Perde chi rimane a 0 secondi residui con la bomba in mano.
