package _03_Structural;

/*
* 외국의 전자제품중에서는 전원 어댑터 규격이 한국과는 달라서 사용하기 곤란한 경우가 있습니다. 이럴 때 변환 어댑터를 이용해서 한국 콘센트에서도 사용할 수 있도록 합니다.
즉, 클라이언트의 요구 타입과 반환 타입이 다를지라도 중간에 어댑터를 둠으로써 적절히 가공하여 둘을 연결지어준다는 것이죠.
이 어댑터 패턴(Adapter Pattern)을 사용하면 전혀 다른 인자값을 가지고도 몇몇 알고리즘을 사용해서 로직을 수행할 수 있습니다.
*
* 연관없는 두 객체를 연결해서 원하는 요구사항을 수용한다면
*  생산성 측면에서도 더 높아질 수 있습니다.
이를 통해 우리는 매번 요구사항마다 새로운 알고리즘을 만들거나
* 타입별로 비슷한 알고리즘들을 새로 구현할 필요 없이
* 기존의 알고리즘을 변형해 재활용 할 수 있게 됩니다.
* 예를 들어 리스트(ArrayList)컬렉션을 버블정렬을 해야하는 요구사항이 생겼을 때
* 나한테 배열을 기준으로 버블정렬을 구현한 알고리즘이 있다면,
* 굳이 리스트컬렉션을 이용한 새로운 알고리즘을 구현할 필요 없이
* 리스트를 배열로 만들어서 알고리즘을 수행 후
* 다시 리스트컬렉션으로 변환화는게 낫습니다.
* */

//기존에 보유하고있는 알고리즘
class Math {
    static double twoTime(double num){
        return num*2;
    }
    static double half(double num){
        return num/2;
    }
}
// 요구사항
//1. Float 인자값을 받아서 수의 두 배를 Float 타입으로 반환하는 twiceOf 메서드를 구현
//2. Float 인자값을 받아서 수의 절반을 Float 타입으로 반환하는 harfOf 메서드를 구현
//3. 구현 객체명은 Adapter로 명명
interface Adapter {
    public Float twiceOf(Float f);
    public Float halfOf(Float f);
}
class AdapterImpl implements Adapter{

    @Override
    public Float twiceOf(Float f) {
        return (float) Math.twoTime(f.doubleValue());
    }

    @Override
    public Float halfOf(Float f) {
        return (float) Math.half(f.doubleValue());
    }
}