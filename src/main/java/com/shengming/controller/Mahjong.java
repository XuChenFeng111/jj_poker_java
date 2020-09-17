package com.shengming.controller;


import java.util.*;

/**
 * Description: 条strip 筒canister 万 ten thousand
 *
 * @Author Fann
 * @Data 2018/11/14
 */
public class Mahjong {
    //牌插入时候的序号,从1开始
    int n = 1;
    //插入的花色类型
    int[] num = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    String[] typeOne = new String[]{"筒", "万", "条"};
    String[] typeTwo = new String[]{"东风", "西风", "南风", "北风", "红中", "白板", "发财"};

    LinkedList<Integer> idMahjong;          //存储牌的顺序,利用牌的Integer可以通过hashmap查询到花色.
    HashMap<Integer, String> idWithValue;    //key:牌的序号,value:花色
    ArrayList<ArrayList<Integer>> player;   //存储四位玩家的ArrayList,默认第一位庄

    /**
     * 默认构造方法
     */
    public Mahjong() {
        this.idMahjong = new LinkedList<Integer>();
        this.idWithValue = new HashMap<Integer, String>();
        this.player = new ArrayList<ArrayList<Integer>>( 4 );
        for (int i = 0; i < 4; i++) {
            player.add( new ArrayList<Integer>() );
        }
    }

    /**
     * 整理麻将,共136张 并且打乱.
     */
    public void arrangeMahjong() {
        //加入筒,万,条
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < typeOne.length; j++) {
                String s = num[i] + typeOne[j];
                for (int k = 0; k < 4; k++) {  //每张牌插入四次
                    idWithValue.put( n, s );
                    idMahjong.add( n );
                    n++;
                }
            }
        }
        //插入东,西,南,北风,红中,白板,发财
        for (int i = 0; i < typeTwo.length; i++) {
            String s = typeTwo[i];
            for (int j = 0; j < 4; j++) {
                idWithValue.put( n, s );
                idMahjong.add( n );
                n++;
            }
        }
        //对牌进行打乱
        Collections.shuffle( idMahjong );
    }

    /**
     * 发牌的过程
     */
    public void dealMahjong() {
        //利用ArrayList--idMahjong迭代器进行摸牌
        //四个人交替摸牌的过程,  每次四张, 三次.
        Iterator<Integer> iteratorForId = idMahjong.iterator();
        for (int i = 0; i < 3; i++) {  //三次
            for (int j = 0; j < 4; j++) {  //轮到第几号玩家
                for (int k = 0; k < 4; k++) {  //摸牌的张数
                    Integer data = iteratorForId.next();
                    player.get( j ).add( data );
                    iteratorForId.remove(); //同时在牌中删除这个.
                }
            }
        }

        //之后是庄家摸两张,两张间索引差4,其他人再摸一张
        //意思就是再摸5张牌,庄家拿1和5号,其他人拿2 3 4号
        for (int i = 0; i < 4; i++) {
            Integer data = iteratorForId.next();
            player.get( i ).add( data );
            iteratorForId.remove();
        }
        player.get( 0 ).add( iteratorForId.next() );  //第五张,
        iteratorForId.remove();
    }

    /**
     * 展示四位玩家的牌,和底牌.四位玩家的牌是有顺序的,底牌是乱序的
     */
    public void printMahjong() {
        for (int i = 0; i < 4; i++) {
            int t = 0;
            System.out.print("第"+(i+1)+"位player的牌:");
            Collections.sort( player.get( i ),comparator );
            Iterator<Integer> iteratorPlayer = player.get( i ).iterator();
            while (iteratorPlayer.hasNext()){
                Integer key = iteratorPlayer.next(); //迭代器里面存的是牌的序号,也就是key
                System.out.print(idWithValue.get( key )+" ");
                t++;
            }
            System.out.println("一共"+t+"张");
        }
        /**
         * 打印出底牌
         */
        System.out.print("底桌上的牌:");
        int t = 0;
        Iterator<Integer> iteratorForDesk = idMahjong.iterator();
        while (iteratorForDesk.hasNext()){
            Integer key = iteratorForDesk.next();
            System.out.print(idWithValue.get( key )+" ");
            t++;
        }
        System.out.println("牌桌上一共剩下"+t+"张");
    }

    /**
     * 比较器类,根据玩家ArrayList中的Integer得到HashMap中的String,比较String
     * 按照 筒,万,条,风,红中,白板,发财  记录优先级为1-7
     */
    private Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            String s1 = idWithValue.get( o1 );
            int levelForS1 = 0;
            String s2 = idWithValue.get( o2 );
            int levelForS2 = 0;

            switch (s1.charAt( 1 )) {
                case '筒': levelForS1 = 1;break;
                case '万': levelForS1 = 2;break;
                case '条': levelForS1 = 3;break;
                case '风': levelForS1 = 4;break;
                case '中': levelForS1 = 5;break;
                case '板': levelForS1 = 6;break;
                case '财': levelForS1 = 7;break;
            }
            switch (s2.charAt( 1 )) {
                case '筒': levelForS2 = 1;break;
                case '万': levelForS2 = 2;break;
                case '条': levelForS2 = 3;break;
                case '风': levelForS2 = 4;break;
                case '中': levelForS2 = 5;break;
                case '板': levelForS2 = 6;break;
                case '财': levelForS2 = 7;break;
            }

            if (levelForS1 < levelForS2) { //如果第一个的优先级高,返回-1,让第一张牌在前面
                return -1;
            } else if (levelForS1 > levelForS2) {
                return 1;
            } else {  //证明两个优先级相等,接着比较牌1-9
                //如果都是筒or万 or条,比较数字.
                if (levelForS1 == 1 || levelForS1 == 2 || levelForS1 == 3) {
                    switch (s1.charAt( 0 )) {
                        case '1': levelForS1 = 1;break;
                        case '2': levelForS1 = 2;break;
                        case '3': levelForS1 = 3;break;
                        case '4': levelForS1 = 4;break;
                        case '5': levelForS1 = 5;break;
                        case '6': levelForS1 = 6;break;
                        case '7': levelForS1 = 7;break;
                        case '8': levelForS1 = 8;break;
                        case '9': levelForS1 = 9;break;
                    }
                    switch (s2.charAt( 0 )) {
                        case '1': levelForS2 = 1;break;
                        case '2': levelForS2 = 2;break;
                        case '3': levelForS2 = 3;break;
                        case '4': levelForS2 = 4;break;
                        case '5': levelForS2 = 5;break;
                        case '6': levelForS2 = 6;break;
                        case '7': levelForS2 = 7;break;
                        case '8': levelForS2 = 8;break;
                        case '9': levelForS2 = 9;break;
                    }
                    return levelForS1 - levelForS2; //如果第一个数字比较小,返回的是负值,保证第一张牌在前面.

                } else if (levelForS1 == 4) { //如果是风,比较东西南北
                    switch (s1.charAt( 0 )) {
                        case '东': levelForS1 = 1;break;
                        case '西': levelForS1 = 2;break;
                        case '南': levelForS1 = 3;break;
                        case '北': levelForS1 = 4;break;
                    }
                    switch (s2.charAt( 0 )) {
                        case '东': levelForS2 = 1;break;
                        case '西': levelForS2 = 2;break;
                        case '南': levelForS2 = 3;break;
                        case '北': levelForS2 = 4;break;
                    }
                    return levelForS1 - levelForS2;//如果第一个数字比较小,返回的是负值,保证了第一张牌在前面.

                } else {
                    return levelForS1 - levelForS2; //其他情况就是红中,发财,白板.
                }
            }
        }
    };

    public static void main(String[] args) {
        Mahjong mahjong = new Mahjong();
        //整理牌
        mahjong.arrangeMahjong();
        //发牌
        mahjong.dealMahjong();
        //打印牌
        mahjong.printMahjong();
    }
}

