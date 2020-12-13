package pl.lodz.p.it.bges.account.dto;

public class Views {

    public interface Base {
    }

    public interface UserPublic extends Base {

    }

    public interface User extends UserPublic {

    }

    public interface UserSecret extends User {

    }
}
