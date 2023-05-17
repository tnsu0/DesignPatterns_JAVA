package _03_Structural;

/*
* Adapter는 변경할 수 없는 클래스를
* 원하는 형태의 인터페이스나 클래스로 사용하고자 할때
* Adapter 클래스를 도입하여 사용할 수 있다.
* */

import java.util.ArrayList;

class MainAd2 {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals.add(new Dog("댕이"));
        animals.add(new Cat("냥이"));
        animals.add(new TigerAdapter("타거"));

        animals.forEach(animal -> {
            animal.sound();
        });
    }
}
//기존 코드 tiger는
//새로 만들려는 Animal 클래스를 상속받을 수 없음
//Adapter 패턴이 필요함
class Tiger {
    private String name;

    void setName(String name){
        this.name = name;
    }

    String getName() {
        return name;
    }

    void roar() {
        System.out.println("growl");
    }
}

class TigerAdapter extends Animal {
    private Tiger tiger;

    public TigerAdapter(String name) {
        super(name);

        tiger = new Tiger();
        tiger.setName(name);
    }

    @Override
    public void sound() {
        System.out.print(tiger.getName() + " ");
        tiger.roar();
    }
}


// 아래는 새로운 객체지향 코드
abstract class Animal {
    protected String name;

    public Animal(String name){
        this.name = name;
    }

    public abstract void sound();
}

class Dog extends Animal {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void sound() {
        System.out.println(name + " Barking");
    }
}

class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public void sound() {
        System.out.println(name + " Meow");
    }
}