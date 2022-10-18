import java.util.Random;

public abstract class BattleLocation extends Location {
    protected Obstacle obstacle;
    protected String award;

    Random random = new Random();

    public BattleLocation(Player player, String name, Obstacle obstacle, String award) {
        super(player);
        this.name = name;
        this.obstacle = obstacle;
        this.award = award;
    }

    public boolean onLocation() {
        this.getObstacle().randomObstacleNumber();
        System.out.println("==============================");
        System.out.println("Şuan buradasınız: " + this.getName());
        System.out.println("Dikkatli Ol !!! " +
                "Şu an burada " + this.getObstacle().getMaxObstacle() + " adet " + obstacle.getName() + " yaşıyor");
        System.out.print("<S>avaş ya da <K>aç : ");
        String selCase = scan.nextLine().toUpperCase();

        if (selCase.equals("S")) {

            if (combat()) {
                System.out.println();
                System.out.println(this.getName() + " bölgesindeki tüm düşmanları öldürdünüz.");

                winAward();
                return true;
            }

            if (this.getPlayer().getHealth() <= 0) {
                System.out.println("Öldünüz...");
                return false;
            }
        } else {
            return true;
        }
        return true;
    }

    public boolean combat() {
        for (int i = 0; i < this.getObstacle().getMaxObstacle(); i++) {
            this.getObstacle().setHealth(this.getObstacle().getRealHealth());
            if (i == 0) {
                this.getPlayer().playerStats();
                this.getObstacle().obstacleStats();
            }
            System.out.println();
            System.out.println((i + 1) + ". " + this.getObstacle().getName() + " canavarına");
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("<V>ur ya da <K>aç : ");
                String selCase = scan.nextLine().toUpperCase();

                if (selCase.equals("V")) {
                    Random random = new Random();
                    boolean firstHit = random.nextBoolean();
                    if (firstHit) { // if firstHit is true, Player will hit the enemy
                        firstHitPlayer();
                    } else {
                        firstHitEnemy();
                    }

                } else {
                    return false;
                }

                if (this.getObstacle().getHealth() <= 0 && this.getPlayer().getHealth() > 0) {
                    System.out.println((i + 1) + ". " + this.getObstacle().getName() + " düşmanını yendiniz ve "
                            + this.getObstacle().getAward() + " para kazandınız. ");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                    System.out.println("Güncel Para: " + this.getPlayer().getMoney());
                    break;
                } else if (this.getPlayer().getHealth() <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void firstHitPlayer() {
        System.out.println();
        System.out.println(this.getObstacle().getName() + " canavarına " + this.getPlayer().getTotalDamage() + " hasar vurdun.");
        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
        afterHit();

        if (this.getObstacle().getHealth() > 0) {
            if (this.getObstacle().getDamage() < this.getPlayer().getInventory().getArmor().getAvoid()) {
                System.out.println(this.getObstacle().getName() + " sana " + 0 + " hasar vurdu");
            } else {
                System.out.println(this.getObstacle().getName() + " sana " +
                        (this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getAvoid()) + " hasar vurdu");
                this.getPlayer().setHealth(this.getPlayer().getHealth() -
                        (this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getAvoid()));
            }
            afterHit();
        }
    }

    public void firstHitEnemy() {
        System.out.println();
        if (this.getObstacle().getDamage() < this.getPlayer().getInventory().getArmor().getAvoid()) {
            System.out.println(this.getObstacle().getName() + " sana " + 0 + " hasar vurdu");
        } else {
            System.out.println(this.getObstacle().getName() + " sana " +
                    (this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getAvoid()) + " hasar vurdu");
            this.getPlayer().setHealth(this.getPlayer().getHealth() -
                    (this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getAvoid()));
        }
        afterHit();

        if (this.getPlayer().getHealth() > 0) {
            System.out.println(this.getObstacle().getName() + " canavarına " + this.getPlayer().getTotalDamage() + " hasar vurdun.");
            this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
            afterHit();
        }

    }

    public void afterHit() {
        System.out.println("-----------------------------------------");
        System.out.println("Oyuncu Can: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Can: " + this.getObstacle().getHealth());
        System.out.println("------------------------------------------");
    }

    public void snakeAward() {
        System.out.println();
        int winItem = random.nextInt(100);

        if (winItem < 15) {
            int winWeapon = random.nextInt(100);
            boolean takeWeapon = false;
            int weaponID = 0;
            if (winWeapon < 50) {
                if (!(this.getPlayer().getInventory().getWeapon().getName().equals("Tabanca"))) {
                    takeWeapon = awardWeapon("Tabanca");
                    weaponID = 1;
                }
            } else if (winWeapon < 80) {
                if (!(this.getPlayer().getInventory().getWeapon().getName().equals("Kılıç"))) {
                    takeWeapon = awardWeapon("Kılıç");
                    weaponID = 2;

                }
            } else {
                if (!(this.getPlayer().getInventory().getWeapon().getName().equals("Tüfek"))) {
                    takeWeapon = awardWeapon("Tüfek");
                    weaponID = 3;
                }
            }

            if (takeWeapon) {
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjectID(weaponID));
            }
        } else if (winItem < 30) {
            int winArmor = random.nextInt(100);
            boolean takeArmor = false;
            int armorID = 0;
            if (winArmor < 50) {
                if (!(this.getPlayer().getInventory().getArmor().getName().equals("Hafif"))) {
                    takeArmor = awardArmor("Hafif");
                    armorID = 1;
                }
            } else if (winArmor < 80) {
                if (!(this.getPlayer().getInventory().getArmor().getName().equals("Orta"))) {
                    takeArmor = awardArmor("Orta");
                    armorID = 2;
                }
            } else {
                if (!(this.getPlayer().getInventory().getArmor().getName().equals("Ağır"))) {
                    takeArmor = awardArmor("Ağır");
                    armorID = 3;
                }

            }

            if (takeArmor) {
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjectID(armorID));
            }
        } else if (winItem < 55) {
            int winMoney = random.nextInt(100);
            int moneyAmount = 0;
            if (winMoney < 50) {
                moneyAmount = 3;
            } else if (winMoney < 80) {
                moneyAmount = 6;
            } else {
                moneyAmount = 9;
            }
            this.getPlayer().setMoney(this.getPlayer().getMoney() + moneyAmount);
            System.out.println();
            System.out.println("Tebrikler " + moneyAmount + " Para Kazandınız.");
            System.out.println("Mevcut Para: " + this.getPlayer().getMoney());
        } else {
            System.out.println("Maalesef item düşüremediniz !!!");
        }
    }

    public boolean awardWeapon(String weaponName) {
        System.out.println();
        System.out.println("Tebrikler " + weaponName + " Silahını Kazandınız.");
        System.out.println("Mevcut Silahınız: " + this.getPlayer().getInventory().getWeapon().getName());

        System.out.print("Almak ister Misiniz (Y/N): ");
        String selectAwardWeapon = scan.nextLine().toUpperCase();

        while (!(selectAwardWeapon.equals("Y")) && !(selectAwardWeapon.equals("N"))) {
            System.out.print("Geçersiz Seçim, Tekrar seçim yapınız: ");
            selectAwardWeapon = scan.nextLine().toUpperCase();
        }

        if (selectAwardWeapon.equals("Y")) {
            System.out.println("Yeni Silahınız: " + weaponName);
            return true;
        } else {
            return false;
        }
    }

    public boolean awardArmor(String armorName) {
        System.out.println();
        System.out.println("Tebrikler " + armorName + " Zırh Kazandınız.");
        System.out.println("Mevcut Zırhınız: " + this.getPlayer().getInventory().getArmor().getName());

        System.out.print("Almak ister Misiniz (Y/N): ");
        String selectAwardArmor = scan.nextLine().toUpperCase();

        while (!(selectAwardArmor.equals("Y")) && !(selectAwardArmor.equals("N"))) {
            System.out.print("Geçersiz Seçim, Tekrar seçim yapınız: ");
            selectAwardArmor = scan.nextLine().toUpperCase();
        }

        if (selectAwardArmor.equals("Y")) {
            System.out.println("Yeni Zırhınız: " + armorName);
            return true;
        } else {
            return false;
        }
    }

    public void winAward() {
        if (this.award.equals("Food") && !(this.getPlayer().getInventory().isFood())) {
            System.out.println(this.award + " Kazandınız !!!");
            this.getPlayer().getInventory().setFood(true);
        } else if (this.award.equals("Firewood") && !(this.getPlayer().getInventory().isFirewood())) {
            System.out.println(this.award + " Kazandınız !!!");
            this.getPlayer().getInventory().setFirewood(true);
        } else if (this.award.equals("Water") && !(this.getPlayer().getInventory().isWater())) {
            System.out.println(this.award + " Kazandınız !!!");
            this.getPlayer().getInventory().setWater(true);
        } else if (this.getName().equals("Mine")) {
            snakeAward();
        }
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

}
