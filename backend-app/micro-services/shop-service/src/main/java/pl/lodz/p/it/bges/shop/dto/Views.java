package pl.lodz.p.it.bges.shop.dto;

public class Views {

    public interface Base {
    }

    public interface Id {
    }

    public interface Internal {
    }


    public interface Address extends Base {

    }


    public interface Client extends Base {

    }


    public interface OrderPublic extends Base {

    }

    public interface Order extends OrderPublic {

    }

    public interface OrderExtended extends Order {

    }

    public interface OrderItemPublic extends Base {

    }

    public interface OrderItem extends OrderItemPublic {

    }


    public interface ClientWithAddress extends Address, Client {

    }

    public interface OrderWithClient extends Order, Client {
    }

    public interface OrderWithAddress extends Order, Address {
    }

    public interface OrderWithItems extends Order, OrderItem {
    }


    public interface getClient extends ClientWithAddress, Id {
    }

    public interface patchClient extends ClientWithAddress {
    }

    public interface postOrder extends OrderWithAddress, OrderWithItems {

    }

}
