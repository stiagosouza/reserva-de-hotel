import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Classe de subsistema para gerenciar quartos
class QuartoService {
    private static final Map<Integer, String> quartosDisponiveis = new HashMap<>();

    static {
        quartosDisponiveis.put(101, "disponível");
        quartosDisponiveis.put(102, "disponível");
        quartosDisponiveis.put(103, "disponível");
    }

    public static boolean verificarDisponibilidade(int numeroQuarto) {
        String status = quartosDisponiveis.get(numeroQuarto);
        return status != null && status.equals("disponível");
    }

    public static void marcarComoOcupado(int numeroQuarto) {
        quartosDisponiveis.put(numeroQuarto, "ocupado");
    }

    public static void marcarComoDisponivel(int numeroQuarto) {
        quartosDisponiveis.put(numeroQuarto, "disponível");
    }
}

// Classe de subsistema para gerenciar hóspedes
class HospedeService {
    public void registrarHospede(String nomeHospede) {
        // Lógica para registrar um hóspede
        System.out.println("Registrando hóspede: " + nomeHospede);
    }
}

// Classe de subsistema para gerenciar pagamento
class PagamentoService {
    public void realizarPagamento(int numeroQuarto, double valor) {
        // Lógica para realizar o pagamento
        System.out.println("Realizando pagamento do quarto " + numeroQuarto + " no valor de R$" + valor);
    }
}

// Classe ReservaFacade que atua como a fachada
class ReservaFacade {
    private QuartoService quartoService;
    private HospedeService hospedeService;
    private PagamentoService pagamentoService;

    public ReservaFacade() {
        quartoService = new QuartoService();
        hospedeService = new HospedeService();
        pagamentoService = new PagamentoService();
    }

    public void fazerReservaQuarto(int numeroQuarto, String dataEntrada, String dataSaida, String nomeHospede) {
        boolean disponivel = QuartoService.verificarDisponibilidade(numeroQuarto);


        if (disponivel) {
            hospedeService.registrarHospede(nomeHospede);
            System.out.println("Reservando quarto " + numeroQuarto + " para " + nomeHospede);
            QuartoService.marcarComoOcupado(numeroQuarto);

        } else {
            System.out.println("Quarto " + numeroQuarto + " não está disponível.");
        }
    }

    public void cancelarReserva(int numeroQuarto) {
        System.out.println("Cancelando reserva do quarto " + numeroQuarto);
        QuartoService.marcarComoDisponivel(numeroQuarto);
    }

    public void realizarPagamento(int numeroQuarto, double valor) {
        pagamentoService.realizarPagamento(numeroQuarto, valor);
    }

    public boolean verificarDisponibilidade(int numeroQuarto) {
        return quartoService.verificarDisponibilidade(numeroQuarto);
    }
}

public class HotelReservationExample {
    public static void main(String[] args) {
        ReservaFacade reservaFacade = new ReservaFacade();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Fazer reserva de quarto");
            System.out.println("2. Cancelar reserva de quarto");
            System.out.println("3. Realizar pagamento");
            System.out.println("4. Verificar disponibilidade de quarto");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Informe o número do quarto: ");
                    int numeroQuarto = scanner.nextInt();

                    System.out.print("Informe a data de entrada: ");
                    String dataEntrada = scanner.next();

                    System.out.print("Informe a data de saída: ");
                    String dataSaida = scanner.next();

                    scanner.nextLine();

                    System.out.print("Informe o nome do hóspede: ");
                    String nomeHospede = scanner.nextLine();

                    reservaFacade.fazerReservaQuarto(numeroQuarto, dataEntrada, dataSaida, nomeHospede);
                    break;

                case 2:
                    System.out.print("Informe o número do quarto a ser cancelado: ");
                    int numeroQuartoCancelar = scanner.nextInt();

                    reservaFacade.cancelarReserva(numeroQuartoCancelar);
                    break;

                case 3:
                    System.out.print("Informe o número do quarto para pagamento: ");
                    int numeroQuartoPagamento = scanner.nextInt();

                    System.out.print("Informe o valor do pagamento: ");
                    double valorPagamento = scanner.nextDouble();

                    reservaFacade.realizarPagamento(numeroQuartoPagamento, valorPagamento);
                    break;

                case 4:
                    System.out.print("Informe o número do quarto para verificar disponibilidade: ");
                    int numeroQuartoDisponibilidade = scanner.nextInt();

                    boolean disponivel = reservaFacade.verificarDisponibilidade(numeroQuartoDisponibilidade);
                    System.out.println("Quarto " + numeroQuartoDisponibilidade + " está disponível: " + disponivel);
                    break;

                case 0:
                    System.out.println("Saindo do programa.");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }
}
