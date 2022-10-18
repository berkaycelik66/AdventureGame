public class SafeHouse extends NormalLocation {

    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }

    public boolean onLocation() {
        System.out.println();
        this.getPlayer().setHealth(this.getPlayer().getRealHealth());
        System.out.println("Canınız Yenilenmiştir !!");
        System.out.println("Mevcut Can: " + this.getPlayer().getHealth());
        return true;
    }
}
