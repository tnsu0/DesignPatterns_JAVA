package _04_Behavioral;

/*
* https://www.youtube.com/watch?v=Wao5HiXM_Cg
* */

/*
* 어떤 하나의 기능을 구성하는 특정 부분을 실행중에
* 다른것으로 효과적으로 변경할 수 있는 방안을 제공한다.
* 필요한 경우 전략을 변경할 수 있는 패턴.
* */

/*
여러 알고리즘을 하나의 추삭적인 접근점(Interface)을 만들어 접근점에서 서로 교환 가능(Deligate)하도록 하는 패턴
사용자(Client)는 자신에게 맞는 전략(Strategy)을 취사선택하여 로직을 수행할 수 있게하는 방법입니다.
게임을 예로들면 캐릭터는 공격이라는 작업을 수행함에 있어 무기를 상황에 맞게 선택해서 공격을 위임할 수 있습니다.
Weapon 이라는 인터페이스를 상속해서 같은 기능(책임)만 수행할 수 있다면 얼마든지 다른 무기도 추가할 수 있습니다.
*/
class MainSy {
    public static void main(String[] args) {
        GameCharacter character = new GameCharacter();
        character.attack();

        character.setWeapon(new Knife());
        character.attack();

        character.setWeapon(new Sword());
        character.attack();
    }
}

interface Weapon {
    void attack();
}

class Knife implements Weapon{

    @Override
    public void attack() {
        System.out.println("칼 공격");
    }
}

class Sword implements Weapon{

    @Override
    public void attack() {
        System.out.println("검 공격");
    }
}

class GameCharacter {
    //접근점
    private Weapon weapon;

    //교환 가능
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
    public void attack(){
        if (weapon == null){
            System.out.println("맨손 공격");
        }
        else {
            //위임(Delegate)
            weapon.attack();
        }
    }
}