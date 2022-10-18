import java.util.Scanner;

public class Player {
    private int id;
    private String name, characterName;
    private int damage;
    private int health;
    private int realHealth;
    private int money;
    protected Inventory inventory;
    Scanner scan = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
        inventory = new Inventory();
    }

    public void selectCharacter() {
        GameCharacter[] gameCharacters = {new Samurai(), new Archer(), new Knight()};
        System.out.println("========================================");
        for (int i = 0; i < gameCharacters.length; i++) {
            System.out.println(gameCharacters[i].getId() + ")" + gameCharacters[i].getName() + "  " +
                    "Hasar: " + gameCharacters[i].getDamage() + "  " +
                    "Can: " + gameCharacters[i].getHealth() + "  " +
                    "Para: " + gameCharacters[i].getMoney() + "  ");
        }
        System.out.print("Karakterinizi Seçerek Oyuna Başlayınız: ");
        int characterID = scan.nextInt();

        while (characterID < 1 || characterID > 3) {
            System.out.println("Geçersiz Seçim !!! ");
            System.out.print("Yeniden Seçim Yapınız: ");
            characterID = scan.nextInt();
        }

        switch (characterID) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }

        System.out.println("\nKarakteriniz Oluşturuldu !!!");
        System.out.println("İsim: " + this.getCharacterName() + " " + this.getName() +
                "\nHasar: " + this.getDamage() +
                "\nSağlık: " + this.getHealth() +
                "\nPara: " + this.getMoney() +
                "\nSilah: " + this.getInventory().getWeapon().getName() +
                "\nZırh: " + this.getInventory().getArmor().getName());
    }

    public void initPlayer(GameCharacter gameCharacter) {
        this.setId(gameCharacter.getId());
        this.setCharacterName(gameCharacter.getName());
        this.setDamage(gameCharacter.getDamage());
        this.setRealHealth(gameCharacter.getHealth());
        this.setHealth(gameCharacter.getHealth());
        this.setMoney(gameCharacter.getMoney());
    }

    public void playerStats() {
        System.out.println();
        System.out.println("Oyuncu Özellikleri ");
        System.out.println("------------------------");
        System.out.println("Can: " + this.getHealth());
        System.out.println("Silah: " + this.getInventory().getWeapon().getName());
        System.out.println("Hasar: " + this.getTotalDamage());
        System.out.println("Zırh: " + this.getInventory().getArmor().getName());
        System.out.println("Engelleme: " + this.getInventory().getArmor().getAvoid());
        System.out.println("Para: " + this.getMoney());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        } else {
            this.health = health;
        }
    }

    public int getRealHealth() {
        return realHealth;
    }

    public void setRealHealth(int realHealth) {
        this.realHealth = realHealth;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getTotalDamage() {
        return (this.getDamage() + getInventory().getWeapon().getDamage());
    }
}
