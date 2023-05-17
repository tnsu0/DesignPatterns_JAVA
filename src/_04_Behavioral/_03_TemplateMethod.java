package _04_Behavioral;

/*
템플릿은 비유하자면
일종의 붕어빵 틀, 타꼬야끼 틀과 비슷하다고 볼 수 있으며,
알고리즘의 구조를 메소드에 정의하고
하위 클래스에서 알고리즘 구조의 변경없이
알고리즘을 재정의 하는 패턴입니다.
*/

/*
템플릿 메서드 패턴은 다음과 같은 목적을 가진다.
작업에서 알고리즘의 골격을 정의하고
일부 단계를 하위 클래스로 연기합니다.
템플릿 메서드를 사용해 하위 클래스에서 전체 구조를 변경하지 않고
알고리즘의 특정 단계를 재정의할 수 있습니다.
* */

//일정한 프로세스 를 가진 요구사항을 템플릿 메소드 패턴을 이용하여 구현할 수 있습니다.
//ex: Spring Security의 인증및 인가과정, 애노테이션 프로세서의 라운드구조, 로직의 완성까지의 로직이 순차적인 일정한 단계가 있는 경우 등.

class MainTM {
    public static void main(String[] args) {
        Citizen h1 = new Citizen();
        AbstSoldierConscriptionHelper helper = new SoldierConscriptionHelperImpl();
        Soldier soldier = helper.conscription();
        soldier.attack();
        soldier.defense();
    }
}

/**요구사항 : 시민들을 징집해서 병사로 만들어 주세요
 1.
 시민들 중 마린이 될 수 있는 조건의 시민 징집합니다.
 conscriptionCitizen():Citizen
 2.
 징집된 군인들을 훈련합니다.
 traning(Citizen):void
 3.
 보직을 시민에서 병사로 변경합니다.
 changeOfPosition(Citizen citizen):Soldier
 4.
 장비를 보급합니다.
 supplyEquipment(Solder soldier);**/

interface HumanTM{
    void myNoti();
}
class Human implements HumanTM{
    private WeaponTM weapon;
    public ArmorTM getArmor() {
        return armor;
    }
    private ArmorTM armor;
    public String getNoti() {
        return noti;
    }
    public void setNoti(String noti) {
        this.noti = noti;
    }
    public WeaponTM getWeapon() {
        return weapon;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    private String noti;
    public int getAgility() {
        return agility;
    }
    public void setAgility(int agility) {
        this.agility = agility;
    }
    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    private int strength;
    private int agility;
    private int intelligence;
    public Human(){
        this.strength = 1;
        this.agility = 1;
        this.intelligence = 4;
    }

    public void updateStrength(int i) {
        this.strength += i;
    }

    public void updateAgility(int i) {
        this.agility += i;
    }

    public void updateIntelligence(int i){
        this.intelligence += i;
    }

    @Override
    public void myNoti() {
        if(getNoti() == null) {
            System.out.println("신원 조회가 안됩니다.");
        } if (getNoti().equals("Citizen")) {
            System.out.println("시민입니다.");
        } else if (getNoti().equals("Soldier")) {
            System.out.println("군인입니다.");
        }
    }

    public void setWeapon(WeaponTM weapon) {
        this.weapon = weapon;
        if(getWeapon() == null){
            System.out.println("무기 미보급");
        }
        else {
            weapon.getEq();
            weapon.myWeapon();
        }
    }

    public void setArmor(ArmorTM armor) {
        this.armor = armor;
        if(armor == null){
            System.out.println("방어구 미보급");
        }
        else {
            armor.getEq();
            armor.myArmor();
        }
    }

    public void attack() {
        if(!getWeapon().equals(null)){
            getWeapon().myWeapon();
            System.out.println("으로 공격합니다.");
        }
        else {
            System.out.println("맨손으로 공격합니다.");
        }
    }
    public void defense() {
        if(getArmor() != null){
            getArmor().myArmor();
            System.out.println("으로 방어합니다");
        } else{
            System.out.println("맨몸으로 막습니다.");
        }
    }
}

class Citizen extends Human implements HumanTM{

    public Citizen(){
        setStrength(1);
        setAgility(2);
        setIntelligence(5);
        setNoti("citizen");
    }

}

class Soldier extends Citizen{

    public Soldier(Citizen citizen) {
        super();
        setNoti("Soldier");
    }
}

interface eqTemplateMethod {
    void getEq();
}

interface WeaponTM extends eqTemplateMethod{
    void myWeapon();
}

class Gun implements WeaponTM{
    @Override
    public void getEq() {
        System.out.println("무기 착용");
    }

    @Override
    public void myWeapon() {
        System.out.println("현재 무기 : 총");
    }
}

interface ArmorTM extends eqTemplateMethod{
    void myArmor();
}
class steelArmor implements ArmorTM{
    @Override
    public void getEq() {
        System.out.println("갑옷 착용");
    }

    @Override
    public void myArmor() {
        System.out.println("현재 갑옷 : 철갑옷");
    }
}

abstract class AbstSoldierConscriptionHelper {
    // 시민들 중 마린이 될 수 있는 조건의 시민 징집
    protected abstract Citizen conscriptionCitizen();

    // 징집된 군인들을 훈련
    protected abstract void training(Citizen citizen);

    // 장비를 보급
    protected abstract void supplyEquipment(Soldier soldier);

    // 보직을 시민에서 병사로 변경
    protected abstract Soldier changeOfPosition(Citizen citizen);

    protected abstract Soldier conscription();
}
class SoldierConscriptionHelperImpl extends AbstSoldierConscriptionHelper {

    @Override
    protected Citizen conscriptionCitizen() {
        System.out.println("징집!");
        return new Citizen();
    }

    @Override
    protected void training(Citizen citizen) {
        citizen.updateStrength(5);
        citizen.updateAgility(4);
        citizen.updateIntelligence(-3);
        System.out.println("병사훈련!");
    }

    @Override
    protected void supplyEquipment(Soldier soldier) {
        soldier.setWeapon(new Gun());
        soldier.setArmor(new steelArmor());
        System.out.println("장비 보급 완료");
    }

    @Override
    protected Soldier changeOfPosition(Citizen citizen) {
        System.out.println("시민 > 병사 변경");
        return new Soldier(citizen);
    }

    @Override
    protected Soldier conscription() {
        Citizen citizen = conscriptionCitizen();
        training(citizen);
        Soldier soldier = changeOfPosition(citizen);
        supplyEquipment(soldier);
        return soldier;
    }
}