package pl.lodz.p.it.bges.shop.dto;

public class Views {

    public interface Version {
    }

    public interface Id {
    }


    public interface Basic {

    }

    public interface Normal extends Basic {

    }

    public interface Extended extends Normal {

    }

    public interface Internal {

    }


    public interface Details extends Id, Version, Normal {

    }

    public interface Modify extends Version, Normal {

    }

    public interface List extends Id, Basic {

    }

}
