import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pedido {
    private final String nombreCliente;
    private final LinkedList<Producto> productos;

    public Pedido(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.productos = new LinkedList<>();
    }

    public void agregarProducto(String nombreProducto, int cantidad) {
        productos.add(new Producto(nombreProducto, cantidad));
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public LinkedList<Producto> getProductos() {
        return productos;
    }

    @Override
    public String toString() {
        StringBuilder detalle = new StringBuilder("Cliente: " + nombreCliente + "\n");
        productos.forEach((producto) -> {
            detalle.append("  - ").append(producto.getNombre()).append(": ").append(producto.getCantidad()).append("\n");
        });
        return detalle.toString();
    }
}

class Producto {
    private final String nombre;
    private final int cantidad;

    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }
}

public class SistemaCafeteria {
    private static final Queue<Pedido> pedidos = new LinkedList<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;
            
            do {
                System.out.println("Menú de Gestión de Pedidos");
                System.out.println("1. Registrar un nuevo pedido");
                System.out.println("2. Atender el próximo pedido");
                System.out.println("3. Ver estado de pedidos pendientes");
                System.out.println("4. Salir");
                System.out.print("Elija una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1:
                        registrarPedido(scanner);
                        break;
                    case 2:
                        atenderPedido();
                        break;
                    case 3:
                        mostrarPedidosPendientes();
                        break;
                    case 4:
                        System.out.println("Saliendo del sistema.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
                System.out.println();
            } while (opcion != 4);
        }
    }

    private static void registrarPedido(Scanner scanner) {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = scanner.nextLine();
        Pedido pedido = new Pedido(nombreCliente);

        System.out.print("Ingrese el número de productos a pedir: ");
        int numProductos = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < numProductos; i++) {
            System.out.print("Nombre del producto " + (i + 1) + ": ");
            String nombreProducto = scanner.nextLine();
            System.out.print("Cantidad de " + nombreProducto + ": ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); 

            pedido.agregarProducto(nombreProducto, cantidad);
        }

        pedidos.add(pedido);
        System.out.println("Pedido registrado exitosamente.");
    }  

    private static void atenderPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos pendientes.");
        } else {
            Pedido pedidoAtendido = pedidos.poll();
            System.out.println("Atendiendo el pedido:");
            System.out.println(pedidoAtendido);
        }
    }

    private static void mostrarPedidosPendientes() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos pendientes.");
        } else {
            System.out.println("Pedidos pendientes:");
            pedidos.forEach((pedido) -> {
                System.out.println(pedido);
            });
        }
    }
}
