package pl.lodz.p.it.bges.shop.dto;

public class Views {

    public interface Version {
    }

    public interface Id {
    }

    public interface Internal {
    }


    public interface Address {

    }


    public interface Client {

    }


    public interface OrderPublic {

    }

    public interface Order extends OrderPublic {

    }

    public interface OrderExtended extends Order {

    }

    public interface OrderItemPublic {

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


    public interface getClient extends ClientWithAddress, Id, Version {
    }

    public interface patchClient extends ClientWithAddress, Version {
    }

    public interface postOrder extends OrderWithAddress, OrderWithItems {

    }

    public interface getOrder extends OrderExtended, OrderWithAddress, OrderWithItems, Id, Version {

    }

    public interface getOrders extends Order, OrderExtended, Id {
    }

}
