package _04_Behavioral;

/*
* https://www.youtube.com/watch?v=T3sXKtlr0Ow
* */

/*
* 동일한 형태의 데이터 항목을 여러개 가지고 있는 것을
* Container 혹은 Aggregator라고 한다.
* Aggregator의 종류에는 배열, Linked List, Tree, Graph, Table(DBMS) 등등이 있다.
* Aggregator의 종류에 구애받지 않고 통일된 하나의 방법으로 구성데이터를 가져오는 방법이
* 바로 Iterator 패턴이다.
* */

class MainEntery {
    public static void main(String[] args) {
        Item[] items = {
                new Item("CPU", 1000),
                new Item("Keyboard", 2000),
                new Item("Mouse", 1000),
                new Item("HDD", 3000)
        };

        Array array = new Array(items);
        Iterator it = array.iterator();

        while(it.next()){
            Item item = (Item)it.current();
            System.out.println(item);
        }
    }
}

interface Iterator {
    boolean next();
    Object current();
}

interface Aggregator {
    Iterator iterator();
}

class Array implements Aggregator {
    private Item[] items;

    public Array(Item[] items){
        this.items = items;
    }

    public Item getItem(int index) {
        return items[index];
    }

    public int getCount() {
        return items.length;
    }
    @Override
    public Iterator iterator() {
        return new ArrayIterator(this);
    }
}

class ArrayIterator implements Iterator{
    private Array array;
    private int index;

    public ArrayIterator(Array array){
        this.array = array;
        this.index = -1;
    }

    @Override
    public boolean next() {
        index++;
        return index < array.getCount();
    }

    @Override
    public Object current() {
        return array.getItem(index);
    }
}

class Item {
    private String name;
    private int cost;

    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + cost + ")";
    }
}