public class ToolStore extends NormalLocation {

    public ToolStore(Player player) {
        super(player, "ToolStore");
    }

    public boolean onLocation() {
        System.out.println("=============================");
        this.getPlayer().playerStats();
        boolean showMenu = true;

        while(showMenu){
            System.out.println();
            System.out.println("1- Silahlar\n2- Zırhlar\n0- Çıkış");
            System.out.print("Seçiminizi Yapınız: ");
            int selTool = scan.nextInt();

            while (selTool < 0 || selTool > 2 ){
                System.out.print("Geçersiz Seçim, Tekrar seçim yapınız: ");
                selTool = scan.nextInt();
            }

            switch (selTool) {
                case 1:
                    weaponMenu();
                    buyWeapon();
                    break;
                case 2:
                    armorMenu();
                    buyArmor();
                    break;
                case 0:
                    System.out.println("Çıkış Yapılıyor...");
                    showMenu = false;
                    break;
            }
        }

        return true;
    }

    public void weaponMenu() {
        System.out.println();
        System.out.println("----------- Silahlar -----------");
        for(Weapon weapon: Weapon.weapons()){
            System.out.println(weapon.getId() + "- " + weapon.getName() +
                    " < Hasar: " + weapon.getDamage() +
                    ", Para: " + weapon.getPrice() + " >");
        }
        System.out.println("0- Çıkış Yap");
    }

    public void buyWeapon() {
        System.out.print("Seçim Yapınız: ");
        int selectWeaponID = scan.nextInt();

        while(selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length){
            System.out.print("Geçersiz seçim, Tekrar seçim yapınız: ");
            selectWeaponID = scan.nextInt();
        }

        if(selectWeaponID != 0){

            Weapon selectedWeapon = Weapon.getWeaponObjectID(selectWeaponID);

            if(selectedWeapon.getName().equals(this.getPlayer().getInventory().getWeapon().getName())){
                System.out.println();
                System.out.println("Bu eşya envanterinizde mevcut olduğu için tekrar satın alamazsınız !!");
            } else{
                if(selectedWeapon != null){
                    System.out.println();
                    if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                        System.out.println("Yetersiz Para !!!");
                    } else {
                        System.out.println("Silah Satın Alındı: " + selectedWeapon.getName());
                        int balance = getPlayer().getMoney() - selectedWeapon.getPrice();
                        this.getPlayer().setMoney(balance);
                        this.getPlayer().getInventory().setWeapon(selectedWeapon);
                        System.out.println("Mevcut Hasar: " + this.getPlayer().getTotalDamage());
                        System.out.println("Mevcut Para: " + this.getPlayer().getMoney());
                    }
                }
            }

        }
    }

    public void armorMenu() {
        System.out.println();
        System.out.println("----------- Zırhlar -----------");
        for(Armor armor : Armor.armors()){
            System.out.println(armor.getId() + "- " + armor.getName() +
                    " < Zırh: " + armor.getAvoid() +
                    ", Para: " + armor.getPrice() + " >");
        }
        System.out.println("0- Çıkış");
    }

    public void buyArmor() {

        System.out.print("Seçiminizi Yapınız: ");
        int selectArmor = scan.nextInt();

        while(selectArmor < 0 || selectArmor > Armor.armors().length){
            System.out.print("Geçersiz seçim, Tekrar Seçim Yapınız: ");
            selectArmor = scan.nextInt();
        }

        if(selectArmor != 0 ){
            Armor selectedArmor = Armor.getArmorObjectID(selectArmor);

            if(selectedArmor.getName().equals(this.getPlayer().getInventory().getArmor().getName())){
                System.out.println("Bu eşya envanterinizde mevcut olduğu için tekrar satın alamazsınız !!");
            } else {
                System.out.println();
                if(selectedArmor != null){
                    if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                        System.out.println("Yetersiz Para !!!");
                    } else {
                        System.out.println("Zırh Satın Alındı: " + selectedArmor.getName());
                        int balance = getPlayer().getMoney() - selectedArmor.getPrice();
                        this.getPlayer().setMoney(balance);
                        this.getPlayer().getInventory().setArmor(selectedArmor);
                        System.out.println("Mevcut Engelleme: " + this.getPlayer().getInventory().getArmor().getAvoid());
                        System.out.println("Mevcut Para: " + this.getPlayer().getMoney());
                    }
                }
            }
        }
    }
}
