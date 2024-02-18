import motor3R.TresEnRaya;
import java.util.Scanner;

public class InterfaceConsola {

    protected TresEnRaya miObjetoTresEnRaya = new TresEnRaya();

    protected Scanner sc = new Scanner(System.in);

    private InterfaceConsola() {
        System.out.println("El tablero está colocado como un pad numérico, con las posiciones del 1 al 9");
        printTablero();
        System.out.print("Seleccione dificultad: ");
        miObjetoTresEnRaya.setCpuLevel(Integer.parseInt(sc.next().trim()));
        jugar();
    }

    public static void main(String[] args) {new InterfaceConsola();}

    private void jugar(){
        while (true){
            System.out.println("Turno del jugador\nEscribe tu posición: ");
            turnoJugador();
            printTablero();
            if (miObjetoTresEnRaya.checkFinPartida()) break;
            System.out.println("Turno de la CPU:");
            miObjetoTresEnRaya.turnoCPU();
            printTablero();
            if (miObjetoTresEnRaya.checkFinPartida()) break;
        }
        System.out.println("Partida finalizada, ganador: " + miObjetoTresEnRaya.getGanador());
    }

    private void turnoJugador(){
        int pos = Integer.parseInt(sc.next().trim());
        while(!miObjetoTresEnRaya.posicionar(pos, '0')){
            System.out.println("Posición no válida\nEscribe tu nueva posición: ");
            pos = Integer.parseInt(sc.next().trim());
        }
    }

    private void printTablero(){
        System.out.println((miObjetoTresEnRaya.getTablero()[2]));
        System.out.println((miObjetoTresEnRaya.getTablero()[1]));
        System.out.println((miObjetoTresEnRaya.getTablero()[0]));
    }
}