public class Weapon {
    private String name;
    private int id;
    private int damage;
    private int price;

    public Weapon(String name, int id, int damage, int price){
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.price = price;
    }

    public static Weapon[] weapons(){
        Weapon[] weaponList = {new Weapon("Tabanca",1,2,25),
                new Weapon("Kılıç",2,3,35),
                new Weapon("Tüfek",3,7,45)};
        return weaponList;
    }

    public static Weapon getWeaponObjectID(int id) { // Bu method geriye id si eşit bir weapon dondurur.
        // Weapon weapon=null; diyip nesneyi döndürebiliriz.
        for (Weapon w : weapons()) {
            if (id == w.getId()) {
                return w;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
