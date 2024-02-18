package motor3R;

/**
 * Clase TresEnRaya que contiene la lógica detrás de una partida de tres en raya contra la CPU
 */
public class TresEnRaya {
    /**
     * Esta variable contiene nuestro tablero
     */
    private char[][] tablero;
    private java.lang.String ganador;

    private int cpuLevel;

    /**
     * Inicializa la partida
     */
    public TresEnRaya(){
        tablero = new char[][]{{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
        ganador = "None";
        cpuLevel = 1;
    }

    /**
     * Busca modificar nuestro tablero y es la función encargada de que no se realicen jugadas ilegales
     * @param posicion Es la posición dentro del tablero que ocupará (1-9)
     * @param valor Es el símbolo que rellenará el tablero: 'X' para la CPU y '0' para el jugador
     * @return Devuelve un true/false para, además de modificar el tablero,
     *         tener información de si el tablero ha sido modificado, o en su defecto, no ha sufrido modificaciones
     *         debido a una jugada ilegal o imposible
     */
    public boolean posicionar(int posicion, char valor){
        if(posicion < 1 || posicion > 9) return false; //Si la posición seleccionada no está dentro del rango no permitirá posicionar
        int[] pos = traducirPosicion(posicion);
        if (tablero[pos[0]][pos[1]] != '-') return false;
        else tablero[pos[0]][pos[1]] = valor;
        return true;
    }

    private int[] traducirPosicion(int posicion){
        //Podemos obtener la posición simplemente dividiendo y sacando el resto,
        // lo que nos evita tener que hacer una tabla con cada una de las posiciones
        int x = (posicion-1)/3;
        int y = (posicion-1)%3;
        return new int[]{x, y};
    }

    /**
     * Realiza comprobaciones para saber si hemos terminado la partida y modifica nuestro ganador
     * @return Devuelve true si la partida ha finalizado
     */
    public boolean checkFinPartida(){
        int casillasLibres = 0;
        for(int x = 0; x <3; x++){
            if(tablero[x][0] == tablero[x][1] && tablero[x][0] == tablero[x][2]){ //Comprobamos todas las líneas horizontales
                if(tablero[x][0] == '0') {ganador = "Jugador"; return true;}
                if(tablero[x][0] == 'X') {ganador = "CPU"; return true;}
            }
        }
        for(int y = 0; y <3; y++){
            if(tablero[0][y] == tablero[1][y] && tablero[0][y] == tablero[2][y]){//Comprobamos todas las líneas verticales
                if(tablero[0][y] == '0') {ganador = "Jugador"; return true;}
                if(tablero[0][y] == 'X') {ganador = "CPU"; return true;}
            }
        }
        if ((tablero[0][0] == tablero[1][1] && tablero[0][0] == tablero[2][2])
                ||(tablero[2][0] == tablero[1][1] && tablero[2][0] == tablero[0][2])){//Comprobamos todas las líneas digonales
            if(tablero[1][1] == '0') {ganador = "Jugador"; return true;}
            if(tablero[1][1] == 'X') {ganador = "CPU";return true;}
        }
        for (int x = 0; x <3; x++){ //Buscamos cuantas casillas tenemos libres para realizar jugadas
            for(int y = 0; y <3; y++){
                if (tablero[x][y] == '-') casillasLibres++;
            }
        }
        if (casillasLibres == 0){ganador = "Empate"; return true;} //Sin casillas libres ni tres en raya la partida finaliza en empate
        return false;
    }

    /**
     * La CPU reaiza su jugada basándose en la dificultad elegida
     */
    public void turnoCPU(){
        switch (cpuLevel){
            case 1: {cpuDificultadFacil(); break;}
            case 2: {cpuDificultadMedia();break;}
            case 3: {cpuDificultadDificil();break;}
        }
        //En cuanto a las dificultades, nuestra CPU no es una que intente ganar al juego,
        // sino una que evite perder a toda costa en su máxima dificultad.
        // Podrían implementarse jugadas predeterminadas para generar una CPU más complicada que
        // sea imposible de vencer (dado el funcionamiento del juego), pero la nuestra debería
        // resultar un reto más que aceptable
    }

    /**
     * Función que nos devolverá el ganador de la partida
     * @return String con el ganador ("CPU", "Jugador" o "Empate")
     */
    public String getGanador() {
        return ganador;
    }

    /**
     * Función que nos devolverá el tablero en su estado actual
     * @return Array del tablero en formato 3x3
     */
    public char[][] getTablero() {
        return tablero;
    }

    /**
     * Función que modifica la dificultad de la CPU
     * @param cpuLevel Dificultad de la CPU (1-3)
     */
    public void setCpuLevel(int cpuLevel) {
        if(cpuLevel < 1 || cpuLevel > 3) return;
        this.cpuLevel = cpuLevel;
    }

    private void cpuDificultadFacil() {
        int cpuPlay = (int) (Math.random() * 9 + 1);    //Utilizamos Math para generar una posición aleatoria entre 1 y 9
        while (!posicionar(cpuPlay, 'X')) {
            cpuPlay = (int) (Math.random() * 9 + 1);
        }
    }

    private void cpuDificultadMedia(){
        //Comprobamos si podemos ganar en diagonal
        if(tablero[0][0] == tablero[1][1] && tablero[0][0] == 'X')
            if(posicionar(9, 'X'))return;
        if(tablero[1][1] == tablero[2][2] && tablero[1][1] == 'X')
            if(posicionar(1, 'X'))return;
        if(tablero[2][0] == tablero[1][1] && tablero[2][0] == 'X')
            if(posicionar(3, 'X'))return;
        if(tablero[1][1] == tablero[0][2] && tablero[1][1] == 'X')
            if(posicionar(7, 'X'))return;
        if((tablero[2][0] == tablero[0][2] || tablero[0][0] == tablero[2][2]) && tablero[2][0] == 'X')
            if(posicionar(5, 'X'))return;

        //Comprobamos si podemos ganar en horizontal
        for(int x = 0; x <3; x++){
            if(tablero[x][0] == tablero[x][1] && tablero[x][0] == 'X'){
                if(posicionar(x*3+3, 'X')) return;
            }
            if (tablero[x][0] == tablero[x][2] && tablero[x][0] == 'X'){
                if(posicionar(x*3+2, 'X')) return;
            }
            if (tablero[x][1] == tablero[x][2] && tablero[x][1] == 'X'){
                if(posicionar(x*3+1, 'X')) return;
            }
        }
        //Finalmente, comprobamos si podemos ganar en vertical
        for(int y = 0; y <3; y++){
            if(tablero[0][y] == tablero[1][y] && tablero[0][y] == 'X'){
                if(posicionar(7+y, 'X')) return;
            }
            if (tablero[0][y] == tablero[2][y] && tablero[0][y] == 'X'){
                if(posicionar(4+y, 'X')) return;
            }
            if (tablero[1][y] == tablero[2][y] && tablero[1][y] == 'X'){
                if(posicionar(1+y, 'X')) return;
            }
        }
        cpuDificultadFacil(); //Si la CPU se encuentra con que no puede ganar en su jugada realizará una al azar
    }

    private void cpuDificultadDificil(){

        //Esta dificultad funciona igual a la media, salvo que busca si es el jugador quien puede ganar,
        // en cuyo caso tapará para evitarlo

        if(tablero[0][0] == tablero[1][1] && tablero[0][0] == '0')
            if(posicionar(9, 'X'))return;
        if(tablero[1][1] == tablero[2][2] && tablero[1][1] == '0')
            if(posicionar(1, 'X'))return;
        if(tablero[2][0] == tablero[1][1] && tablero[2][0] == '0')
            if(posicionar(3, 'X'))return;
        if(tablero[1][1] == tablero[0][2] && tablero[1][1] == '0')
            if(posicionar(7, 'X'))return;
        if((tablero[2][0] == tablero[0][2] || tablero[0][0] == tablero[2][2]) && tablero[2][0] == '0')
            if(posicionar(5, 'X'))return;

        for(int x = 0; x <3; x++){
            if(tablero[x][0] == tablero[x][1] && tablero[x][0] == '0'){
                if(posicionar(x*3+3, 'X')) return;
            }
            if (tablero[x][0] == tablero[x][2] && tablero[x][0] == '0'){
                if(posicionar(x*3+2, 'X')) return;
            }
            if (tablero[x][1] == tablero[x][2] && tablero[x][1] == '0'){
                if(posicionar(x*3+1, 'X')) return;
            }
        }
        for(int y = 0; y <3; y++){
            if(tablero[0][y] == tablero[1][y] && tablero[0][y] == '0'){
                if(posicionar(7+y, 'X')) return;
            }
            if (tablero[0][y] == tablero[2][y] && tablero[0][y] == '0'){
                if(posicionar(4+y, 'X')) return;
            }
            if (tablero[1][y] == tablero[2][y] && tablero[1][y] == '0'){
                if(posicionar(1+y, 'X')) return;
            }
        }
        cpuDificultadMedia(); //Si no puede tapar, la CPU tratará de ganar
    }
}
