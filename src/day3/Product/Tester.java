package day3.Product;

import day3.Enums.*;

public class Tester {

    public static void main(String[] args) {
        // 初始化产品
        initializeInventory();

        //设置搜索条件1---类型：服务器
        System.out.println("搜索条件-类型：服务器");
        InstrumentSpec whatBryanLikes = new InstrumentSpec()
                .setInstrumentClass(InstrumentClass.SERVER);

        //搜索1
        Inventory.searchFor(whatBryanLikes);

        //设置搜索条件2---品牌：苹果，类型：笔记本
        System.out.println("搜索条件-品牌：apple，类型：笔记本");
        InstrumentSpec whatBryanLikes2 = new InstrumentSpec()
                .setInstrumentBrand(InstrumentBrand.APPLE)
                .setInstrumentClass(InstrumentClass.LAPTOP);

        //搜索2
        Inventory.searchFor(whatBryanLikes2);

        //设置搜索条件3---品牌：华为，类型：笔记本
        System.out.println("搜索条件-品牌：huawei，类型：台式机");
        InstrumentSpec whatBryanLikes3 = new InstrumentSpec()
                .setInstrumentBrand(InstrumentBrand.HUAWEI)
                .setInstrumentClass(InstrumentClass.DESKTOP);

        //搜索2
        Inventory.searchFor(whatBryanLikes3);

    }

    //初始化部分机器
    private static Inventory initializeInventory() {
        Inventory inventory = new Inventory();

        InstrumentSpec instrument1 = getInstrument1();
        inventory.addInstrument("1", 16999, instrument1);

        InstrumentSpec instrument2 = getInstrument2();
        inventory.addInstrument("2", 8999, instrument2);

        InstrumentSpec instrument3 = getInstrument3();
        inventory.addInstrument("3", 10999, instrument3);

        return inventory;
    }

    private static InstrumentSpec getInstrument1() {
        return new InstrumentSpec()
                .setInstrumentCPU(InstrumentCPU.RALON)
                .setInstrumentBrand(InstrumentBrand.THINKPAD)
                .setInstrumentClass(InstrumentClass.SERVER);
    }

    private static InstrumentSpec getInstrument2() {
        return new InstrumentSpec()
                .setInstrumentCPU(InstrumentCPU.INTER)
                .setInstrumentBrand(InstrumentBrand.HUAWEI)
                .setInstrumentClass(InstrumentClass.LAPTOP);
    }

    private static InstrumentSpec getInstrument3() {
        return new InstrumentSpec()
                .setInstrumentCPU(InstrumentCPU.INTER)
                .setInstrumentBrand(InstrumentBrand.APPLE)
                .setInstrumentClass(InstrumentClass.LAPTOP);
    }


}
