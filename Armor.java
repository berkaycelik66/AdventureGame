public class Armor {

    private String name;
    private int id;
    private int avoid;
    private int price;

    public Armor(String name, int id,int avoid,int price){
        this.name = name;
        this.id = id;
        this.avoid = avoid;
        this.price = price;
    }

    public static Armor[] armors(){
        Armor[] armorList = {new Armor("Hafif", 1, 1, 15),
                new Armor("Orta",2,3,25),
                new Armor("Ağır",3,5,40)};
        return armorList;
    }

    public static Armor getArmorObjectID(int id) { // Bu method geriye id si eşit bir armor dondurur.
        // Armor armor = null; diyip nesneyi döndürebiliriz.
        for (Armor a : armors()) {
            if (id == a.getId()) {
                return a;
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

    public int getAvoid() {
        return avoid;
    }

    public void setAvoid(int avoid) {
        this.avoid = avoid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
