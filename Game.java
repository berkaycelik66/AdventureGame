import java.util.Scanner;

public class Game {
    protected Player player;
    protected Location location;
    Scanner scan = new Scanner(System.in);

    public void start(){
        System.out.println("==== Macera Oyununa Hoşgeldin ====");
        System.out.print("İsminizi Giriniz: ");
        player = new Player(scan.nextLine());
        this.getPlayer().selectCharacter();

        while(true){
            System.out.println();
            System.out.println("======================================");
            System.out.println("1- Güvenli Ev --> Canınız yenilenir.");
            System.out.println("2- Mağara --> Karşınıza zombi çıkabilir.");
            System.out.println("3- Orman --> Karşınıza vampir çıkabilir.");
            System.out.println("4- Nehir --> Karşınıza ayı çıkabilir.");
            System.out.println("5- Maden --> Karşınıza yılan çıkabilir");
            System.out.println("6- Eşya Dükkanı --> Silah ve zırh alabilirsiniz.");
            System.out.println("7- Oyuncu Özellikleri --> Mevcut oyuncu özelliklerini gösterir.");
            System.out.println("0- Çıkış Yap --> Bu macerayı sonlandır.");
            System.out.print("Gitmek istediğiniz mekanı seçiniz: ");
            int locationID = scan.nextInt();

            while (locationID < 0 || locationID > 7) {
                System.out.println("Geçersiz Seçim !!!");
                System.out.print("Yeniden Seçim Yapınız: ");
                locationID = scan.nextInt();
            }

            switch (locationID) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    if (this.getPlayer().getInventory().isFood()){
                        location = null;
                    } else {
                        location = new Cave(player);
                    }
                    break;
                case 3:
                    if(this.getPlayer().getInventory().isFirewood()){
                        location = null;
                    } else {
                        location = new Forest(player);
                    }
                    break;
                case 4:
                    if(this.getPlayer().getInventory().isWater()){
                        location = null;
                    } else {
                        location = new River(player);
                    }
                    break;
                case 5:
                    location = new Mine(player);
                    break;
                case 6:
                    location = new ToolStore(player);
                    break;
                case 7:
                    this.getPlayer().playerStats();
                    location = null;
                    break;
                default:
                    System.out.println("Geçerli Değer Giriniz !!! ");
            }

            if(location == null && locationID == 0){
                System.out.println();
                System.out.println("Oyunu Sonlandırdın...");
                break;
            } else if (location == null){
                if(locationID == 2 || locationID == 3 || locationID == 4){
                    System.out.println();
                    System.out.println("Bu mekandaki ödülü zaten kazandığınız için tekrar giremezsiniz !!!");
                }
                continue;
            }

            if(location.getClass().getName().equals("SafeHouse")){
                if(this.getPlayer().getInventory().isWater() &&
                        this.getPlayer().getInventory().isFirewood() &&
                        this.getPlayer().getInventory().isFood()){
                    System.out.println();
                    System.out.println("***** TEBRİKLER OYUNU KAZANDINIZ *****");
                    break;
                }
            }

            if (!(location.onLocation())) {
                System.out.println("Oyun Bitti !!");
                break;
            }
        }
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
