package cn.infocore.springboot.test;


import cn.infocore.springboot.convert.ComplexSourceDestinationMapper;
import cn.infocore.springboot.convert.SimpleSourceDestinationMapper;
import cn.infocore.springboot.description.ComplexDestination;
import cn.infocore.springboot.description.SimpleDestination;
import cn.infocore.springboot.source.ComplexSource;
import cn.infocore.springboot.source.SimpleSource;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/3 15:01
 * @Description
 */
public class ComplexSourceDestinationTest {
    public static void main(String[] args) {
        testSourceToDestination();
        testDestinationToSource();
    }

    private static void testDestinationToSource() {
        ComplexDestination destination = new ComplexDestination("12355", "提莫队长", "26");
        ComplexSource source = ComplexSourceDestinationMapper.INSTANCE.destinationToSource(destination);

        System.out.println(source);
    }

    private static void testSourceToDestination() {
        ComplexSource source = new ComplexSource(131520, "法外狂徒", "infocore");
        ComplexDestination destination = ComplexSourceDestinationMapper.INSTANCE.sourceToDestination(source);
        System.out.println(destination.toString());

    }
}
