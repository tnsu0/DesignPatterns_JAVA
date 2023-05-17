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

class Citizen {
    private int strength;
    private int agility;
    private int intelligence;

    Citizen(){
        this.strength = 1;
        this.agility = 1;
        this.intelligence = 5;
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
}

class Soldier extends Citizen{

    private WeaponTM weapon;
    private ArmorTM armor;

    public void setWeapon(WeaponTM weapon) {
        this.weapon = weapon;
        if(weapon == null){
            System.out.println("장비 미보급");
        }
        else {
            weapon.getEq();
            weapon.myWeapon();
        }
    }

    public void setArmor(ArmorTM armor) {
        this.armor = armor;
        if(armor == null){
            System.out.println("장비 미보급");
        }
        else {
            armor.getEq();
            armor.myArmor();
        }
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

    Soldier conscription() {
        Citizen citizen = conscriptionCitizen();
        training(citizen);
        Soldier soldier = changeOfPosition(citizen);
        supplyEquipment(soldier);
        return soldier;
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
            soldier.setArmor(new steelAmor());
        }

        @Override
        protected Soldier changeOfPosition(Citizen citizen) {
            return null;
        }
    }

}